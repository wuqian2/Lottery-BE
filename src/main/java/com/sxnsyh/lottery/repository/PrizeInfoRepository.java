package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PrizeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeInfoRepository extends JpaRepository<PrizeInfoEntity,Integer> {

    List<PrizeInfoEntity> findAllByPrizeId(int prizeId);

    void deleteAllByPrizeId(int prizeId);
}
