package ua.clamor1s.task911.service;

import ua.clamor1s.task911.dto.OperationAllInfoDto;
import ua.clamor1s.task911.dto.OperationDetailsDto;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Operation;

import java.util.List;

public interface OperationService {

    void deleteOperation(int id);

    void updateOperation(int id, OperationSaveDto dto);

    int saveOperation(OperationSaveDto operationDto);

    OperationDetailsDto findOperationById(int id);

    OperationAllInfoDto findAll();

    void deleteAll();

}
