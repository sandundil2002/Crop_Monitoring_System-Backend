package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.EquipmentDAO;
import lk.ijse.crop_monitoring_systembackend.dto.EquipmentDTO;
import lk.ijse.crop_monitoring_systembackend.service.EquipmentService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveEquipment(EquipmentDTO equipment) {
        equipment.setEquipmentId(generateEquipmentID());
        equipmentDAO.save(mappingUtil.equipmentConvertToEntity(equipment));
        System.out.println("Equipment saved successfully: " + equipment);
    }

    @Override
    public void updateEquipment(String id, EquipmentDTO equipment) {

    }

    @Override
    public EquipmentDTO getEquipmentById(String id) {
        return null;
    }

    @Override
    public boolean deleteEquipment(String id) {
        return false;
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return List.of();
    }

    private String generateEquipmentID() {
        if (equipmentDAO.count() == 0) {
            return "E001";
        } else {
            String lastId = equipmentDAO.findAll().get(equipmentDAO.findAll().size() - 1).getEquipmentId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "E00" + newId;
            } else if (newId < 100) {
                return "E0" + newId;
            } else {
                return "E" + newId;
            }
        }
    }
}
