package lk.ijse.crop_monitoring_systembackend.dao;

import lk.ijse.crop_monitoring_systembackend.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {
    @Modifying
    @Transactional
    @Query("UPDATE VehicleEntity v SET v.status = :status WHERE v.vehicleId = :vehicleId")
    void updateVehicleStatus(@Param("vehicleId") String vehicleId, @Param("status") String status);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM VehicleEntity v WHERE v.vehicleId = :vehicleId AND v.status = 'Assigned'")
    boolean isVehicleAssigned(@Param("vehicleId") String vehicleId);
}