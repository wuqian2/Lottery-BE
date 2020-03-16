package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.domain.PlanInfoDomain;
import com.sxnsyh.lottery.entity.PlanInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoEntity,Integer> {

    @Query("select  new com.sxnsyh.lottery.domain.PlanInfoDomain(i.id,i.prizeId,p.prizeName,p.prizeLevel,i.planCount,i.sendedCount,i.quantityLimit,i.amountLimit)" +
            " from PlanInfoEntity i,PrizeEntity p where i.prizeId = p.id and i.planId = ?1")
    List<PlanInfoDomain> queryPlanInfo(int planId);

    void deleteAllByPlanId(int planId);


    @Modifying
    @Query("update PlanInfoEntity p set p.sendedCount = p.sendedCount+1 where p.id = ?1")
    void updateSendedCount(int id);

}
