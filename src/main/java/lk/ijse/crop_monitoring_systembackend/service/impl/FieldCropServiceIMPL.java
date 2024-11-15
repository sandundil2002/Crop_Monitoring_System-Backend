package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.FieldCropDAO;
import lk.ijse.crop_monitoring_systembackend.dto.FieldCropDTO;
import lk.ijse.crop_monitoring_systembackend.service.FieldCropService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldCropServiceIMPL implements FieldCropService {
    @Autowired
    private FieldCropDAO fieldCropDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveFieldCrop(FieldCropDTO fieldCrop) {

    }

    @Override
    public void updateFieldCrop(FieldCropDTO fieldCrop) {

    }
}
