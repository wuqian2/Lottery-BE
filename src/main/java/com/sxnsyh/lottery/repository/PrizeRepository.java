package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PrizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PrizeRepository extends JpaRepository<PrizeEntity,Integer> {

    @Query(value = "select  i.*,p.prize_level,p.prize_name from plan_info i,prize p where i.prize_id =p.id " +
            "AND plan_id = ?1 " +
            "AND i.plan_count - sended_count >0 " +
            "AND ?2 > i.quantity_limit " +
            "AND ?3 > i.amount_limit " +
            "ORDER BY p.prize_level DESC Limit 1",nativeQuery = true)
    Map<String, Object> getLotteryPrize(int planId,int quantity,double amount);


    @Modifying
    @Query("update PrizeEntity p set p.sentedPrize = p.sentedPrize+1 where p.id =?1")
    void updateSendPrizeCount(int id);
}
