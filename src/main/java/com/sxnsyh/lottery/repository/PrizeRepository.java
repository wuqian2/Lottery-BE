package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PrizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PrizeRepository extends JpaRepository<PrizeEntity,Integer> {

 /*   @Query(value = "select  i.*,p.prize_level,p.prize_name from plan_info i,prize p where i.prize_id =p.id " +
            "AND plan_id = ?1 " +
            "AND i.plan_count - sended_count >0 " +
            "AND ?2 > i.quantity_limit " +
            "AND ?3 > i.amount_limit " +
            "ORDER BY p.prize_level DESC Limit 1",nativeQuery = true)
    Map<String, Object> getLotteryPrize(int planId,int quantity,double amount);*/

    @Query(value = "select i.*,p.prize_name from  prize_info i, prize p WHERE i.prize_id = p.id " +
            " AND ?1 > p.amount_limit AND ?2> p.quantity_limit" +
            " AND i.count - i.sended > 0 AND " +
            " ?3 BETWEEN i.begin_date AND i.end_date",nativeQuery = true)
    List<Map<String, Object>> getLotteryPrize(double amount, int quantity, Date date);


    @Modifying
    @Query("update PrizeEntity p set p.sentedPrize = p.sentedPrize+1 where p.id =?1")
    void updateSendPrizeCount(int id);
}
