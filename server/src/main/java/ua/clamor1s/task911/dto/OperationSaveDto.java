package ua.clamor1s.task911.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import ua.clamor1s.task911.model.Card;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class OperationSaveDto {

    @NotNull(message = "Card can't be null.")
    private Integer cardId;

    @NotNull(message = "Amount can't be null.")
    private Integer amount;


    private Timestamp operationDatetime;

}
