package lk.ijse.crop_monitoring_systembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field_crop")
@Entity
public class FieldCropEntity implements Serializable {
    @Id
    private String fieldCropId;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private CropEntity crop;
}
