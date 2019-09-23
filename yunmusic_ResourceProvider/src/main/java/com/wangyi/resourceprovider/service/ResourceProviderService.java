package com.wangyi.resourceprovider.service;

import com.wangyi.common.vo.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ResourceProviderService {

    R<String> uploadResource(MultipartFile file) throws IOException;

    R<List<String>> uploadResources(MultipartFile[] file) throws IOException;

    void downloadResource(String fileName,HttpServletResponse response) throws IOException;

    R delResource(String fileName) throws IOException;
}
