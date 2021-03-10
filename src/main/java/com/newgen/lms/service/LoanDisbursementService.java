package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.LoanDisbursement;
import com.newgen.lms.model.Sanction;
import com.newgen.lms.model.Unit;
import com.newgen.lms.repository.LoanDisbursementRepository;
import com.newgen.lms.repository.SanctionRepository;
import com.newgen.lms.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Map;

@Service
public class LoanDisbursementService extends BaseService {

    private static final String SUCCESS = "Successfully date saved";
    private static final String ERROR = "Data not found!";
    private static final String SANCTION_ID_EMPTY = "Sanction id not found!";
    private static final String UNIT_ID_EMPTY = "Unit id not found!";
    private static final String DISBURSEMENT_REF_EMPTY = "Disbursement reference not found!";
    private static final String CURRENCY_EMPTY = "Currency not found!";
    private static final String LOAN_AMOUNT_EMPTY = "Loan amount not found!";
    private static final String CONVERSION_RATE_EMPTY = "Conversion rate not found!";
    private static final String EXPIRE_DATE_EMPTY = "Expire date not found!";
    private static final String DISBURSEMENT_DATE_EMPTY = "Disbursement date not found!";
    private static final String STATUS_EMPTY = "Status not found!";
    private static final String DISBURSEMENT_AMOUNT_EXCEED_FOR_UNIT = "Disbursement amount exceeded for combined unit allowance!";
    private static final String DISBURSEMENT_AMOUNT_EXCEED_FOR_SANCTION = "Combined sanction limit exceeded!";
    private static final String LOAN_AMOUNT_NEGATIVE = "Loan amount can't be negative!";


    @Autowired
    private SanctionRepository sanctionRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private LoanDisbursementRepository loanDisbursementRepository;

    public Map save(Map<String, String> dMap) {

        Sanction sanction = null;
        Unit unit = null;
        LoanDisbursement loanDisbursement = null;
        Double loanAmountInBDT = 0.0;
        Date formattedDisbursementDate;
        Date formattedExpireDate;

        try {

            if (dMap.get("sanctionId") != "") {
                sanction = sanctionRepository.findById(Long.parseLong(dMap.get("sanctionId"))).get();
                if (sanction == null) {
                    return errorMessage(SANCTION_ID_EMPTY, loanDisbursement);
                }
            }

            if (dMap.get("unitId") != "") {
                unit = unitRepository.findById(Long.parseLong(dMap.get("unitId"))).get();
                if (unit == null) {
                    return errorMessage(UNIT_ID_EMPTY, loanDisbursement);

                }
            }

            if (dMap.get("disbursementRefNo") == "") {
                return errorMessage(DISBURSEMENT_REF_EMPTY, loanDisbursement);
            }
            if (dMap.get("loanAmount") == "") {
                return errorMessage(LOAN_AMOUNT_EMPTY, loanDisbursement);
            }
            if (dMap.get("disbursementCurrency") == "") {
                return errorMessage(CURRENCY_EMPTY, loanDisbursement);
            }
            if (dMap.get("conversionRate") == "") {
                return errorMessage(CONVERSION_RATE_EMPTY, loanDisbursement);
            }
            if (dMap.get("expiryDate") == "") {
                return errorMessage(EXPIRE_DATE_EMPTY, loanDisbursement);
            } else {
                formattedExpireDate = formattedDate(dMap.get("expiryDate"));
            }
            if (dMap.get("disbursementDate") == "") {
                return errorMessage(DISBURSEMENT_DATE_EMPTY, loanDisbursement);
            } else {
                formattedDisbursementDate = formattedDate(dMap.get("disbursementDate"));
            }
            if (dMap.get("status") == "") {
                return errorMessage(STATUS_EMPTY, loanDisbursement);
            }

            Double loanAmount = Double.parseDouble(dMap.get("loanAmount"));
            Double conversionRate = Double.parseDouble(dMap.get("conversionRate"));
            loanAmountInBDT = loanAmount * conversionRate;


            if (loanAmountInBDT > 0) {
                Double disbursementLimitForUnit = loanDisbursementRepository.checkLoanLimitForUnit(Long.parseLong(dMap.get("sanctionId")), Long.parseLong(dMap.get("unitId")));
                Double totalAmountInDisbursementRef = loanDisbursementRepository.totalAmountInDisbursementRef(dMap.get("disbursementRefNo"));
                Double totalAmountInSanctionRef = loanDisbursementRepository.totalAmountInSanctionRef(Long.parseLong(dMap.get("sanctionId")));
                Double totalLimitForSanctionRef = loanDisbursementRepository.totalLimitForSanctionRef(Long.parseLong(dMap.get("sanctionId")));
                if (totalAmountInDisbursementRef == null) {
                    totalAmountInDisbursementRef = 0.0;
                    if (disbursementLimitForUnit < (totalAmountInDisbursementRef + loanAmountInBDT)) {
                        return errorMessage(DISBURSEMENT_AMOUNT_EXCEED_FOR_UNIT, loanDisbursement);
                    }
                    if (totalLimitForSanctionRef < (totalAmountInSanctionRef + loanAmountInBDT)) {
                        return errorMessage(DISBURSEMENT_AMOUNT_EXCEED_FOR_SANCTION, loanDisbursement);
                    }

                } else {
                    if (disbursementLimitForUnit < (totalAmountInDisbursementRef + loanAmountInBDT)) {
                        return errorMessage(DISBURSEMENT_AMOUNT_EXCEED_FOR_UNIT, loanDisbursement);
                    }
                    if (totalLimitForSanctionRef < (totalAmountInSanctionRef + loanAmountInBDT)) {
                        return errorMessage(DISBURSEMENT_AMOUNT_EXCEED_FOR_SANCTION, loanDisbursement);
                    }
                }

            } else {
                return errorMessage(LOAN_AMOUNT_NEGATIVE, loanDisbursement);
            }


            loanDisbursement = new LoanDisbursement();
            loanDisbursement.setDisbursementRefNo(dMap.get("disbursementRefNo"));
            loanDisbursement.setDisbursementDate(formattedDisbursementDate);
            loanDisbursement.setSanctionId(sanction.getId());
            loanDisbursement.setUnitId(unit.getId());
            loanDisbursement.setLoanAmountInBdt(loanAmountInBDT);
            loanDisbursement.setLoanAmount(Double.parseDouble(dMap.get("loanAmount")));
            loanDisbursement.setDisbursementCurrency(dMap.get("disbursementCurrency"));
            loanDisbursement.setConversionRate(Double.parseDouble(dMap.get("conversionRate")));
            loanDisbursement.setStatus(dMap.get("status"));
            loanDisbursement.setExpiryDate(formattedExpireDate);

            loanDisbursementRepository.save(loanDisbursement);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }

        return successMessage(SUCCESS, loanDisbursement);
    }

    @GetMapping("/list")

    public Map list() {
        return null;
    }

    @PostMapping("/update")
    public Map update() {
        return null;
    }

    @PostMapping("/delete")
    public Map delete() {
        return null;
    }
}

