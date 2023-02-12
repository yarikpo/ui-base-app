package ua.clamor1s.task911.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CardQueryDto {

    private String name;

    private String surname;

    private String code;

    private Integer cvv;

    private Date creationDate;

}
