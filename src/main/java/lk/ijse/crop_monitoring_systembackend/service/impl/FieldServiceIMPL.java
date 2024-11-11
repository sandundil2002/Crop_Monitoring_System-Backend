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
}
