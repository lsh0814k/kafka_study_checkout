package com.example.checkout.framework.web;

import com.example.checkout.framework.web.dto.CheckOutDto;
import com.example.checkout.application.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckOutSubmitController {
    private final SaveService saveService;

    @PostMapping
    public ResponseEntity submitCheckOut(@RequestBody CheckOutDto checkOutDto) {
        log.info(checkOutDto.toString());
        Long checkOutId = saveService.saveCheckOutData(checkOutDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
