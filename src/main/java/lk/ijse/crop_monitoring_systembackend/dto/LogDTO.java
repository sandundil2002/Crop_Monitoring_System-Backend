package lk.ijse.crop_monitoring_systembackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lk.ijse.crop_monitoring_systembackend.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements Serializable, Response {
    private String logId;
    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    @NotBlank(message = "Details cannot be blank")
    @Size(max = 255, message = "Details must be at most 255 characters")
    private String details;
    @NotBlank(message = "Temperature cannot be blank")
    @Pattern(regexp = "^-?\\d+(\\.\\d+)?°?[CF]$", message = "Temperature must be a valid format, e.g., 36.5°C or 98°F")
    private String temperature;
    @Size(max = 65535, message = "Observed image data exceeds maximum size")
    private String observedImg;
    @NotEmpty(message = "Fields list cannot be empty")
    private List<@Valid FieldDTO> fields;
    @NotEmpty(message = "Crops list cannot be empty")
    private List<@Valid CropDTO> crops;
    @NotEmpty(message = "Staff list cannot be empty")
    private List<@Valid StaffDTO> staff;
}