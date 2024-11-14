package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.LogDAO;
import lk.ijse.crop_monitoring_systembackend.dto.LogDTO;
import lk.ijse.crop_monitoring_systembackend.entity.LogEntity;
import lk.ijse.crop_monitoring_systembackend.exception.NotFoundException;
import lk.ijse.crop_monitoring_systembackend.service.LogService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LogServiceIMPL implements LogService {
    @Autowired
    private LogDAO logDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveLog(LogDTO log) {
        log.setLogId(generateLogID());
        log.setDate(LocalDate.now());
        logDAO.save(mappingUtil.logConvertToEntity(log));
        System.out.println("Log saved successfully: " + log);
    }

    @Override
    public void updateLog(String id, LogDTO log) {
        Optional<LogEntity> tmpLogEntity = logDAO.findById(id);
        if (tmpLogEntity.isPresent()) {
            LogEntity logEntity = mappingUtil.logConvertToEntity(log);
            tmpLogEntity.get().setDetails(logEntity.getDetails());
            tmpLogEntity.get().setTemperature(logEntity.getTemperature());
            tmpLogEntity.get().setObservedImg(logEntity.getObservedImg());
            tmpLogEntity.get().setField(logEntity.getField());
            tmpLogEntity.get().setCrop(logEntity.getCrop());
        } else {
            System.out.println("Log not found with id: " + id);
            throw new NotFoundException("Log not found with id: " + id);
        }
    }

    @Override
    public LogDTO searchLog(String id) {
        return null;
    }

    @Override
    public boolean deleteLog(String id) {
        return false;
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return List.of();
    }

    private String generateLogID() {
        if (logDAO.count() == 0) {
            return "L001";
        } else {
            String lastId = logDAO.findAll().get(logDAO.findAll().size() - 1).getLogId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "L00" + newId;
            } else if (newId < 100) {
                return "L0" + newId;
            } else {
                return "L" + newId;
            }
        }
    }
}
