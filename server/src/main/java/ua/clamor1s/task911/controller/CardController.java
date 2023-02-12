package ua.clamor1s.task911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.clamor1s.task911.dto.*;
import ua.clamor1s.task911.service.CardService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createCard(@Valid @RequestBody CardSaveDto dto) {
        int id = cardService.saveCard(dto);
        return new RestResponse(String.valueOf(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDetailsDto getCardById(@PathVariable int id) {
        return cardService.getCard(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CardAllDetailsDto getAllCards() {
        return cardService.listAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateCardById(@PathVariable int id, @Valid @RequestBody CardSaveDto dto) {
        cardService.updateCard(id, dto);
        return new RestResponse("OK");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteCardById(@PathVariable int id) {
        cardService.deleteCard(id);
        return new RestResponse("OK");
    }

    @PostMapping("/_search/{page}")
    @ResponseStatus(HttpStatus.OK)
    public CardAllInfoDto searchPagination(@PathVariable int page, @RequestBody CardQueryDto query) {
        return cardService.search(page, query);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteAll() {
        cardService.deleteAll();
        return new RestResponse("OK");
    }



}
