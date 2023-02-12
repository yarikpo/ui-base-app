package ua.clamor1s.task911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.clamor1s.task911.dto.OperationAllInfoDto;
import ua.clamor1s.task911.dto.OperationDetailsDto;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.dto.RestResponse;
import ua.clamor1s.task911.service.OperationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createOperation(@Valid @RequestBody OperationSaveDto dto) {
        int id = operationService.saveOperation(dto);
        return new RestResponse(String.valueOf(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationDetailsDto getOperationById(@PathVariable int id) {
        return operationService.findOperationById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OperationAllInfoDto findAll() {
        return operationService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateOperationById(@PathVariable int id, @Valid @RequestBody OperationSaveDto dto) {
        operationService.updateOperation(id, dto);
        return new RestResponse("OK");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteOperationById(@PathVariable int id) {
        operationService.deleteOperation(id);
        return new RestResponse("OK");
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteAll() {
        operationService.deleteAll();
        return new RestResponse("OK");
    }

}
