package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Charge;
import com.newgen.lms.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChargeService extends BaseService {

    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String CHARGE_NAME_EXIST = "Charge name already exist!";
    private static final String DELETED = "Data Delete Successful!";


    @Autowired
    private ChargeRepository chargeRepository;

    public Map saveOrUpdate(Map<String, String> dMap) {
        Charge charge = null;
        try {

            if (dMap.get("id") != "") {
                charge = chargeRepository.findByChangeNameIgnoreCaseAndIdIsNot(dMap.get("changeName"), Long.parseLong(dMap.get("id")));
                if (charge != null) {
                    return errorMessage(CHARGE_NAME_EXIST, charge);
                }

            } else {
                charge = chargeRepository.findByChangeNameIgnoreCase(dMap.get("changeName"));
                if (charge != null) {
                    return errorMessage(CHARGE_NAME_EXIST, charge);
                }
                charge = new Charge();
            }

            charge.setChangeName(dMap.get("changeName"));
            charge.setChargeRate(Double.parseDouble(dMap.get("chargeRate")));
            charge.setRemarks(dMap.get("remarks"));

            chargeRepository.save(charge);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, charge);
    }


    public Map delete(Map<String, String> dMap) {
        Charge charge = null;
        try {
            if (dMap.get("id") != "") {
                charge = chargeRepository.findById(Long.parseLong(dMap.get("id"))).get();
                charge.setDeleted(true);
                chargeRepository.save(charge);
            } else {
                return errorMessage(ERROR, charge);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, charge);
    }

    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> chargeList = chargeRepository.getList();
            dMap.put("chargeList", chargeList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }
}
