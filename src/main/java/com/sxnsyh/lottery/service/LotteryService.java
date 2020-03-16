package com.sxnsyh.lottery.service;

import com.alibaba.fastjson.JSONObject;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.entity.LogsEntity;
import com.sxnsyh.lottery.entity.PlanEntity;
import com.sxnsyh.lottery.entity.RecordEntity;
import com.sxnsyh.lottery.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class LotteryService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanInfoRepository planInfoRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    LogsRepository logsRepository;


    /**
     * 登录后将客户信息保存进sesion中
     * @param phoneNo
     * @param certNo
     */
    public JSONObject login(String phoneNo, String certNo) {
        // 查询客户信息
        CustomerEntity customerEntity = customerRepository.findFirstByPhoneNoAndCertNo(phoneNo, certNo);
        if (customerEntity == null) {
            throw new ServiceException("无对应客户信息");
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", customerEntity.getId());
            jsonObject.put("name", customerEntity.getCustomerName());
            jsonObject.put("count", customerEntity.getRemainingCount());
            return jsonObject;
        }

    }

    /**
     * 获取剩余次数
     * @param id
     * @return
     */
    public int getCount(int id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ServiceException("无对应客户！"));
        return customerEntity.getRemainingCount();
    }

    /**
     * 获取抽奖礼物
     * @return
     */
    public List<JSONObject> getPrize() {
        return prizeRepository.findAll().stream().map(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("prizeName", item.getPrizeName());
            return jsonObject;
        }).collect(Collectors.toList());
    }


    /**
     * 抽奖逻辑
     * @param customerId
     * @return
     */
    public int doLottery(int customerId) {
        // 返回礼物id
        int prizeId = 0;

        // 1.检测是否还有剩余次数
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(() -> new ServiceException("无对应客户！"));
        Integer count = customerEntity.getRemainingCount();
        if (count <= 0) {
            throw new ServiceException("今日抽奖次数已用完");
        }

        // 2.检测该客户是否已经中过奖
        if ( !"Y".equals(customerEntity.getFlag())) {

            // 3.获取计划，计算客户中奖概率
            PlanEntity planEntity = planRepository.findPlanBydate(new Date()).orElseThrow(() -> new ServiceException("今日无抽奖计划"));
            // 概率差值
            double proValue =  planEntity.getTopProbability()-planEntity.getBaseProbability();
            // 交易笔数差值
            int countDif = customerRepository.getCountDif();
            Integer transactionCount = customerEntity.getTransactionCount();
            // 概率值
            int pro = (int) Math.ceil((proValue/countDif)*(transactionCount-planEntity.getBaseProbability())+planEntity.getBaseProbability());
            // 根据概率计算客户是否中奖
            Random random = new Random();
            int randInt = random.nextInt(100);
            // 若生成的随机数小于该客户的概率值则表示中奖了
            if (randInt <= pro) {
                // 获取中奖的奖品
                Map<String, Object> lotteryPrize = prizeRepository.getLotteryPrize(planEntity.getId(),customerEntity.getTransactionCount(),customerEntity.getTransactionAmount());
                // 看奖品是否还有剩余以及该客户是否有资格,若查出来不为null则表示中哦了该产品
                if (lotteryPrize != null) {
                    // 满足笔数，金额和奖品剩余数量则中奖
                    prizeId = (int) lotteryPrize.get("prize_id");

                    // 添加更新中奖数量和中奖记录以及中奖标识
                    // 更新计划表的送出数量
                    planInfoRepository.updateSendedCount((Integer) lotteryPrize.get("id"));
                    // 更新礼物表的送出数量
                    prizeRepository.updateSendPrizeCount((Integer) lotteryPrize.get("prize_id"));
                    // 更新中奖标识
                    customerEntity.setFlag("Y");
                    // 添加中奖记录
                    RecordEntity recordEntity = new RecordEntity();
                    recordEntity.setCustomerId(customerEntity.getId());
                    recordEntity.setCustomerName(customerEntity.getCustomerName());
                    recordEntity.setPhone(customerEntity.getPhoneNo());
                    recordEntity.setPrizeId(prizeId);
                    recordEntity.setPrizeName((String) lotteryPrize.get("prize_name"));
                    recordEntity.setWinningDate(new java.sql.Date(System.currentTimeMillis()));
                    recordEntity.setCertNo(customerEntity.getCertNo());
                    recordRepository.save(recordEntity);

                }
            }
        }

        // 3.减少抽奖剩余次数
        customerEntity.setRemainingCount(customerEntity.getRemainingCount()-1);
        customerRepository.save(customerEntity);

        // 4.添加执行日志
        LogsEntity logsEntity = new LogsEntity();
        logsEntity.setCustomerId(customerEntity.getId());
        logsEntity.setCustomerName(customerEntity.getCustomerName());
        logsEntity.setLogTime(Timestamp.valueOf(LocalDateTime.now()));
        logsEntity.setPhone(customerEntity.getPhoneNo());
        logsEntity.setCertNo(customerEntity.getCertNo());
        logsRepository.save(logsEntity);


        return prizeId;
    }



}
