package ua.clamor1s.task911.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@AllArgsConstructor
@Jacksonized
@Getter
public class OperationAllInfoDto {

    private List<OperationInfoDto> operations;

}
