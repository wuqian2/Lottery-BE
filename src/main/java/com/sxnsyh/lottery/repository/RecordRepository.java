package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,Integer> {
}
