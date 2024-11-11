package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.UserDAO;
import lk.ijse.crop_monitoring_systembackend.dto.UserDTO;
import lk.ijse.crop_monitoring_systembackend.service.UserService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveUser(UserDTO user) {

    }

    @Override
    public void updateUser(String email, UserDTO user) {

    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }
}
