package lk.ijse.crop_monitoring_systembackend.service;

import lk.ijse.crop_monitoring_systembackend.dto.FieldCropDTO;

public interface FieldCropService {
    void saveFieldCrop(FieldCropDTO fieldCrop);
    void updateFieldCrop(FieldCropDTO fieldCrop);
}
