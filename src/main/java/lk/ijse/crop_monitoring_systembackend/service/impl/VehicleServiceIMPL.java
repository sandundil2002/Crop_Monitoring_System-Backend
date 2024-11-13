package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.VehicleDAO;
import lk.ijse.crop_monitoring_systembackend.dto.VehicleDTO;
import lk.ijse.crop_monitoring_systembackend.service.VehicleService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveVehicle(VehicleDTO vehicle) {
        vehicle.setVehicleId(generateVehicleID());
        vehicleDAO.save(mappingUtil.vehicleConvertToEntity(vehicle));
        System.out.println("Vehicle saved successfully: " + vehicle);
    }

    @Override
    public void updateVehicle(String id, VehicleDTO vehicle) {

    }

    @Override
    public VehicleDTO searchVehicle(String id) {
        return null;
    }

    @Override
    public boolean deleteVehicle(String id) {
        return false;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return List.of();
    }

    private String generateVehicleID() {
        if (vehicleDAO.count() == 0) {
            return "V001";
        } else {
            String id = vehicleDAO.findAll().get(vehicleDAO.findAll().size() - 1).getVehicleId();
            int i = Integer.parseInt(id.replace("V", "")) + 1;
            if (i < 10) {
                return "V00" + i;
            } else if (i < 100) {
                return "V0" + i;
            } else {
                return "V" + i;
            }
        }
    }
}
