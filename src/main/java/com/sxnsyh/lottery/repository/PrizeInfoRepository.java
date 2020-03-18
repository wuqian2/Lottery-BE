package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PrizeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PrizeInfoRepository extends JpaRepository<PrizeInfoEntity,Integer> {

    List<PrizeInfoEntity> findAllByPrizeId(int prizeId);

    void deleteAllByPrizeId(int prizeId);


    @Modifying
    @Query("update PrizeInfoEntity p set p.sended = p.sended+1 where p.id = ?1")
    void updateSendedCount(int id);

}
