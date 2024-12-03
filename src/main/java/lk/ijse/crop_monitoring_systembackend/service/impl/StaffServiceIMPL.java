package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.StaffDAO;
import lk.ijse.crop_monitoring_systembackend.dao.VehicleDAO;
import lk.ijse.crop_monitoring_systembackend.dto.StaffDTO;
import lk.ijse.crop_monitoring_systembackend.entity.StaffEntity;
import lk.ijse.crop_monitoring_systembackend.entity.VehicleEntity;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.exception.NotFoundException;
import lk.ijse.crop_monitoring_systembackend.service.StaffService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveStaff(StaffDTO staff) {
        staff.setStaffId(generateStaffID());
        staff.setJoinedDate(LocalDate.now());
        saveVehicleStatus(staff.getVehicleId());
        StaffEntity staffEntity = mappingUtil.staffConvertToEntity(staff);
        staffDAO.save(staffEntity);
        System.out.println("Staff saved successfully: " + staffEntity);
    }

    @Override
    @Transactional
    public void updateStaff(String id, StaffDTO staff) {
        StaffEntity reference = staffDAO.getReferenceById(id);
        if (!Objects.equals(reference.getVehicleId().getVehicleId(), staff.getVehicleId())) {
            if (reference.getVehicleId() != null) {
                updateVehicleStatus(reference.getVehicleId().getVehicleId(), "Available");
            }

            if (staff.getVehicleId() != null) {
                if (vehicleDAO.isVehicleAssigned(staff.getVehicleId())) {
                    throw new DataPersistFailedException("Vehicle already assigned to another staff");
                }
                updateVehicleStatus(staff.getVehicleId(), "Assigned");
            }
        }

        Optional<StaffEntity> tmpStaffEntity = staffDAO.findById(id);
        if (tmpStaffEntity.isPresent()) {
            StaffEntity staffEntity = mappingUtil.staffConvertToEntity(staff);
            StaffEntity existingStaff = tmpStaffEntity.get();
            existingStaff.setFirstName(staffEntity.getFirstName());
            existingStaff.setLastName(staffEntity.getLastName());
            existingStaff.setDesignation(staffEntity.getDesignation());
            existingStaff.setGender(staffEntity.getGender());
            existingStaff.setDateOfBirth(staffEntity.getDateOfBirth());
            existingStaff.setAddressLine1(staffEntity.getAddressLine1());
            existingStaff.setAddressLine2(staffEntity.getAddressLine2());
            existingStaff.setAddressLine3(staffEntity.getAddressLine3());
            existingStaff.setAddressLine4(staffEntity.getAddressLine4());
            existingStaff.setAddressLine5(staffEntity.getAddressLine5());
            existingStaff.setMobile(staffEntity.getMobile());
            existingStaff.setEmail(staffEntity.getEmail());
            existingStaff.setRole(staffEntity.getRole());
            existingStaff.setVehicleId(staffEntity.getVehicleId());
            System.out.println("Staff updated successfully: " + existingStaff);
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
    }

    @Override
    public StaffDTO searchStaff(String id) {
        if (staffDAO.existsById(id)) {
            return mappingUtil.staffConvertToDTO(staffDAO.getReferenceById(id));
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return mappingUtil.staffConvertToDTOList(staffDAO.findAll());
    }

    @Override
    public boolean deleteStaff(String id) {
        if (staffDAO.existsById(id)) {
            staffDAO.deleteById(id);
            System.out.println("Staff deleted successfully: " + id);
            return true;
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
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

    private void saveVehicleStatus(String vehicleId) {
        VehicleEntity vehicle = vehicleDAO.getReferenceById(vehicleId);
        if (!vehicle.getStatus().equals("Assigned")) {
            vehicleDAO.updateVehicleStatus(vehicleId, "Assigned");
        } else {
            throw new DataPersistFailedException("Failed to update vehicle status");
        }
    }

    private void updateVehicleStatus(String vehicleId, String desiredStatus) {
        VehicleEntity vehicle = vehicleDAO.getReferenceById(vehicleId);
        if (!vehicle.getStatus().equals(desiredStatus)) {
            vehicleDAO.updateVehicleStatus(vehicleId, desiredStatus);
            System.out.println("Vehicle status updated to " + desiredStatus);
        }
    }
}
