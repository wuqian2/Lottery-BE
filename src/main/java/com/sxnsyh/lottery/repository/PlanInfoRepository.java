package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.domain.PlanInfoDomain;
import com.sxnsyh.lottery.entity.PlanInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoEntity,Integer> {

    @Query("select  new com.sxnsyh.lottery.domain.PlanInfoDomain(i.id,i.prizeId,p.prizeName,p.prizeLevel,i.planCount,i.sendedCount,i.quantityLimit,i.amountLimit)" +
            " from PlanInfoEntity i,PrizeEntity p where i.prizeId = p.id and i.planId = ?1")
    List<PlanInfoDomain> queryPlanInfo(int planId);

    void deleteAllByPlanId(int planId);

}
