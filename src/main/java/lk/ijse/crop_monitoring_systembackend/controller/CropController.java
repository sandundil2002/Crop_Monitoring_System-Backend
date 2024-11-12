package lk.ijse.crop_monitoring_systembackend.controller;

import jakarta.validation.Valid;
import lk.ijse.crop_monitoring_systembackend.customResponse.ErrorResponse;
import lk.ijse.crop_monitoring_systembackend.customResponse.Response;
import lk.ijse.crop_monitoring_systembackend.dto.CropDTO;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.service.CropService;
import lk.ijse.crop_monitoring_systembackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/crop")
@RequiredArgsConstructor
public class CropController {
    @Autowired
    private CropService cropService;

    private static final Logger logger = Logger.getLogger(CropController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCrop(
            @Valid
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImg") MultipartFile cropImg) {
        try {
            byte[] img = cropImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCropImg(base64Img);
            cropService.saveCrop(cropDTO);
            logger.info("Crop saved successfully: " + commonName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to save crop: " + commonName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCrop(
            @Valid
            @PathVariable String id,
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImg") MultipartFile cropImg) {
        try {
            byte[] img = cropImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCropImg(base64Img);
            cropService.updateCrop(id, cropDTO);
            logger.info("Crop updated successfully: " + commonName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to update crop: " + commonName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchCrop(@PathVariable String id) {
        try {
            CropDTO cropDTO = cropService.searchCrop(id);
            logger.info("Crop searched successfully: " + cropDTO.getCommonName());
            return cropDTO;
        } catch (Exception e) {
            logger.severe("Failed to search crop with id: " + id);
            return new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
