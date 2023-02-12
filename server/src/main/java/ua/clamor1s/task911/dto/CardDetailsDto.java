package ua.clamor1s.task911.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class CardDetailsDto {

    private int cardId;

    private String name;

    private String surname;

    private String code;

    private Integer cvv;

    private Date creationDate;

}
