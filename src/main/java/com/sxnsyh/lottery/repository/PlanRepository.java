package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {
}
