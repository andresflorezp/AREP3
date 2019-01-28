package edu.eci.arsw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeServer {

	DatagramSocket socket;

	public DatagramTimeServer() {
		try {
			socket = new DatagramSocket(4098);
		} catch (SocketException ex) {
			Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void startServer() throws InterruptedException {
		byte[] buf = new byte[256];
		
		try {
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			String dString = new Date().toString();
			buf = dString.getBytes();
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buf, buf.length, address, port);
			socket.send(packet);
			Thread.currentThread().wait(5000);
			String[] L = {"1"};
			DatagramTimeClient.main(L);

		} catch (IOException ex) {
			Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
		}

		socket.close();
	}

	public static void main(String[] args) throws InterruptedException {
		DatagramTimeServer ds = new DatagramTimeServer();
		ds.startServer();
	}
}