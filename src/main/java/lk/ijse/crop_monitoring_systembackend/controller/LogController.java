package lk.ijse.crop_monitoring_systembackend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lk.ijse.crop_monitoring_systembackend.dto.CropDTO;
import lk.ijse.crop_monitoring_systembackend.dto.FieldDTO;
import lk.ijse.crop_monitoring_systembackend.dto.LogDTO;
import lk.ijse.crop_monitoring_systembackend.dto.StaffDTO;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.service.LogService;
import lk.ijse.crop_monitoring_systembackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
public class LogController {
    @Autowired
    private LogService logService;

    private static final Logger logger = Logger.getLogger(LogController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveLog(
            @Valid
            @RequestPart("details") String details,
            @RequestPart("temperature") String temperature,
            @RequestPart("observedImg") MultipartFile observedImg,
            @RequestPart("fieldId") String fieldId,
            @RequestPart("cropId") String cropId) {
        try {
            byte[] img = observedImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            LogDTO logDTO = new LogDTO();
            logDTO.setDetails(details);
            logDTO.setTemperature(temperature);
            logDTO.setObservedImg(base64Img);
            logDTO.setFieldId(fieldId);
            logDTO.setCropId(cropId);
            logService.saveLog(logDTO);
            logger.info("Log saved successfully: " + logDTO);
            return new ResponseEntity<>("Log saved successfully", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            logger.severe("Failed to save log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Failed to save log", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to save log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
