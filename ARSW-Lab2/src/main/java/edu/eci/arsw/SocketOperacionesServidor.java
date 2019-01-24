package edu.eci.arsw;

import java.net.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class SocketOperacionesServidor {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8078);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(inputLine);
			String temp=st.nextToken();
			double val =0.0;
			outputLine = "Respuesta ";
			if(temp.equals("fun:sin")) {
				int fa = Integer.parseInt(st.nextToken());
				val = Math.sin(fa);
				outputLine+=val;
			}
			if(temp.equals("fun:cos") || temp.equals("fun:")) {
				int fa = Integer.parseInt(st.nextToken());
				val = Math.cos(fa);
				outputLine+=val;
				
			}
			if(temp.equals("fun:tan")) {
				int fa = Integer.parseInt(st.nextToken());
				val = Math.tan(fa);
				outputLine+=val;
	
			}
			out.println(outputLine);
			if (outputLine.equals("Respuestas: Bye."))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}