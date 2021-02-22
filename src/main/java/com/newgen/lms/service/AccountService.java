package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService extends BaseService {
    @Autowired
    private AccountRepository accountRepository;

    public Map saveOrUpdate(Map<String, String> dMap) {
        try {

        } catch (Exception ex) {

        }
        return null;
    }

    public Map delete(Map<String, String> dMap) {
        try {

        } catch (Exception ex) {

        }
        return null;
    }

    public Map list() {
        try {

        } catch (Exception ex) {

        }
        return null;
    }
}
