package lk.ijse.crop_monitoring_systembackend.dao;

import lk.ijse.crop_monitoring_systembackend.entity.FieldStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldStaffDAO extends JpaRepository<FieldStaffEntity, String > {
    void deleteByField_FieldId(String fieldId);
    List<FieldStaffEntity> findByField_FieldId(String fieldId);
    @Query("SELECT fs.staff.staffId FROM FieldStaffEntity fs WHERE fs.field.fieldId = :fieldId")
    List<String> findStaffIdsByFieldId(@Param("fieldId") String fieldId);

}
