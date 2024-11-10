package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.StaffDAO;
import lk.ijse.crop_monitoring_systembackend.dto.StaffDTO;
import lk.ijse.crop_monitoring_systembackend.entity.StaffEntity;
import lk.ijse.crop_monitoring_systembackend.service.StaffService;
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
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveStaff(StaffDTO staff) {
        staff.setStaffId(generateStaffID());
        staff.setJoinedDate(LocalDate.now());
        StaffEntity staffEntity = mappingUtil.staffConvertToEntity(staff);
        staffDAO.save(staffEntity);
        System.out.println("Staff saved successfully: " + staffEntity);
    }

    @Override
    public void updateStaff(StaffDTO staffDTO) {

    }

    @Override
    public void deleteStaff(String staffId) {

    }

    @Override
    public StaffDTO searchStaff(String staffId) {
        return null;
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return List.of();
    }

    private String generateStaffID() {
        if (staffDAO.count() == 0) {
            return "S001";
        } else {
            String lastId = staffDAO.findAll().get(staffDAO.findAll().size() - 1).getStaffId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "S00" + newId;
            } else if (newId < 100) {
                return "S0" + newId;
            } else {
                return "S" + newId;
            }
        }
    }
}
