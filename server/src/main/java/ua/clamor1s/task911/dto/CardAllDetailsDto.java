package ua.clamor1s.task911.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import ua.clamor1s.task911.model.Card;

import java.util.List;

@Getter
@AllArgsConstructor
@Jacksonized
public class CardAllDetailsDto {

    List<CardDetailsDto> cards;

}
