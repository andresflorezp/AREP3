package edu.eci.arsw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient implements Runnable {
	DatagramSocket socket;
	
	public DatagramTimeClient() throws SocketException {
		// TODO Auto-generated constructor stub
		socket = new DatagramSocket();
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
			byte[] sendBuf = new byte[256];
			try {
				
				byte[] buf = new byte[256];
				InetAddress address = InetAddress.getByName("127.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4565);
				socket.send(packet);
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Date: " + received);
			} catch (SocketException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnknownHostException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}
	public static void main(String[] args) throws SocketException {
		DatagramTimeClient datagram= new DatagramTimeClient();
		datagram.run();

	}
}