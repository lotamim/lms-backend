package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Division;
import com.newgen.lms.model.Unit;
import com.newgen.lms.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DivisionService extends BaseService {

    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String DIVISION_NAME_EXIST = "Division name already exist";
    private static final String DELETED = "Data Delete Successful!";
    private static final String DIVISION_NAME_EMPTY = "Division name is empty!";

    @Autowired
    private DivisionRepository divisionRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Division division = null;
        try {
            Division duplicateCheck = null;
            if (dMap.get("id") != "") {
                division = divisionRepository.findById(Long.parseLong(dMap.get("id"))).get();
                duplicateCheck = divisionRepository.findByDivisionNameIgnoreCaseAndIdIsNot(dMap.get("divisionName"), Long.parseLong(dMap.get("id")));
                if (duplicateCheck != null) {
                    return errorMessage(DIVISION_NAME_EXIST, division);
                }

            } else {
                division = new Division();
                duplicateCheck = divisionRepository.findByDivisionNameIgnoreCase(dMap.get("divisionName"));
                if (duplicateCheck != null) {
                    return errorMessage(DIVISION_NAME_EXIST, division);
                }
            }

            if (dMap.get("divisionName") == "") {
                return  errorMessage(DIVISION_NAME_EMPTY,division);
            }
            division.setDivisionName(dMap.get("divisionName"));
            division.setContactPerson(dMap.get("contactPerson"));
            division.setEmail(dMap.get("email"));
            division.setPhoneNumber(dMap.get("phoneNumber"));
            division.setDescription(dMap.get("description"));

            divisionRepository.save(division);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, division);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> divisionList = divisionRepository.getList();
            dMap.put("divisionList", divisionList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Division division = null;
        try {
            if (dMap.get("id") != "") {
                division = divisionRepository.findById(Long.parseLong(dMap.get("id"))).get();
                division.setDeleted(true);
                divisionRepository.save(division);
            } else {
                return errorMessage(ERROR, division);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, division);
    }

}
