package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.dto.FieldDTO;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.service.FieldService;
import lk.ijse.crop_monitoring_systembackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;

    private static final Logger logger = Logger.getLogger(FieldController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveField(
            @RequestPart("fieldName") String fieldName,
            @RequestPart("location") String fieldLocation,
            @RequestPart("size") String fieldSize,
            @RequestPart("fieldImg1") MultipartFile fieldImg1,
            @RequestPart("fieldImg2") MultipartFile fieldImg2) {
            try {
                Point location = AppUtil.parseLocation(fieldLocation);
                byte[] img1 = fieldImg1.getBytes();
                byte[] img2 = fieldImg2.getBytes();
                String base64Img1 = AppUtil.toBase64Pic(img1);
                String base64Img2 = AppUtil.toBase64Pic(img2);
                FieldDTO fieldDTO = new FieldDTO();
                fieldDTO.setFieldName(fieldName);
                fieldDTO.setLocation(location);
                fieldDTO.setSize(fieldSize);
                fieldDTO.setFieldImg1(base64Img1);
                fieldDTO.setFieldImg2(base64Img2);
                fieldService.saveField(fieldDTO);
                logger.info("Field saved successfully: " + fieldName);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Failed to save field: " + fieldName);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
