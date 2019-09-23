package com.wangyi.resourceapi.service;

import com.wangyi.common.vo.R;
import com.wangyi.resourceapi.config.FeignMultipartSupportConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "ResourceProvider",configuration = FeignMultipartSupportConfig.class)
public interface ResourceApiService {

    @RequestMapping(value = "/provider/resource/uploadResource.do",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R uploadResource(@RequestPart("file") MultipartFile file);

    @RequestMapping(value = "/provider/resource/uploadResources.do",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R uploadResources(@RequestPart("file") MultipartFile[] file);

    @RequestMapping(value = "/provider/resource/downloadResource.do",method = RequestMethod.GET)
    Response downloadResource(@RequestParam("fileName") String fileName);

    @DeleteMapping("/provider/resource/delResource.do")
    R delResource(@RequestParam("fileName") String fileName);
}
