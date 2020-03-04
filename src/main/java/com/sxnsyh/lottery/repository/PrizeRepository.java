package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.PrizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends JpaRepository<PrizeEntity,Integer> {
}
