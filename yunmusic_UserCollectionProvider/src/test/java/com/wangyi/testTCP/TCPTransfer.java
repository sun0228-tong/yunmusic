package com.wangyi.testTCP;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTransfer {

    @Test
    public void server() throws IOException {

        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket = serverSocket.accept();
        // is
        InputStream is = socket.getInputStream();
        byte[] buf = new byte[1024];
        int length = is.read(buf);
        System.out.println(new String(buf,0,length));
        // os
        OutputStream os = socket.getOutputStream();
        os.write("服务器：你好客户端".getBytes());

        socket.close();
        serverSocket.close();
    }

    @Test
    public void client() throws IOException {
        Socket socket = new Socket("192.168.88.1", 10000);

        //os
        OutputStream os = socket.getOutputStream();
        os.write("收到客户端信息".getBytes());
        // is
        InputStream is = socket.getInputStream();
        byte[] buf = new byte[1024];
        int length = is.read(buf);
        System.out.println(new String(buf,0,length));

        socket.close();

    }
}
