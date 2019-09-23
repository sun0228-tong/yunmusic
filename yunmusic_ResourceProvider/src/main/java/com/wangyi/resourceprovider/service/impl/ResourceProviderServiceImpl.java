package com.wangyi.resourceprovider.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.resourceprovider.core.config.OssConfig;
import com.wangyi.resourceprovider.core.oss.OssUtil;
import com.wangyi.resourceprovider.core.util.FileUtil;
import com.wangyi.resourceprovider.service.ResourceProviderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceProviderServiceImpl implements ResourceProviderService {
    @Override
    public R<String> uploadResource(MultipartFile file) throws IOException {
        String fileName = FileUtil.renameFile(file.getOriginalFilename());
        String url = OssUtil.sendByte("ccq1904", fileName, file.getBytes());
        if (url != null && url.length() > 0) {
            return RUtil.setOK("上传成功",url);
        } else {
            return RUtil.setERROR("上传失败");
        }
    }

    @Override
    public R<List<String>> uploadResources(MultipartFile[] file) throws IOException {
        List<String> list = new ArrayList<>();
        for(MultipartFile file1 : file) {
            String fileName = FileUtil.renameFile(file1.getOriginalFilename());
            String url = OssUtil.sendByte("ccq1904", fileName, file1.getBytes());
            if (url != null && url.length() > 0) {
                list.add(url);
            }
        }
        return RUtil.setOK("批量上传成功",list);
    }

    @Override
    public void downloadResource(String fileName,HttpServletResponse response) throws IOException {
        //ByteArrayOutputStream byteArrayOutputStream = OssUtil.downFile("ccq1904",fileName);
        //String fileName1 = URLEncoder.encode(fileName, "utf-8");
        //response.setHeader("Content-Disposition","attachment;filename=" + fileName1);
        //return RUtil.setOK("下载完成",byteArrayOutputStream);
        // 创建OSSClient实例。

        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject("ccq1904", fileName);

        String fileName1 = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName1);

        ServletOutputStream outputStream = response.getOutputStream();

        // 读取文件内容。
        BufferedInputStream bis = new BufferedInputStream(ossObject.getObjectContent());
        byte[] data=new byte[1024];
        int len;
        while (true) {
            len = bis.read(data);
            if (len == -1){ break;}
           outputStream.write(data,0,len);
        }
// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        bis.close();
        outputStream.close();
// 关闭OSSClient。
        ossClient.shutdown();

    }

    @Override
    public R delResource(String fileName) throws IOException {
        OssUtil.delObject("ccq1904",fileName);
        return RUtil.setOK("删除成功");
    }
}
