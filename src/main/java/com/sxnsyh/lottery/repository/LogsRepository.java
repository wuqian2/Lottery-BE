package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.LogsEntity;
import com.sxnsyh.lottery.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<LogsEntity,Integer> {
}
