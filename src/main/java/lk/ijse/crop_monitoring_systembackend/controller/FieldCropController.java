package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.service.FieldCropService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/field-crop")
@RequiredArgsConstructor
public class FieldCropController {
    @Autowired
    private FieldCropService fieldCropService;

    private static final Logger logger = Logger.getLogger(FieldCropController.class.getName());
}
