package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {

    @Query("select p from PlanEntity p where ?1 between p.beginDate and p.endDate")
    Optional<PlanEntity> findPlanBydate(Date date);
}
