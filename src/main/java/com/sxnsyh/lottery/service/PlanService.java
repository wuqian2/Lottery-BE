package com.sxnsyh.lottery.service;

import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.domain.PlanDomain;
import com.sxnsyh.lottery.domain.PlanInfoDomain;
import com.sxnsyh.lottery.entity.PlanEntity;
import com.sxnsyh.lottery.entity.PlanInfoEntity;
import com.sxnsyh.lottery.repository.PlanInfoRepository;
import com.sxnsyh.lottery.repository.PlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanService {
    @Autowired
    PlanRepository repository;

    @Autowired
    PlanInfoRepository planInfoRepository;

    /**
     * 获取计划列表
     * @return
     */
    public List<PlanDomain> getPlanList() {
        return repository.findAll().stream().map(item -> {
            // 计划
            PlanDomain planDomain = new PlanDomain();
            BeanUtils.copyProperties(item, planDomain);

            // 计划详情
            List<PlanInfoDomain> planInfoDomains = planInfoRepository.queryPlanInfo(planDomain.getId());
            planDomain.setPlanInfoList(planInfoDomains);

            return planDomain;
        }).collect(Collectors.toList());
    }


    public PlanEntity getPlan() {
        return repository.findById(1).orElseThrow(() -> new ServiceException("服务器出错！"));
    }

    public void savePlan(PlanDomain planDomain) {
        // 保存计划
        PlanEntity planEntity = repository.findById(planDomain.getId()).orElse(new PlanEntity());
        BeanUtils.copyProperties(planDomain,planEntity);
        repository.save(planEntity);
    }


   /* *//**
     * 保存计划信息
     *//*
    public void savePlan(PlanDomain planDomain) {
        // 保存计划
        PlanEntity planEntity = repository.findById(planDomain.getId()).orElse(new PlanEntity());
        BeanUtils.copyProperties(planDomain,planEntity);
        // 保存后获取自增的主键
        PlanEntity afterSave = repository.save(planEntity);

        //保存计划详情
        List<PlanInfoEntity> planInfoEntities = planDomain.getPlanInfoList().stream().map(item -> {
            PlanInfoEntity planInfoEntity = new PlanInfoEntity();
            BeanUtils.copyProperties(item, planInfoEntity);
            planInfoEntity.setPlanId(afterSave.getId());
            return planInfoEntity;
        }).collect(Collectors.toList());

        planInfoRepository.saveAll(planInfoEntities);

    }*/

    /**
     * 删除计划信息
     * @param id
     */
    public void deletePlan(int id) {
        // 删除计划
        repository.deleteById(id);
        // 删除计划详情
        planInfoRepository.deleteAllByPlanId(id);
    }
}
