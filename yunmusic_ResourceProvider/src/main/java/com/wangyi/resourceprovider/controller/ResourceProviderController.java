package com.wangyi.resourceprovider.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.wangyi.common.vo.R;
import com.wangyi.resourceprovider.core.config.OssConfig;
import com.wangyi.resourceprovider.service.ResourceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class ResourceProviderController {

    @Autowired
    private ResourceProviderService resourceProviderService;

    @RequestMapping(value = "/provider/resource/uploadResource.do",method = RequestMethod.POST)
    public R uploadResource(@RequestPart("file") MultipartFile file) throws IOException {
        return resourceProviderService.uploadResource(file);
    }

    @RequestMapping(value = "/provider/resource/uploadResources.do",method = RequestMethod.POST)
    public R uploadResources(@RequestPart("file") MultipartFile[] file) throws IOException {
        return resourceProviderService.uploadResources(file);
    }

    @RequestMapping(value = "/provider/resource/downloadResource.do",method = RequestMethod.GET)
    public void downloadResource(@RequestParam("fileName") String fileName,HttpServletResponse response) throws IOException {

        // resourceProviderService.downloadResource(fileName,response);
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject("ccq1904", fileName);

        String fileName1 = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName1);

        ServletOutputStream outputStream = response.getOutputStream();

        // 读取文件内容。
        BufferedInputStream bis = new BufferedInputStream(ossObject.getObjectContent());
        byte[] data=new byte[1024*4];
        int len;
        while ((len = bis.read(data)) != -1) {
            outputStream.write(data,0,len);
        }
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        bis.close();
        outputStream.close();
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @DeleteMapping("/provider/resource/delResource.do")
    public R delResource(String fileName) throws IOException {
        return resourceProviderService.delResource(fileName);
    }

}
