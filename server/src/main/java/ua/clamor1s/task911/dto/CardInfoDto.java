package ua.clamor1s.task911.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CardInfoDto {

    private int cardId;

    private String name;

    private String surname;



}
