package com.sxnsyh.lottery.controller;

import com.sxnsyh.lottery.common.Result;
import com.sxnsyh.lottery.common.ResultGenerator;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.service.CommonService;
import com.sxnsyh.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 抽奖页面
 *
 * @author wuqian
 */
@RestController
@RequestMapping("lottery")
public class LotteryController {
    @Autowired
    LotteryService service;


    /**
     * 登录
     * @param phone
     * @param certNO
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("phone") String phone, @RequestParam("certNo") String certNO) {
        return ResultGenerator.genSuccessResult(service.login(phone, certNO));
    }


    /**
     * 获取客户剩余次数
     * @param id 客户编号
     * @return
     */
    @GetMapping("/getCountById/{id}")
    public Result getCountById(@PathVariable int id) {
        return ResultGenerator.genSuccessResult(service.getCount(id));
    }


    /**
     * 获取礼物列表
     * @return
     */
    @GetMapping("/prizes")
    public Result getPrize() {
        return ResultGenerator.genSuccessResult(service.getPrize());
    }


    /**
     * 获取礼物列表
     * @param id 客户编号
     * @return
     */
    @GetMapping("/doLottery")
    public Result doLottery(@RequestParam("id") int id) {
        return ResultGenerator.genSuccessResult(service.doLottery(id));
    }
}
