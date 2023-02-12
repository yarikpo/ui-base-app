package ua.clamor1s.task911.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@AllArgsConstructor
@Jacksonized
public class CardAllInfoDto {

    private List<CardInfoDto> cards;

}
