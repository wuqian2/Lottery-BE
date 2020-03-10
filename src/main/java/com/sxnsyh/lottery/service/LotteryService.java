package com.sxnsyh.lottery.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
@Slf4j
public class LotteryService {
    @Autowired
    CustomerRepository customerRepositoryRepository;

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
}
