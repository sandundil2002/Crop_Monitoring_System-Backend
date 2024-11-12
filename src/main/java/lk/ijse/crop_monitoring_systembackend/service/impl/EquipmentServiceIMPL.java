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
}
