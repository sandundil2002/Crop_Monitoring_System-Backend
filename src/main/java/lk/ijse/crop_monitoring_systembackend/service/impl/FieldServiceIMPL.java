package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.FieldDAO;
import lk.ijse.crop_monitoring_systembackend.dto.FieldDTO;
import lk.ijse.crop_monitoring_systembackend.service.FieldService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveField(FieldDTO field) {
        field.setFieldId(generateFieldID());
        fieldDAO.save(mappingUtil.fieldConvertToEntity(field));
        System.out.println("Field saved successfully: " + field);
    }

    @Override
    public void updateField(String id, FieldDTO field) {

    }

    @Override
    public FieldDTO searchField(String id) {
        return null;
    }

    @Override
    public boolean deleteField(String id) {
        return false;
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return List.of();
    }

    private String generateFieldID() {
        if (fieldDAO.count() == 0) {
            return "F001";
        } else {
            String lastId = fieldDAO.findAll().get(fieldDAO.findAll().size() - 1).getFieldId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "F00" + newId;
            } else if (newId < 100) {
                return "F0" + newId;
            } else {
                return "F" + newId;
            }
        }
    }
}
