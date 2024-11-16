package lk.ijse.crop_monitoring_systembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field_staff")
@Entity
public class FieldStaffEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldStaffId;
    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "fieldId")
    @ToString.Exclude
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staffId")
    private StaffEntity staff;
    @Column(name = "assigned_date")
    private LocalDate assignedDate;
}
