package ua.clamor1s.task911.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;

@Builder
@Getter
@Jacksonized
public class OperationDetailsDto {

    private int operationId;

    private CardDetailsDto card;

    private Integer amount;

    private Timestamp operationDatetime;

}
