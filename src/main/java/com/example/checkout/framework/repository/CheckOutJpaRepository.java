package com.example.checkout.framework.repository;

import com.example.checkout.domain.CheckOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutJpaRepository extends JpaRepository<CheckOutEntity, Long> {

}
