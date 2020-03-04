package com.sxnsyh.lottery.service;

import com.sxnsyh.lottery.domain.PrizeDomain;
import com.sxnsyh.lottery.entity.PrizeEntity;
import com.sxnsyh.lottery.repository.PrizeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    PrizeRepository prizeRepository;

    public List<PrizeEntity> getPrizeList() {
        return prizeRepository.findAll();
    }

    /**
     * 保存礼物信息
     */
    public void savePrize(PrizeDomain prizeDomain) {
        PrizeEntity prizeEntity = prizeRepository.findById(prizeDomain.getId()).orElse(new PrizeEntity());
        BeanUtils.copyProperties(prizeDomain,prizeEntity);
        prizeRepository.save(prizeEntity);
    }

    /**
     * 删除礼物信息
     * @param id
     */
    public void deletePrize(int id) {
        prizeRepository.deleteById(id);
    }
}
