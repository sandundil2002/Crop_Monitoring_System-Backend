package lk.ijse.crop_monitoring_systembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "logs")
@Entity
public class LogEntity implements Serializable {
    @Id
    private String logId;
    private LocalDate date;
    private String details;
    private String temperature;
    private String observedImg;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private CropEntity crop;
    @ManyToMany
    @JoinTable(name = "log_staff",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<StaffEntity> staff;
}
