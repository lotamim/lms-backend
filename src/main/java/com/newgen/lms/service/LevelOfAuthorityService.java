package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.common.Status;
import com.newgen.lms.common.Type;
import com.newgen.lms.model.LevelOfAuthority;
import com.newgen.lms.repository.LevelOfAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LevelOfAuthorityService extends BaseService {
    private static final String SUCCESS = "Date save successful !";
    private static final String ERROR = "Fail to data save !";

    @Autowired
    private LevelOfAuthorityRepository levelOfAuthorityRepository;

    public Map save(Map<String, String> dMap) {
        LevelOfAuthority levelOfAuthority = null;
        try {
            levelOfAuthority = new LevelOfAuthority();
            levelOfAuthority.setSanctionId(Long.parseLong(dMap.get("sanctionId")));
            levelOfAuthority.setEmployeeId(Long.parseLong(dMap.get("employeeId")));
            if (dMap.get("approverTypeId").equals(Type.APPROVER.toString())) {
                levelOfAuthority.setType(Type.APPROVER);
                levelOfAuthority.setStatus(Status.NOT_YET_REVIEWED);
            } else if (dMap.get("approverTypeId").equals(Type.RECOMMENDER.toString())) {
                levelOfAuthority.setType(Type.RECOMMENDER);
                levelOfAuthority.setStatus(Status.NOT_YET_REVIEWED);
            } else {
                levelOfAuthority.setType(Type.INITIATER);
                levelOfAuthority.setStatus(Status.WAITING_FOR_REVIEWED);
            }
            levelOfAuthorityRepository.save(levelOfAuthority);
        } catch (Exception ex) {
            return errorMessage(ERROR, levelOfAuthority);
        }
        return successMessage(SUCCESS, levelOfAuthority);
    }

    public Map update(Map<String, String> dMap) {
        return null;
    }

    public Map delete(Map<String, String> dMap) {
        return null;
    }

    public Map list(Map<String, String> dMap) {
        return null;
    }
}
