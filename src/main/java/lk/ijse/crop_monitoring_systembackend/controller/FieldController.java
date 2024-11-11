package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;

    private static final Logger logger = Logger.getLogger(FieldController.class.getName());
}
