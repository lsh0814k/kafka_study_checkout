package com.example.checkout.application.outputport;

import com.example.checkout.domain.CheckOutEntity;

public interface CheckOutRepository {
    CheckOutEntity save(CheckOutEntity checkOutEntity);
}
