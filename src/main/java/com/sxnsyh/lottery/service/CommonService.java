package com.sxnsyh.lottery.service;

import com.alibaba.excel.EasyExcel;
import com.sxnsyh.lottery.common.CustomerExcelReaderListener;
import com.sxnsyh.lottery.common.ServiceException;
import com.sxnsyh.lottery.domain.PageDomain;
import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.entity.RecordEntity;
import com.sxnsyh.lottery.repository.CustomerRepository;
import com.sxnsyh.lottery.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CommonService {
    @Autowired
    CustomerRepository customerRepositoryRepository;

    @Autowired
    RecordRepository recordRepository;

    /**
     * 获取中奖记录列表
     * @return
     */
    public List<RecordEntity> getRecordList() {
        return recordRepository.findAll(Sort.by("winningDate"));
    }


    /**
     * 分页获取客户列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageDomain<CustomerEntity> getCustomerList(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        Page<CustomerEntity> customers = customerRepositoryRepository.findAll(pageable);

        // 构造分页对象
        PageDomain pageDomain = new PageDomain();
        pageDomain.setDataSet(customers.getContent());
        pageDomain.setTotal(customers.getTotalElements());
        return pageDomain;

    }


    /**
     * 客户信息导入
     * @param file
     */
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
