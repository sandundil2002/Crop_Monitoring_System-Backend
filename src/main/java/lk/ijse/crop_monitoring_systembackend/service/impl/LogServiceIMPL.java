package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.LogDAO;
import lk.ijse.crop_monitoring_systembackend.dto.LogDTO;
import lk.ijse.crop_monitoring_systembackend.service.LogService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
