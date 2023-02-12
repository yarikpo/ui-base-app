package ua.clamor1s.task911.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class OperationInfoDto {

    private Integer operationId;

    private Integer cardId;

    private int amount;

}
