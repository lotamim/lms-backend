package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Unit;
import com.newgen.lms.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnitService extends BaseService {

    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String UNIT_NAME_EXIST = "Unit name already exist";
    private static final String DELETED = "Data Delete Successful!";
    private static final String UNIT_NAME_EMPTY = "Unit name is empty!";

    @Autowired
    private UnitRepository unitRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Unit unit = null;
        try {
            Unit duplicateCheck = null;
            if (dMap.get("id") != "") {
                unit = unitRepository.findById(Long.parseLong(dMap.get("id"))).get();
                duplicateCheck = unitRepository.findByUnitNameIgnoreCaseAndIdIsNot(dMap.get("unitName"), Long.parseLong(dMap.get("id")));
                if (duplicateCheck != null) {
                    return errorMessage(UNIT_NAME_EXIST, unit);
                }

            } else {
                unit = new Unit();
                duplicateCheck = unitRepository.findByUnitNameIgnoreCase(dMap.get("unitName"));
                if (duplicateCheck != null) {
                    return errorMessage(UNIT_NAME_EXIST, unit);
                }
            }
            if (dMap.get("unitName") == "") {
                return  errorMessage(UNIT_NAME_EMPTY,unit);
            }

            unit.setUnitName(dMap.get("unitName"));
            unit.setUnitShortName(dMap.get("unitShortName"));
            unit.setAddress(dMap.get("address"));
            unit.setPhoneNumber(dMap.get("phoneNumber"));
            unit.setContactPerson(dMap.get("contactPerson"));
            unit.setDescription(dMap.get("description"));

            unitRepository.save(unit);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, unit);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> unitList = unitRepository.getList();
            dMap.put("unitList", unitList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Unit unit = null;
        try {
            if (dMap.get("id") != "") {
                unit = unitRepository.findById(Long.parseLong(dMap.get("id"))).get();
                unit.setDeleted(true);
                unitRepository.save(unit);
            } else {
                return errorMessage(ERROR, unit);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, unit);
    }

}
