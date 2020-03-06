package com.sxnsyh.lottery.service;

import com.alibaba.excel.EasyExcel;
import com.sxnsyh.lottery.common.CustomerExcelReaderListener;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.domain.PageDomain;
import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@Slf4j
public class CommonService {
    @Autowired
    CustomerRepository customerRepositoryRepository;

    public PageDomain<CustomerEntity> getCustomerList(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        Page<CustomerEntity> customers = customerRepositoryRepository.findAll(pageable);

        // 构造分页对象
        PageDomain pageDomain = new PageDomain();
        pageDomain.setDataSet(customers.getContent());
        pageDomain.setTotal(customers.getTotalElements());
        return pageDomain;

    }

    public void readExcel(MultipartFile file) {
        // 先删除所有记录
        customerRepositoryRepository.deleteAll();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        try {
            EasyExcel.read(file.getInputStream(), CustomerEntity.class, new CustomerExcelReaderListener(customerRepositoryRepository)).sheet().doRead();
        } catch (IOException e) {
            log.error("导入文件出错"+e);
            throw new ServiceException("导入文件出错!!");
        }
    }
}
