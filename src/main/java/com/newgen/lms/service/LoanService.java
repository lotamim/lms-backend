package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Loan;
import com.newgen.lms.model.LoanSubType;
import com.newgen.lms.model.LoanType;
import com.newgen.lms.repository.LoanRepository;
import com.newgen.lms.repository.LoanSubTypeRepository;
import com.newgen.lms.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanService extends BaseService {
    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String LOAN_NAME_EXIST = "Loan name already exist!";
    private static final String DELETED = "Data Delete Successful!";
    private static final String LOAN_TYPE_NOT_FOUND = "Loan type not found!";
    private static final String LOAN_SUBTYPE_NOT_FOUND = "Loan subtype not found!";

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanTypeRepository loanTypeRepository;
    @Autowired
    private LoanSubTypeRepository loanSubTypeRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Loan loan = null;
        LoanSubType loanSubType = null;
        LoanType loanType = null;
        try {
            if (dMap.get("id") != "") {
                loan = loanRepository.findByLoanFacilityNameIgnoreCaseAndIdIsNot(dMap.get("loanFacilityName"), Long.parseLong(dMap.get("id")));
                if (loan != null) {
                    return errorMessage(LOAN_NAME_EXIST, loan);
                }

            } else {
                loan = loanRepository.findByLoanFacilityNameIgnoreCase(dMap.get("loanFacilityName"));
                if (loan != null) {
                    return errorMessage(LOAN_NAME_EXIST, loan);
                }
                loan = new Loan();
            }

            loanType = loanTypeRepository.findById(Long.parseLong(dMap.get("loanTypeId"))).get();
            if (loanType == null || loanType.equals("")) {
                return errorMessage(LOAN_TYPE_NOT_FOUND, null);
            }

            loanSubType = loanSubTypeRepository.findById(Long.parseLong(dMap.get("loanSubTypeId"))).get();
            if (loanSubType == null || loanSubType.equals("")) {
                return errorMessage(LOAN_SUBTYPE_NOT_FOUND, null);
            }

            loan.setLoanTypeId(Long.parseLong(dMap.get("loanTypeId")));
            loan.setLoanSubTypeId(Long.parseLong(dMap.get("loanSubTypeId")));
            loan.setLoanFacilityName(dMap.get("loanFacilityName"));
            loanRepository.save(loan);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, loan);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> loanList = loanRepository.getList();
            dMap.put("loanList", loanList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Loan loan = null;
        try {
            if (dMap.get("id") != "") {
                loan = loanRepository.findById(Long.parseLong(dMap.get("id"))).get();
                loan.setDeleted(true);
                loanRepository.save(loan);
            } else {
                return errorMessage(ERROR, loan);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, loan);
    }

}
