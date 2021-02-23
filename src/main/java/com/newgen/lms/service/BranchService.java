package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Branch;
import com.newgen.lms.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BranchService extends BaseService {
    private static final String SUCCESS = "Data save successful !";
    private static final String ERROR = "Data is not found !";

    @Autowired
    private BranchRepository branchRepository;


    public Map save(Map<String, String> dMap) {  // validation not yet completed
        Branch branch = null;
        try {
            if (dMap.get("id") != "") {
                branch = branchRepository.findById(Long.parseLong(dMap.get("id"))).get();
            } else {
                branch = new Branch();
            }
            branch.setBranchName(dMap.get("branchName"));
            branch.setAddress(dMap.get("address"));
            branch.setPhone(dMap.get("phone"));
            branch.setEmail(dMap.get("email"));
            branch.setContactPerson(dMap.get("contactPerson"));
            branch.setDistrict(dMap.get("district"));
            branchRepository.save(branch);
        } catch (Exception ex) {
            return errorMessage(ERROR, branch);
        }
        return successMessage(SUCCESS, branch);
    }

    public Map delete(Map<String, String> dMap) {
        Branch branch = null;
        try {
            branch = branchRepository.findById(Long.parseLong(dMap.get("id"))).get();
            branch.setDeleted(true);
            branchRepository.save(branch);
        } catch (Exception ex) {
            errorMessage(ERROR, branch);
        }
        return successMessage(SUCCESS, branch);
    }

    public Map list() {
        try {

        } catch (Exception ex) {

        }
        return null;
    }
}
