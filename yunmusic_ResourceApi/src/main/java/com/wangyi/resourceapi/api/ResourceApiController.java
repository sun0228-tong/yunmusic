package com.wangyi.resourceapi.api;

import com.wangyi.common.vo.R;
import com.wangyi.resourceapi.service.ResourceApiService;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@Api(value = "资源操作",tags = "统一资源操作")
public class ResourceApiController {

    @Autowired
    private ResourceApiService resourceApiService;

    @RequestMapping(value = "/api/resource/uploadResource.do",method = RequestMethod.POST)
    @ApiOperation(value = "实现资源的上传，返回访问路径",notes = "实现资源的上传，返回访问路径")
    public R uploadResource(@RequestParam("file") MultipartFile file) {
        return resourceApiService.uploadResource(file);
    }

    @RequestMapping(value = "/api/resource/uploadResources.do",method = RequestMethod.POST)
    @ApiOperation(value = "实现文件的批量上传，返回访问路径集合",notes = "实现文件的批量上传，返回访问路径集合")
    public R uploadResources(@RequestParam("file") MultipartFile[] file) {
        return resourceApiService.uploadResources(file);
    }

    @RequestMapping(value = "/api/resource/downloadResource.do",method = RequestMethod.GET)
    @ApiOperation(value = "实现文件的下载",notes = "实现文件的下载")
    public ResponseEntity<byte[]> downloadResource(@RequestParam("fileName") String fileName) {
        ResponseEntity<byte[]> result = null;
        InputStream inputStream = null;
        Response response = resourceApiService.downloadResource(fileName);

        // feign文件下载
        try {

            Response.Body body = response.body();
            inputStream = body.asInputStream();
            //以下方式下载文件变小,慎用
//            byte[] b = new byte[inputStream.available()];
//            inputStream.read(b);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024*4];
            while (true) {
                int len = inputStream.read(buf);
                if (len < 0) {
                    break;
                }
                bos.write(buf, 0, len);
            }
            byte[] b = bos.toByteArray();

            HttpHeaders heads = new HttpHeaders();
            String fileName1 = URLEncoder.encode(fileName, "utf-8");
            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName1);
            heads.add("Content-Type", "application/octet-stream");

            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @DeleteMapping("/api/resource/delResource.do")
    @ApiOperation(value = "实现文件的删除",notes = "实现文件的删除")
    public R delResource(@RequestParam("fileName") String fileName) {
        return resourceApiService.delResource(fileName);
    }
}
