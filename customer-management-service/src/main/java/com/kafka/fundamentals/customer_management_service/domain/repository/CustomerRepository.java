package com.kafka.fundamentals.customer_management_service.domain.repository;


import com.kafka.fundamentals.customer_management_service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}