package com.sxnsyh.lottery.common;

import com.sxnsyh.lottery.repository.CustomerRepository;
import com.sxnsyh.lottery.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 定时任务
 *
 * @author wuqian
 */
@Component
@Slf4j
public class TimeTask {
    @Autowired
    PlanRepository planRepository;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 默认次数
     */
    private int DEFAULT_CONT = 3;

    /**
     * 每日的0点更新次数
     */
    @Scheduled(cron = "0 16 11 * * ?")
    @Transactional
    public void resetTime() {
        planRepository.findById(1).ifPresent(planEntity -> {
            // 修改次数
            this.DEFAULT_CONT = planEntity.getLotteryCount() == 0 ? this.DEFAULT_CONT : planEntity.getLotteryCount();
        });
        customerRepository.remainingCount(this.DEFAULT_CONT);
    }

}
