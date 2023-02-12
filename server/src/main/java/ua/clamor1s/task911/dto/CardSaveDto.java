package ua.clamor1s.task911.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CardSaveDto {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Surname is required.")
    private String surname;

    @NotNull(message = "Mustn't be null.")
    private String code;

    @NotNull(message = "Mustn't be null.")
    private Integer cvv;

    @NotNull(message = "Date is required.")
    private Date creationDate;

}
