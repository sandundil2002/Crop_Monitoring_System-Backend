package lk.ijse.crop_monitoring_systembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field")
@Entity
public class FieldEntity implements Serializable {
    @Id
    private String fieldId;
    private String fieldName;
    private String location;
    private String size;
    private String fieldImg1;
    private String fieldImg2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipment;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<LogEntity> logs;
    @ManyToMany
    @JoinTable(name = "field_crop",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id"))
    private List<CropEntity> crops;
    @ManyToMany
    @JoinTable(name = "field_staff",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<StaffEntity> staff;
}
