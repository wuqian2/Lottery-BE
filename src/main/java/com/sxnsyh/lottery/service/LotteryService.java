package com.sxnsyh.lottery.service;

import com.alibaba.fastjson.JSONObject;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.repository.CustomerRepository;
import com.sxnsyh.lottery.repository.PrizeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class LotteryService {
    @Autowired
    CustomerRepository customerRepositoryRepository;

    @Autowired
    PrizeRepository prizeRepository;

    /**
     * 登录后将客户信息保存进sesion中
     * @param phoneNo
     * @param certNo
     */
    public JSONObject login(String phoneNo, String certNo) {
        // 查询客户信息
        CustomerEntity customerEntity = customerRepositoryRepository.findFirstByPhoneNoAndCertNo(phoneNo, certNo);
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
        CustomerEntity customerEntity = customerRepositoryRepository.findById(id).orElseThrow(() -> new ServiceException("服务器出错！"));
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
        // 1.检测是否还有剩余次数
        int count = this.getCount(customerId);
        if (count <= 0) {
            throw new ServiceException("今日抽奖次数已用完");
        }

        // 2.检测该客户的交易笔数和交易金额是否满足当前规则



        return 0;
    }
}
