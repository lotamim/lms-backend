package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.LoanClassification;
import com.newgen.lms.repository.LoanClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class LoanClassificationService extends BaseService {

    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String CLASSIFICATION_NAME_EXIST = "Classification name already exist!";
    private static final String DELETED = "Data Delete Successful!";


    @Autowired
    private LoanClassificationRepository loanClassificationRepository;

    public Map saveOrUpdate(Map<String, String> dMap) {
        LoanClassification loanClassification = null;
        try {

            if (dMap.get("id") != "") {
                loanClassification = loanClassificationRepository.findByClassificationStageIgnoreCaseAndIdIsNot(dMap.get("classificationStage"), Long.parseLong(dMap.get("id")));
                if (loanClassification != null) {
                    return errorMessage(CLASSIFICATION_NAME_EXIST, loanClassification);
                }

            } else {
                loanClassification = loanClassificationRepository.findByClassificationStageIgnoreCase(dMap.get("classificationStage"));
                if (loanClassification != null) {
                    return errorMessage(CLASSIFICATION_NAME_EXIST, loanClassification);
                }
                loanClassification = new LoanClassification();
            }

            loanClassification.setClassificationStage(dMap.get("classificationStage"));
            loanClassification.setDuration(Long.parseLong(dMap.get("duration")));
            loanClassification.setChargeId(Long.parseLong(dMap.get("chargeId")));
            loanClassification.setType(dMap.get("type"));
            loanClassification.setPercent(Double.parseDouble(dMap.get("percent")));
            loanClassification.setRemarks(dMap.get("remarks"));

            loanClassificationRepository.save(loanClassification);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, loanClassification);
    }


    public Map delete(Map<String, String> dMap) {
        LoanClassification loanClassification = null;
        try {
            if (dMap.get("id") == "") {
                return errorMessage(ERROR, loanClassification);
            }

            loanClassification = loanClassificationRepository.findById(Long.parseLong(dMap.get("id"))).get();
            loanClassification.setDeleted(true);
            loanClassificationRepository.save(loanClassification);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, loanClassification);
    }

    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> classificationList = loanClassificationRepository.getList();
            dMap.put("classificationList", classificationList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }
}
