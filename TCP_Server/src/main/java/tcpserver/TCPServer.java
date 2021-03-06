/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import lombok.SneakyThrows;
import util.FileUtility;

/**
 *
 * @author musamammadov
 */
public class TCPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readAsByte();
    }

    @SneakyThrows
    public static void readAsByte() {
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);//localhost, 127.0.0.1, ipconfig

        while (true) {
            System.out.println("I am waiting for new client");
            Socket connection = ourFirstServerSocket.accept();//I will wait until new socket has been connected
            System.out.println("New socket has been connected");
            
            DataInputStream dataStream = new DataInputStream(connection.getInputStream());
              
            byte[] arr = readMessage(dataStream);
            System.out.println("message length2="+arr.length);//500
            FileUtility.writeBytes(arr, "/Users/MusaMammadov/Desktop/Musa.jpg");
        }
    }

    @SneakyThrows
    public static byte[] readMessage(DataInputStream din) {
        int msgLen = din.readInt();//1
        System.out.println("message length1="+msgLen);//500
        byte[] msg = new byte[msgLen];

        din.readFully(msg);
        return msg;
    }

    @SneakyThrows
    public static void readAsString() {
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);//localhost, 127.0.0.1, ipconfig

        while (true) {
            System.out.println("I am waiting for new client");
            Socket connection = ourFirstServerSocket.accept();//I will wait until new socket has been connected
            System.out.println("New socket has been connected");
            InputStream is = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(is);

            BufferedReader bReader = new BufferedReader(reader);//

            String messageFromClient = bReader.readLine();
            System.out.println("Client says that:" + messageFromClient);
        }
    }

}
