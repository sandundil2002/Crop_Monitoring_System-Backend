package lk.ijse.crop_monitoring_systembackend.service.impl;

import lk.ijse.crop_monitoring_systembackend.dao.CropDAO;
import lk.ijse.crop_monitoring_systembackend.dto.CropDTO;
import lk.ijse.crop_monitoring_systembackend.service.CropService;
import lk.ijse.crop_monitoring_systembackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CropServiceIMPL implements CropService {
    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveCrop(CropDTO crop) {
        crop.setCropId(generateCropID());
        cropDAO.save(mappingUtil.cropConvertToEntity(crop));
    }

    @Override
    public void updateCrop(String id, CropDTO crop) {

    }

    @Override
    public CropDTO searchCrop(String id) {
        return null;
    }

    @Override
    public boolean deleteCrop(String id) {
        return false;
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return List.of();
    }

    private String generateCropID() {
        if (cropDAO.count() == 0) {
            return "C001";
        } else {
            String lastId = cropDAO.findAll().get(cropDAO.findAll().size() - 1).getCropId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "C00" + newId;
            } else if (newId < 100) {
                return "C0" + newId;
            } else {
                return "C" + newId;
            }
        }
    }
}
