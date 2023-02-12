package ua.clamor1s.task911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.clamor1s.task911.dao.CardDao;
import ua.clamor1s.task911.dao.OperationDao;
import ua.clamor1s.task911.dto.CardSaveDto;
import ua.clamor1s.task911.dto.OperationAllInfoDto;
import ua.clamor1s.task911.dto.OperationDetailsDto;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Card;
import ua.clamor1s.task911.model.Operation;

import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao dao;
    @Autowired
    private CardDao cardDao;


    @Override
    public void deleteOperation(int id) {
        dao.deleteOperation(id);
    }

    @Override
    public void updateOperation(int id, OperationSaveDto dto) {
        Operation operation = fromSaveDtoToModel(dto);
        operation.setOperationId(id);
        dao.updateOperation(operation);
    }

    @Override
    public int saveOperation(OperationSaveDto dto) {
        return dao.saveOperation(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public OperationDetailsDto findOperationById(int id) {
        return dao.findOperationById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public OperationAllInfoDto findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    private Operation fromSaveDtoToModel(OperationSaveDto cardDto) {
        return Operation.builder()
                .card(cardDao.findCard(cardDto.getCardId()))
                .amount(cardDto.getAmount())
                .operationDatetime(cardDto.getOperationDatetime())
                .build();
    }
}
