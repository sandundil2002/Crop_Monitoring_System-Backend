package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.dto.UserDTO;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.exception.NotFoundException;
import lk.ijse.crop_monitoring_systembackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO user) {
        if (user != null) {
            try {
                boolean exist = userService.searchUser(user.getEmail());
                if (exist) {
                    userService.saveUser(user);
                    logger.info("User saved successfully: " + user);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to save user: " + user);
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("email") String email, @RequestBody UserDTO user) {
        if (email != null && user != null) {
            try {
                userService.updateUser(email, user);
                logger.info("User password updated successfully: " + user);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to update user: " + user);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}