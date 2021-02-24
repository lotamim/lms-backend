package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChargeService extends BaseService {
    @Autowired
    private ChargeRepository chargeRepository;

    public Map saveOrUpdate(Map<String,String> dMap) {
        try {

        } catch (Exception ex) {

        }
        return null;
    }


    public Map delete(Map<String,String> dMap) {
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
