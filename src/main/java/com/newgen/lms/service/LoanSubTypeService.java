package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.LoanSubType;
import com.newgen.lms.repository.LoanSubTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanSubTypeService extends BaseService {
    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String LOAN_SUBTYPE_EXIST = "Loan subtype already exist";
    private static final String DELETED = "Data Delete Successful!";

    @Autowired
    private LoanSubTypeRepository loanSubTypeRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        LoanSubType loanSubType = null;
        try {
            if (dMap.get("id") != "") {
                loanSubType = loanSubTypeRepository.findByLoanSubTypeNameIgnoreCaseAndIdIsNot(dMap.get("loanSubTypeName"), Long.parseLong(dMap.get("id")));
                if (loanSubType != null) {
                    return errorMessage(LOAN_SUBTYPE_EXIST, loanSubType);
                }

            } else {
                loanSubType = loanSubTypeRepository.findByLoanSubTypeNameIgnoreCase(dMap.get("loanSubTypeName"));
                if (loanSubType != null) {
                    return errorMessage(LOAN_SUBTYPE_EXIST, loanSubType);
                }
                loanSubType = new LoanSubType();
            }

            loanSubType.setLoanSubTypeName(dMap.get("loanSubTypeName"));
            loanSubType.setDescription(dMap.get("description"));

            loanSubTypeRepository.save(loanSubType);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, loanSubType);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> loanSubTypeList = loanSubTypeRepository.getList();
            dMap.put("loanSubTypeList", loanSubTypeList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        LoanSubType loanSubType = null;
        try {
            if (dMap.get("id") != "") {
                loanSubType = loanSubTypeRepository.findById(Long.parseLong(dMap.get("id"))).get();
                loanSubType.setDeleted(true);
                loanSubTypeRepository.save(loanSubType);
            } else {
                return errorMessage(ERROR, loanSubType);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, loanSubType);
    }

}