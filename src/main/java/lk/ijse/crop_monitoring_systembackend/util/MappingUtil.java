package lk.ijse.crop_monitoring_systembackend.util;

import lk.ijse.crop_monitoring_systembackend.dto.StaffDTO;
import lk.ijse.crop_monitoring_systembackend.entity.StaffEntity;
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
}
