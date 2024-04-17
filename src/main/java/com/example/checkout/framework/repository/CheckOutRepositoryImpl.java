package com.example.checkout.framework.repository;

import com.example.checkout.application.outputport.CheckOutRepository;
import com.example.checkout.domain.CheckOutEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CheckOutRepositoryImpl implements CheckOutRepository {
    private final CheckOutJpaRepository checkOutJpaRepository;
    @Override
    public CheckOutEntity save(CheckOutEntity checkOutEntity) {
        return checkOutJpaRepository.save(checkOutEntity);
    }
}
