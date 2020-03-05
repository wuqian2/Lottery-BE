package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.domain.PlanInfoDomain;
import com.sxnsyh.lottery.entity.PlanInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoEntity,Integer> {

    @Query("select i.id,i.prizeId,i.amountLimit,i.planCount,i.planId,i.quantityLimit,i.sendedCount,p.prizeName" +
            " from PlanInfoEntity i,PrizeEntity p where i.prizeId = p.id")
    Object[] queryPlanInfo(int planId);

}
