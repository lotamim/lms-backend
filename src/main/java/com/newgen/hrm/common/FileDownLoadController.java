package com.newgen.hrm.common;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RequestMapping("/download")
public class FileDownLoadController {

    @GetMapping(value = "/file/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> fileDownload(@PathVariable String fileName) throws MalformedURLException {
        Path path = Paths.get(Constants.FILE_PATH).resolve(fileName);
        Resource file = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
