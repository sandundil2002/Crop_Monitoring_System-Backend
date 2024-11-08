package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements Serializable {
    private String cropId;
    private String commonName;
    private String scientificName;
    private String category;
    private String season;
    private String cropImg;
}
