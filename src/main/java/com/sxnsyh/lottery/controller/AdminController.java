package com.sxnsyh.lottery.controller;

import com.sxnsyh.lottery.common.Result;
import com.sxnsyh.lottery.common.ResultGenerator;
import com.sxnsyh.lottery.domain.PrizeDomain;
import com.sxnsyh.lottery.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理controller
 * @author wuqian
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService service;

    @PostMapping("/prize")
    public Result savePrize(@RequestBody PrizeDomain prizeDomain) {
        service.savePrize(prizeDomain);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/prize/{id}")
    public Result deletePrize(@PathVariable int id) {
        service.deletePrize(id);
        return ResultGenerator.genSuccessResult();
    }

}
