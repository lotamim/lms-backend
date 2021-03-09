package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.*;
import com.newgen.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional
public class SanctionService extends BaseService {

    private static final String BANK_DOES_NOT_EXIST = "Bank name doesn't exist";
    private static final String BRANCH_DOES_NOT_EXIST = "Branch name doesn't exist";
    private static final String COMBINED_LIMIT_EMPTY = "Combined limit can't be empty";
    private static final String CURRENCY_EMPTY = "Currency can't be empty";
    private static final String METHOD_EMPTY = "Method can't be empty";
    private static final String NATURE_EMPTY = "Nature can't be empty";
    private static final String PURPOSE_EMPTY = "Purpose can't be empty";
    //sanctionDetails
    private static final String SANCTION_DATE_EMPTY = "Sanction date can't be empty";
    private static final String DIVISION_EMPTY = "Division can't be empty";
    private static final String UNIT_EMPTY = "Unit can't be empty";
    private static final String LOAN_TYPE_EMPTY = "Loan can't be empty";
    private static final String LOAN_SUB_TYPE_EMPTY = "Loan subtype can't be empty";
    private static final String CHARGE_EMPTY = "Charge can't be empty";
    private static final String LIMIT_EMPTY = "Limit can't be empty";
    private static final String MOTGAGE_ITEM_EMPTY = "Motgage item can't be empty";
    private static final String MOTGAGE_ITEM_VALUE_EMPTY = "Motgage item value can't be empty";

    @Autowired
    private SanctionRepository sanctionRepository;
    @Autowired
    private SanctionDetailsRepository sanctionDetailsRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private LoanTypeRepository loanTypeRepository;
    @Autowired
    private LoanSubTypeRepository loanSubTypeRepository;
    @Autowired
    private ChargeRepository chargeRepository;

    //    @Transactional(rollbackFor = Exception.class)
    public Map save(Map<String, Object> dMap) {
        Bank bank = null;
        Branch branch = null;
        Division division = null;
        Unit unit = null;
        LoanType loanType = null;
        LoanSubType loanSubType = null;
        Charge charge = null;
        Sanction sanction = null;
        SanctionDetail sanctionDetail = null;
        Date formattedSanctionDate;


        try {

            if (dMap.get("bankId") != "") {
                bank = bankRepository.findById(Long.parseLong((String) dMap.get("bankId"))).get();
                if (bank == null) {
                    return errorMessage(BANK_DOES_NOT_EXIST, sanction);
                }
            }

            if (dMap.get("branchId") != "") {
                branch = branchRepository.findById(Long.parseLong((String) dMap.get("branchId"))).get();
                if (branch == null) {
                    return errorMessage(BRANCH_DOES_NOT_EXIST, sanction);
                }
            }

            if (dMap.get("sanctionDate") == "") {
                return errorMessage(SANCTION_DATE_EMPTY, sanction);
            }
            if (dMap.get("combineLimit") == "") {
                return errorMessage(COMBINED_LIMIT_EMPTY, sanction);
            }
            if (dMap.get("currency") == "") {
                return errorMessage(CURRENCY_EMPTY, sanction);
            }
            if (dMap.get("method") == "") {
                return errorMessage(METHOD_EMPTY, sanction);
            }
            if (dMap.get("nature") == "") {
                return errorMessage(NATURE_EMPTY, sanction);
            }
            if (dMap.get("purpose") == "") {
                return errorMessage(PURPOSE_EMPTY, sanction);
            }

            sanction = new Sanction();
            sanction.setBankId(Long.parseLong((String) dMap.get("bankId")));
            sanction.setBranchId(Long.parseLong((String) dMap.get("branchId")));
            sanction.setCombineLimit(Double.parseDouble((String) dMap.get("combineLimit")));
            sanction.setCurrency(Boolean.parseBoolean((String) dMap.get("currency")));
            sanction.setMethod((String) dMap.get("method"));
            sanction.setNature((String) dMap.get("nature"));
            sanction.setPurpose((String) dMap.get("purpose"));
            sanction.setRemarks((String) dMap.get("remarks"));

            sanctionRepository.save(sanction);


            List list = (List) dMap.get("sancDetalis");
            for (Object item : list) {
                sanctionDetail = new SanctionDetail();
                if (((LinkedHashMap) item).get("sanctionDate") == "") {
                    return errorMessage(SANCTION_DATE_EMPTY, sanction);
                }

                if (((LinkedHashMap) item).get("divisionId") != "") {
                    division = divisionRepository.findById(Long.parseLong((String) ((LinkedHashMap) item).get("divisionId"))).get();
                    if (division == null) {
                        return errorMessage(DIVISION_EMPTY, sanction);
                    }
                }

                if (((LinkedHashMap) item).get("unitId") != "") {
                    unit = unitRepository.findById(Long.parseLong((String) ((LinkedHashMap) item).get("unitId"))).get();
                    if (unit == null) {
                        return errorMessage(UNIT_EMPTY, sanction);
                    }
                }

                if (((LinkedHashMap) item).get("loanTypeId") != "") {
                    loanType = loanTypeRepository.findById(Long.parseLong((String) ((LinkedHashMap) item).get("loanTypeId"))).get();
                    if (loanType == null) {
                        return errorMessage(LOAN_TYPE_EMPTY, sanction);
                    }
                }

                if (((LinkedHashMap) item).get("loanSubTypeId") != "") {
                    loanSubType = loanSubTypeRepository.findById(Long.parseLong((String) ((LinkedHashMap) item).get("loanSubTypeId"))).get();
                    if (loanSubType == null) {
                        return errorMessage(LOAN_SUB_TYPE_EMPTY, sanction);
                    }
                }

                if (((LinkedHashMap) item).get("chargeId") != "") {
                    charge = chargeRepository.findById(Long.parseLong((String) ((LinkedHashMap) item).get("chargeId"))).get();
                    if (charge == null) {
                        return errorMessage(CHARGE_EMPTY, sanction);
                    }
                }

                formattedSanctionDate = formattedDate((String) ((LinkedHashMap) item).get("sanctionDate"));

                sanctionDetail.setSanctionDate(formattedSanctionDate);
                sanctionDetail.setSanctionId(sanction.getId());
                sanctionDetail.setDivisionId(division.getId());
                sanctionDetail.setUnitId(unit.getId());
                sanctionDetail.setLoanTypeId(loanType.getId());
                sanctionDetail.setLoanSubTypeId(loanSubType.getId());
                sanctionDetail.setChargeId(charge.getId());
                sanctionDetail.setLimit(Double.parseDouble((String) ((LinkedHashMap) item).get("limit")));
                sanctionDetail.setMortgageItem((String) ((LinkedHashMap) item).get("mortgageItem"));
                sanctionDetail.setMortgageItemValue(Double.parseDouble((String) ((LinkedHashMap) item).get("mortgageItemValue")));
                sanctionDetail.setInterestRate(Double.parseDouble((String) ((LinkedHashMap) item).get("interestRate")));

                sanctionDetailsRepository.save(sanctionDetail);

            }


        } catch (Exception ex) {

        }
        return null;
    }

    public Map update() {
        return null;
    }

    public Map list() {
        return null;
    }

    public Map delete() {
        return null;
    }
}
