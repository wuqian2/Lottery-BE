package com.sxnsyh.lottery.controller;

import com.sxnsyh.lottery.common.Result;
import com.sxnsyh.lottery.common.ResultGenerator;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.domain.PlanDomain;
import com.sxnsyh.lottery.service.CommonService;
import com.sxnsyh.lottery.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 其他模块通用controller
 * @author wuqian
 */
@RestController
@RequestMapping("common")
public class CommonController {
    @Autowired
    CommonService service;

    @GetMapping("/customer")
    public Result getCustomerList(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return ResultGenerator.genSuccessResult(service.getCustomerList(pageIndex, pageSize));
    }

    @GetMapping("/record")
    public Result getRecordList() {
        return ResultGenerator.genSuccessResult(service.getRecordList());
    }


    @RequestMapping("/upload")
    public Result uploadFile(@RequestParam("file")MultipartFile file) {
        this.service.readExcel(file);
        return ResultGenerator.genSuccessResult();
    }




    /**
     * 下载excel导入文档
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/download")
    public String downloadFile(HttpServletResponse response) throws UnsupportedEncodingException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/templates/手机银行交易数据200303.xlsx");
        // 如果文件名存在，则进行下载
        if (resource.exists()) {

            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(resource.getFilename(), "UTF-8"));

            // 实现文件下载
            byte[] buffer = new byte[1024];
            InputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = resource.getInputStream();
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            } catch (Exception e) {
                throw new ServiceException("文件模板下载失败！");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        throw new ServiceException("文件流关闭错误！");
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        throw new ServiceException("文件流关闭错误！");
                    }
                }
            }
        }

        return null;
    }

}
