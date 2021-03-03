package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.*;
import com.newgen.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService extends BaseService {
    private static final String SUCCESS = "Data Save Successful!";
    private static final String ERROR = "Data not found!";
    private static final String ACCOUNT_NUMBER_EXIST = "Account already exist!";
    private static final String DELETED = "Data Delete Successful!";
    private static final String BANK_NOT_FOUND = "Bank not found!";
    private static final String BRANCH_NOT_FOUND = "Branch not found!";
    private static final String UNIT_NOT_FOUND = "Unit not found!";
    private static final String DIVISION_NOT_FOUND = "Division not found!";
    private static final String ACCOUNT_NUMBER_NOT_FOUND = "Account number not found!";
    private static final String ACCOUNT_TYPE_NOT_FOUND = "Account type not found!";

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private DivisionRepository divisionRepository;

    public Map<String, String> saveOrUpdate(Map<String, String> dMap) {
        Account account = null;
        Bank bank = null;
        Branch branch = null;
        Unit unit = null;
        Division division = null;
        Account accountDuplicateCheck = null;
        try {
            if (dMap.get("id") != "") {
                account = accountRepository.findById( Long.parseLong(dMap.get("id"))).get();
                accountDuplicateCheck = accountRepository.findByAccountNumberIgnoreCaseAndIdIsNot(dMap.get("accountNumber"), Long.parseLong(dMap.get("id")));
                if (accountDuplicateCheck != null) {
                    return errorMessage(ACCOUNT_NUMBER_EXIST, account);
                }

            } else {
                accountDuplicateCheck = accountRepository.findByAccountNumberIgnoreCaseAndBankId(dMap.get("accountNumber"), Long.parseLong(dMap.get("bankId")));
                if (accountDuplicateCheck != null) {
                    return errorMessage(ACCOUNT_NUMBER_EXIST, account);
                }
                account = new Account();
            }

            bank = bankRepository.findById(Long.parseLong(dMap.get("bankId"))).get();
            if (bank == null || bank.equals("")) {
                return errorMessage(BANK_NOT_FOUND, null);
            }

            branch = branchRepository.findById(Long.parseLong(dMap.get("branchId"))).get();
            if (branch == null || branch.equals("")) {
                return errorMessage(BRANCH_NOT_FOUND, null);
            }

            unit = unitRepository.findById(Long.parseLong(dMap.get("unitId"))).get();
            if (unit == null || unit.equals("")) {
                return errorMessage(UNIT_NOT_FOUND, null);
            }

            division = divisionRepository.findById(Long.parseLong(dMap.get("divisionId"))).get();
            if (division == null || division.equals("")) {
                return errorMessage(DIVISION_NOT_FOUND, null);
            }

            if (dMap.get("accountNumber") == "") {
                return errorMessage(ACCOUNT_NUMBER_NOT_FOUND, account);
            }

            if (dMap.get("accountTypeId") == "") {
                return errorMessage(ACCOUNT_TYPE_NOT_FOUND, account);
            }


            account.setBankId(Long.parseLong(dMap.get("bankId")));
            account.setBranchId(Long.parseLong(dMap.get("branchId")));
            account.setAccountTypeId(Long.parseLong(dMap.get("accountTypeId")));
            account.setDivisionId(Long.parseLong(dMap.get("divisionId")));
            account.setUnitId(Long.parseLong(dMap.get("unitId")));
            account.setAccountNumber(dMap.get("accountNumber"));
            account.setUnitId(Long.parseLong(dMap.get("unitId")));
            account.setAccountBalance(Double.parseDouble(dMap.get("accountBalance")));
            accountRepository.save(account);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, account);
    }


    public Map list() {
        try {
            Map dMap = new LinkedHashMap();
            List<Map<?, ?>> accountList = accountRepository.getList();
            dMap.put("accountList", accountList);
            return dMap;
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Account account = null;
        try {
            if (dMap.get("id") != "") {
                account = accountRepository.findById(Long.parseLong(dMap.get("id"))).get();
                account.setDeleted(true);
                accountRepository.save(account);
            } else {
                return errorMessage(ERROR, account);
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(DELETED, account);
    }

    public Map select(Long id) {
        Map map = new HashMap();
        try {
            map.put("list", accountRepository.selectOne(id));
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
        return map;
    }

}
