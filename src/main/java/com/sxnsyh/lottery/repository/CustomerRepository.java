package com.sxnsyh.lottery.repository;

import com.sxnsyh.lottery.entity.CustomerEntity;
import com.sxnsyh.lottery.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Modifying
    @Query("update CustomerEntity c set c.remainingCount = ?1")
    int remainingCount(int count);

    CustomerEntity findFirstByPhoneNoAndCertNo(String phoneNo, String certNo);

    @Query(value = "select max(c.transaction_count)-min(c.transaction_count ) from customer c ", nativeQuery = true)
    int getCountDif();
}
