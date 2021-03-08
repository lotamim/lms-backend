package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.common.Status;
import com.newgen.lms.common.Type;
import com.newgen.lms.model.DrawdownLevelOfAuthority;
import com.newgen.lms.model.LevelOfAuthority;
import com.newgen.lms.repository.DrawdownLevelOfAuthorityRepository;
import com.newgen.lms.repository.LevelOfAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class DrawdownLevelOfAuthorityService extends BaseService {

    @Autowired
    private DrawdownLevelOfAuthorityRepository drawdownLevelOfAuthorityRepository;

    private static final String SUCCESS = "Date save successful !";
    private static final String ERROR = "Fail to data save !";

    public Map save(Map<String, String> dMap) {
        DrawdownLevelOfAuthority drawdownLevelOfAuthority = null;
        try {
            drawdownLevelOfAuthority = new DrawdownLevelOfAuthority();
            drawdownLevelOfAuthority.setDrawdownId(Long.parseLong(dMap.get("drawdownId")));
            drawdownLevelOfAuthority.setEmployeeId(Long.parseLong(dMap.get("employeeId")));
            if (dMap.get("approverTypeId").equals(Type.APPROVER.toString())) {
                drawdownLevelOfAuthority.setType(Type.APPROVER);
                drawdownLevelOfAuthority.setStatus(Status.NOT_YET_REVIEWED);
            } else if (dMap.get("approverTypeId").equals(Type.RECOMMENDER.toString())) {
                drawdownLevelOfAuthority.setType(Type.RECOMMENDER);
                drawdownLevelOfAuthority.setStatus(Status.NOT_YET_REVIEWED);
            } else {
                drawdownLevelOfAuthority.setType(Type.INITIATER);
                drawdownLevelOfAuthority.setStatus(Status.WAITING_FOR_REVIEWED);
            }
            drawdownLevelOfAuthorityRepository.save(drawdownLevelOfAuthority);
        } catch (Exception ex) {
            return errorMessage(ERROR, drawdownLevelOfAuthority);
        }
        return successMessage(SUCCESS, drawdownLevelOfAuthority);
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
