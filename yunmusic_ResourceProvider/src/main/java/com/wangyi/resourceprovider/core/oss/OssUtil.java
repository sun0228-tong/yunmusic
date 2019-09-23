package com.wangyi.resourceprovider.core.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.wangyi.resourceprovider.core.config.OssConfig;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @program: NewRetail
 * @description: 阿里云的OSS 对象存储
 * @author: suntong
 * @create: 2019-08-15 14:34
 */
public class OssUtil {
    /**
     *创建OSS存储空间
     * @param name 存储空间名称 */
    public static void createBucket(String name){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 创建存储空间。
        Bucket bucket=ossClient.createBucket(name);
        System.out.println(bucket.getName());
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    /**
     * 上传内容 字符串
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param msg 存储的内容
     * @return 访问路径 */
    public static String sendMsg(String name,String objName,String msg){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(name, objName, new ByteArrayInputStream(msg.getBytes()));
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
    /**
     * 上传内容 对象
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param obj 存储的内容
     * @return 访问路径 */
    public static String sendObject(String name,String objName,Object obj){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(name, objName, new ByteArrayInputStream(JSON.toJSONString(obj).getBytes()));
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
    /**
     * 上传内容 对象
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param data 存储的内容
     * @return 访问路径 */
    public static String sendByte(String name,String objName,byte[] data){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(name, objName, new ByteArrayInputStream(data));
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
    /**
     * 上传内容 输入流
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param inputStream 存储的内容
     * @return 访问路径 */
    public static String sendByte(String name, String objName, InputStream inputStream){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(name, objName, inputStream);
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
    /**
     * 上传内容 输入流
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param resurl 存储的内容
     * @return 访问路径 */
    public static String sendByte(String name, String objName, String resurl) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        // 上传网络流。
        InputStream inputStream = new URL(resurl).openStream();
        ossClient.putObject(name, objName, inputStream);
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
    /**
     * 断点续传上传内容 输入流
     * @param name 存储空间名称
     * @param objName 对象名称
     * @param filePath 要上传的文件
     * @return 访问路径 */
    public static String send(String name, String objName, String filePath) throws Throwable {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");
        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(name,objName);
        // 通过UploadFileRequest设置单个参数。
        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(filePath);
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
        // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
        // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile("fileup.log");
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);
        // 设置URL过期时间为1年。
        Date expiration = new Date(System.currentTimeMillis() + 3650*24*3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(name, objName, expiration);

        return url.toString();
    }
    /**
     * 下载
     * */
    public static ByteArrayOutputStream downFile(String name,String objName) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(name, objName);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        // 读取文件内容。
        BufferedInputStream bis = new BufferedInputStream(ossObject.getObjectContent());
        byte[] data=new byte[1024];
        int len;
        while (true) {
            len = bis.read(data);
            if (len == -1){ break;}
            baos.write(data,0,len);
        }
// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        bis.close();
// 关闭OSSClient。
        ossClient.shutdown();
        return baos;
    }
    /**
     * 文件列表 分页获取
     * */
    public static  List<OSSObjectSummary> filePageList(String name) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        final int maxKeys = 200;
        String nextMarker = null;
        ObjectListing objectListing;
        List<OSSObjectSummary> sums=null;
        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(name).withMarker(nextMarker).withMaxKeys(maxKeys));
            sums = objectListing.getObjectSummaries();
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        // 关闭OSSClient。
        ossClient.shutdown();
        return sums;
    }
    /**
     * 删除文件
     * */
    public static  void delObject(String name,String objName) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssConfig.endpoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
        ossClient.deleteObject(name,objName);
        ossClient.shutdown();
    }
}