package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Loan;
import com.newgen.lms.model.LoanType;
import com.newgen.lms.repository.LoanRepository;
import com.newgen.lms.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanTypeService extends BaseService {
    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String LOAN_TYPE_EXIST = "Loan type already exist";
    private static final String DELETED = "Data Delete Successful!";

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        LoanType loanType = null;
        try {
            if (dMap.get("id") != "") {
                loanType = loanTypeRepository.findByLoanTypeNameIgnoreCaseAndIdIsNot(dMap.get("loanTypeName"), Long.parseLong(dMap.get("id")));
                if (loanType != null) {
                    return errorMessage(LOAN_TYPE_EXIST, loanType);
                }

            } else {
                loanType = loanTypeRepository.findByLoanTypeNameIgnoreCase(dMap.get("loanTypeName"));
                if (loanType != null) {
                    return errorMessage(LOAN_TYPE_EXIST, loanType);
                }
                loanType = new LoanType();
            }

            loanType.setLoanTypeName(dMap.get("loanTypeName"));
            loanType.setDescription(dMap.get("description"));

            loanTypeRepository.save(loanType);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, loanType);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> loanList = loanTypeRepository.getList();
            dMap.put("loanTypeList", loanList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        LoanType loanType = null;
        try {
            if (dMap.get("id") != "") {
                loanType = loanTypeRepository.findById(Long.parseLong(dMap.get("id"))).get();
                loanType.setDeleted(true);
                loanTypeRepository.save(loanType);
            } else {
                return errorMessage(ERROR, loanType);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, loanType);
    }

}
