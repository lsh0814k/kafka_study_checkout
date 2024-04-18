package com.example.checkout.application;

import com.example.checkout.application.outputport.CheckOutRepository;
import com.example.checkout.domain.CheckOutEntity;
import com.example.checkout.framework.web.dto.CheckOutDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SaveService {
    private static final String CHECKOUT_COMPLETE_TOPIC_NAME = "checkout.complete.v1";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CheckOutRepository checkOutRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Long saveCheckOutData(CheckOutDto checkOutDto) {
        CheckOutEntity entity = saveDatabase(checkOutDto);

        CheckOutDto newCheckOutDto = createNewCheckOutDto(checkOutDto, entity);
        sentToKafka(newCheckOutDto);

        return entity.getCheckOutId();
    }

    private void sentToKafka(CheckOutDto newCheckOutDto) {
        try {
            String jsonInString = objectMapper.writeValueAsString(newCheckOutDto);
            kafkaTemplate.send(CHECKOUT_COMPLETE_TOPIC_NAME, jsonInString);
            log.info("success sendToKafka");
        } catch (JsonProcessingException e) {
            log.error("sendToKafka ", e);
        }
    }

    private CheckOutDto createNewCheckOutDto(CheckOutDto checkOutDto, CheckOutEntity entity) {
        return CheckOutDto.builder()
                .checkOutId(entity.getCheckOutId())
                .createdAt(entity.getCreatedAt())
                .memberId(checkOutDto.getMemberId())
                .productId(checkOutDto.getProductId())
                .amount(checkOutDto.getAmount())
                .build();
    }

    private CheckOutEntity saveDatabase(CheckOutDto checkOutDto) {
        CheckOutEntity entity =CheckOutEntity.builder()
                .memberId(checkOutDto.getMemberId())
                .productId(checkOutDto.getProductId())
                .amount(checkOutDto.getAmount())
                .shippingAddress(checkOutDto.getShippingAddress())
                .build();

        return checkOutRepository.save(entity);
    }
}
