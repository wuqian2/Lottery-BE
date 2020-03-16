package com.sxnsyh.lottery.service;

import com.sxnsyh.lottery.domain.PrizeDomain;
import com.sxnsyh.lottery.entity.PrizeEntity;
import com.sxnsyh.lottery.entity.PrizeInfoEntity;
import com.sxnsyh.lottery.repository.PrizeInfoRepository;
import com.sxnsyh.lottery.repository.PrizeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrizeService {
    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PrizeInfoRepository prizeInfoRepository;



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
        prizeInfoRepository.deleteAllByPrizeId(id);
    }



    public List<PrizeInfoEntity> getPrizeInfoList(int prizeId) {
        return prizeInfoRepository.findAllByPrizeId(prizeId);
    }

    /**
     * 保存礼物信息
     */
    public void savePrizeInfo(List<PrizeInfoEntity> prizeDomain) {
        prizeInfoRepository.saveAll(prizeDomain);
    }

    /**
     * 删除礼物信息
     * @param id
     */
    public void deletePrizeInfo(int id) {
        prizeInfoRepository.deleteById(id);
    }
}
