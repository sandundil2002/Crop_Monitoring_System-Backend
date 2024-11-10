package lk.ijse.crop_monitoring_systembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field_staff")
@Entity
public class FieldStaffEntity implements Serializable {
    @Id
    private String fieldStaffId;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
}
