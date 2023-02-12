package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.dto.OperationAllInfoDto;
import ua.clamor1s.task911.dto.OperationDetailsDto;
import ua.clamor1s.task911.dto.OperationInfoDto;
import ua.clamor1s.task911.dto.OperationSaveDto;
import ua.clamor1s.task911.model.Operation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationDao {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CardDao cardDao;

    public int saveOperation(OperationSaveDto dto) {
        Operation operation = saveDto2Operation(dto);
        entityManager.persist(operation);
        return operation.getOperationId();
    }

    public void updateOperation(Operation operation) {
        entityManager.merge(operation);
    }

    public void deleteOperation(int operationId) {
        int isSuccess = entityManager.createQuery("DELETE FROM Operation o WHERE o.operationId=:id")
                .setParameter("id", operationId)
                .executeUpdate();

        if (isSuccess == 0) {
            throw new RuntimeException("Troubles with deleting operation.");
        }
    }

    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Operation o").executeUpdate();
    }

    public OperationDetailsDto findOperationById(int id) {
        Operation operation = entityManager.find(Operation.class, id);
        return operation2OperationDetails(operation);
    }

    public OperationAllInfoDto findAll() {
        return new OperationAllInfoDto(operationList2OperationInfoList(entityManager.createQuery("SELECT o FROM Operation o", Operation.class).getResultList()));
    }

    private OperationInfoDto operation2OperationInfo(Operation operation) {
        return OperationInfoDto.builder()
                .operationId(operation.getOperationId())
                .cardId(operation.getCard().getCardId())
                .amount(operation.getAmount())
                .build();
    }

    private List<OperationInfoDto> operationList2OperationInfoList(List<Operation> operations) {
        List<OperationInfoDto> dtos = new ArrayList<>();
        for (Operation operation : operations) {
            dtos.add(operation2OperationInfo(operation));
        }
        return dtos;
    }

    private Operation saveDto2Operation(OperationSaveDto dto) {
        return Operation.builder()
                .card(cardDao.findCard(dto.getCardId()))
                .amount(dto.getAmount())
                .operationDatetime(dto.getOperationDatetime())
                .build();
    }

    private OperationDetailsDto operation2OperationDetails(Operation operation) {
        return OperationDetailsDto.builder()
                .operationId(operation.getOperationId())
                .card(cardDao.card2CardDetails(operation.getCard()))
                .amount(operation.getAmount())
                .operationDatetime(operation.getOperationDatetime())
                .build();
    }

}
