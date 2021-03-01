package com.newgen.lms.service;


import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.AccountType;
import com.newgen.lms.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AccountTypeService extends BaseService {
    private static final String SUCCESS = "Data save successful !";
    private static final String ERROR = "Data is not found !";
    private static final String NAME_ALREADY_EXIST = "Account type already exist !";
    private static final String DELETE_SUCCESS = "Data delete successful !";


    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public Map saveOrUpdate(Map<String, String> dMap) {
        AccountType accountType = null;
        try {
            if (dMap.get("id") != "") {
                accountType = accountTypeRepository.findByAccountTypeNameIgnoreCaseAndIdIsNot(dMap.get("accountTypeName"), Long.parseLong(dMap.get("id")));
                if (accountType != null) {
                    return errorMessage(NAME_ALREADY_EXIST, accountType);
                }
                accountType = accountTypeRepository.findById(Long.parseLong(dMap.get("id"))).get();
            } else {
                accountType = accountTypeRepository.findByAccountTypeNameIgnoreCase(dMap.get("accountTypeName"));
                if (accountType != null) {
                    return errorMessage(NAME_ALREADY_EXIST, accountType);
                }
                accountType = new AccountType();
            }

            accountType.setAccountTypeName(dMap.get("accountTypeName"));
            accountType.setDescription(dMap.get("description"));
            accountTypeRepository.save(accountType);

        } catch (Exception ex) {
            return errorMessage(ERROR, accountType);
        }
        return successMessage(SUCCESS, accountType);
    }


    public Map delete(Map<String, String> dMap) {
        AccountType accountType = null;
        try {
            accountType = accountTypeRepository.findById(Long.parseLong(dMap.get("id"))).get();
            accountType.setDeleted(true);
            accountTypeRepository.save(accountType);
        } catch (Exception ex) {
            return errorMessage(ERROR, accountType);
        }
        return successMessage(DELETE_SUCCESS, accountType);
    }

    public Map list() {
        Map dMap = new LinkedHashMap();
        try {
            dMap.put("accountTypeList", accountTypeRepository.getList());
        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return dMap;
    }

}
