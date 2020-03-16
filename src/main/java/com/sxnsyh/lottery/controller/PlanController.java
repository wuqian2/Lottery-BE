package com.sxnsyh.lottery.controller;

import com.sxnsyh.lottery.common.Result;
import com.sxnsyh.lottery.common.ResultGenerator;
import com.sxnsyh.lottery.domain.PlanDomain;
import com.sxnsyh.lottery.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 计划管理controller
 * @author wuqian
 */
@RestController
@RequestMapping("admin")
public class PlanController {
    @Autowired
    PlanService service;

    @GetMapping("/plan")
    public Result getPlan() {
        return ResultGenerator.genSuccessResult(service.getPlan());
    }

    @PostMapping("/plan")
    public Result savePlan(@RequestBody PlanDomain planDomain) {
        service.savePlan(planDomain);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/plan/{id}")
    public Result deletePlan(@PathVariable int id) {
        service.deletePlan(id);
        return ResultGenerator.genSuccessResult();
    }

}
