package com.newgen.lms.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BaseService {
    public Map<String, String> errorMessage(String message, Object obj) {
        Map<String, String> dMap = new HashMap<>();
        dMap.put("error", message);
        return dMap;
    }

    public Map<String, String> successMessage(String message, Object obj) {
//        obj.getClass().getName()
        Map<String, String> dMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        dMap.put("success", sb.toString());
        return dMap;
    }
}
