package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.Constant;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest , @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        if(!fileDirectory.exists()){
            if(!fileDirectory.mkdir()){
                throw new CcbException(CcbExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse.success(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/" + newFileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ApiRestResponse.error(CcbExceptionEnum.UPLOAD_FAILED);
        }
    }

    private URI getHost(URI uri){
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(),uri.getUserInfo(),uri.getHost(),uri.getPort(),null,null,null);

        }catch (URISyntaxException e){
            effectiveURI = null;
        }
        return effectiveURI;
    }
}
