package com.example.data.repository;

import com.example.data.domain.CustomerOrder;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

    @Query("SELECT * FROM customer_order WHERE status = :status")
    List<CustomerOrder> findByStatus(@Param("status") String status);
}