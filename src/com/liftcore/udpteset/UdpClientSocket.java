package com.liftcore.udpteset;
import java.io.*;
import java.net.*;

public class UdpClientSocket {
    private byte[] buffer = new byte[1024];

    private DatagramSocket ds = null;

    public UdpClientSocket() throws Exception {
        ds = new DatagramSocket();
    }
    
    public final void setSoTimeout(final int timeout) throws Exception {
        ds.setSoTimeout(timeout);
    }
    public final int getSoTimeout() throws Exception {
        return ds.getSoTimeout();
    }

    public final DatagramSocket getSocket() {
        return ds;
    }

    public final DatagramPacket send(final String host, final int port,
            final byte[] bytes) throws IOException {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress
                .getByName(host), port);
        ds.send(dp);
        return dp;
    }

    public final String receive(final String lhost, final int lport)
            throws Exception {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        String info = new String(dp.getData(), 0, dp.getLength());
        return info;
    }
    
    public final void close() {
        try {
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        UdpClientSocket client = new UdpClientSocket();
        String serverHost = "192.168.1.195";
        int serverPort = 9999;
        System.out.println("send data to server");
        client.send(serverHost, serverPort, ("hello i am java client").getBytes());
        String info = client.receive(serverHost, 4433);
        System.out.println("receive server = " + info);
    }
}