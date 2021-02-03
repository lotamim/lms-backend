package com.newgen.lms.service;

import com.newgen.lms.model.ApplicationUser;
import com.newgen.lms.model.ResetPasswordHistory;
import com.newgen.lms.repository.ApplicationUserRepository;
import com.newgen.lms.repository.RestPasswordHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResetPasswordService {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String PASSWORD_HISTORY_LIST = "passwordHistoryList";

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private RestPasswordHistoryRepository restPasswordHistoryRepository;

    public Map<String, String> resetPassword(Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        try {
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(dMap.get("username"));
            applicationUser.setPassword(passwordEncoder.encode(dMap.get("password")));
            applicationUserRepository.save(applicationUser);

            /* Note : This code is for password change history */
            ResetPasswordHistory resetPasswordHistory = new ResetPasswordHistory();
            resetPasswordHistory.setApplicationUserId(applicationUser.getId());
            resetPasswordHistory.setRemarks(dMap.get("remarks"));
            restPasswordHistoryRepository.save(resetPasswordHistory);
            msgMap.put(SUCCESS, "Successfully rest password !");
            return msgMap;
        } catch (Exception ex) {
            msgMap.put(ERROR, ex.getMessage());
            return msgMap;
        }
    }

    public List<Map<?,?>> getResetPasswordHistory() {
        return restPasswordHistoryRepository.getHistoryList();
    }
}
