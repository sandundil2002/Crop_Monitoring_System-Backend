package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements Serializable {
    private String fieldId;
    private String fieldName;
    private String location;
    private String size;
    private String fieldImg1;
    private String fieldImg2;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
}
