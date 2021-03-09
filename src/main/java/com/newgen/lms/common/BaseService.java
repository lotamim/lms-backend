package com.newgen.lms.common;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Date formattedDate(String date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("dd-mm-yyyy");
        Date formatted_date = formater.parse(date);
        return formatted_date;
    }
}
