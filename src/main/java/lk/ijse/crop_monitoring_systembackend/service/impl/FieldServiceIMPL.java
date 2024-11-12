package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.FieldDAO;
import lk.ijse.crop_monitoring_systembackend.dto.FieldDTO;
import lk.ijse.crop_monitoring_systembackend.entity.FieldEntity;
import lk.ijse.crop_monitoring_systembackend.exception.NotFoundException;
import lk.ijse.crop_monitoring_systembackend.service.FieldService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    }

    @Override
    public void updateField(String id, FieldDTO field) {
        Optional<FieldEntity> tmpFieldEntity = fieldDAO.findById(id);
        if (tmpFieldEntity.isPresent()) {
            FieldEntity fieldEntity = mappingUtil.fieldConvertToEntity(field);
            tmpFieldEntity.get().setFieldName(fieldEntity.getFieldName());
            tmpFieldEntity.get().setLocation(fieldEntity.getLocation());
            tmpFieldEntity.get().setSize(fieldEntity.getSize());
            tmpFieldEntity.get().setFieldImg1(fieldEntity.getFieldImg1());
            tmpFieldEntity.get().setFieldImg2(fieldEntity.getFieldImg2());
        } else {
            throw new NotFoundException("Field not found with id: " + id);
        }
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
