package lk.ijse.crop_monitoring_systembackend.util;

import lk.ijse.crop_monitoring_systembackend.dto.FieldDTO;
import lk.ijse.crop_monitoring_systembackend.dto.StaffDTO;
import lk.ijse.crop_monitoring_systembackend.dto.UserDTO;
import lk.ijse.crop_monitoring_systembackend.entity.FieldEntity;
import lk.ijse.crop_monitoring_systembackend.entity.StaffEntity;
import lk.ijse.crop_monitoring_systembackend.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingUtil {
    @Autowired
    private ModelMapper modelMapper;

    //Matters of StaffEntity & StaffDTO
    public StaffDTO staffConvertToDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public StaffEntity staffConvertToEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public List<StaffDTO> staffConvertToDTOList(List<StaffEntity> staffEntities) {
        return staffEntities.stream().map(this::staffConvertToDTO).toList();
    }

    //Maters of UserEntity & UserDTO
    public UserDTO userConvertToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity userConvertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    //Maters of FieldEntity & FieldDTO
    public FieldDTO fieldConvertToDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public FieldEntity fieldConvertToEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public List<FieldDTO> fieldConvertToDTOList(List<FieldEntity> fieldEntities) {
        return fieldEntities.stream().map(this::fieldConvertToDTO).toList();
    }

}
