
package com.newgen.hrm.service;

import com.newgen.hrm.common.Constants;
import com.newgen.hrm.common.FileDownLoadController;
import com.newgen.hrm.common.UploadUtils;
import com.newgen.hrm.model.Organization;
import com.newgen.hrm.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationService {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String ORGANIZATION_LIST = "organizationList";

    @Autowired
    private OrganizationRepository organizationRepository;

    public Map save(MultipartFile file, Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        try {
            Organization organization = new Organization();
            organization.setOrganizationName(dMap.get("name"));
            organization.setEmail(dMap.get("email"));
            organization.setAddress(dMap.get("address"));
            organization.setPhoneNumber(dMap.get("extnumber"));
            organization.setRemarks(dMap.get("remarks"));
            if (file != null) {
                organization.setOrganizationLogo(UploadUtils.uniqueCodeGeneratorForFile(file.getOriginalFilename()));
            }
            organizationRepository.save(organization);
            if (organization.getId() != null && organization.getOrganizationLogo() != null) {
                UploadUtils.upload(file, organization.getOrganizationLogo());
            }
            msgMap.put(SUCCESS, "Organization save successful !");
            return msgMap;

        } catch (Exception ex) {
            msgMap.put(ERROR, ex.getMessage());
            return msgMap;
        }
    }


    public Map list() {
        Map<String, List<Organization>> map = new HashMap<>();
        try {
            List<Organization> organizationList = organizationRepository.findAll();
            map.put(ORGANIZATION_LIST, organizationList);
            return map;
        } catch (Exception ex) {
            map.put(ERROR, new ArrayList<>());
            return map;
        }
    }

    public Map select(Map<String, String> dMap) {
        Map<String, Organization> msgMap = new HashMap<>();
        try {
            Organization organization = organizationRepository.findById(Long.parseLong(dMap.get("orgId"))).get();
            String uri = MvcUriComponentsBuilder
                    .fromMethodName(FileDownLoadController.class, "fileDownload",
                            Paths.get(Paths.get(Constants.FILE_PATH) + "/" + organization.getOrganizationLogo()).getFileName().toString())
                    .build().toString();
            organization.setOrganizationLogo(uri);
            msgMap.put("organization", organization);
            return msgMap;
        } catch (Exception ex) {
            return msgMap;
        }
    }
}
