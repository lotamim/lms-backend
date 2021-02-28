package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Bank;
import com.newgen.lms.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService extends BaseService {
    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String BANK_NAME_EXIST = "Bank name already exist";
    private static final String DELETED = "Data Delete Successful!";
    private static final String BANK_NAME_EMPTY = "Bank name can't be empty";

    @Autowired
    private BankRepository bankRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Bank bank = null;
        try {
            Bank duplicateCheck = null;
            if (dMap.get("id") != "") {
                bank = bankRepository.findById(Long.parseLong(dMap.get("id"))).get();
                duplicateCheck = bankRepository.findByBankNameIgnoreCaseAndIdIsNot(dMap.get("bankName"), Long.parseLong(dMap.get("id")));
                if (duplicateCheck != null) {
                    return errorMessage(BANK_NAME_EXIST, bank);
                }

            } else {
                bank = new Bank();
                duplicateCheck = bankRepository.findByBankNameIgnoreCase(dMap.get("bankName"));
                if (duplicateCheck != null) {
                    return errorMessage(BANK_NAME_EXIST, bank);
                }
            }
            if (dMap.get("bankName") == "") {
              return  errorMessage(BANK_NAME_EMPTY,bank);
            }
            bank.setBankName(dMap.get("bankName"));
            bank.setBankShortName(dMap.get("bankShortName"));
            bank.setDescription(dMap.get("description"));
            bankRepository.save(bank);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, bank);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> bankList = bankRepository.getList();
            dMap.put("bankList", bankList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Bank bank = null;
        try {
            if (dMap.get("id") != "") {
                bank = bankRepository.findById(Long.parseLong(dMap.get("id"))).get();
                bank.setDeleted(true);
                bankRepository.save(bank);
            } else {
                return errorMessage(ERROR, bank);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, bank);
    }

}
