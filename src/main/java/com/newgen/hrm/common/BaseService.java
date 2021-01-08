package com.newgen.hrm.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
public abstract class BaseService {
    abstract Map save(Map dMap, MultipartFile file);

    abstract Map update(Map dMap, MultipartFile file);

    abstract Map delete(Map dMap);

    abstract Map select(Map dMap);

    abstract Map List(Map dMap);
}
