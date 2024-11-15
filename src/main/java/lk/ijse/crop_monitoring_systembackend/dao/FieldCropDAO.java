package lk.ijse.crop_monitoring_systembackend.dao;

import lk.ijse.crop_monitoring_systembackend.entity.FieldCropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldCropDAO extends JpaRepository<FieldCropEntity, String> {}
