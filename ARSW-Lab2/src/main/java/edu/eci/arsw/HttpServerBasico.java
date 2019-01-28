package edu.eci.arsw;

import java.net.*;
import java.io.*;

public class HttpServerBasico {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(46000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		PrintWriter out;
		BufferedReader in;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Received: " + inputLine);
				if (!in.ready()) {
					break;
				}
			}

			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/html" + "\r\n");
			out.println("<!DOCTYPE html>" + "\r\n");
			out.println("<html>" + "\r\n");
			out.println("<head>" + "\r\n");
			out.println("<meta charset=\"UTF-8\">" + "\r\n");
			out.println("<title>Title of the document</title>" + "\r\n");
			out.println("</head>" + "\r\n");
			out.println("<body>" + "\r\n");
			out.println("<h1>My Web Site</h1>" + "\r\n");
			out.println("</body>" + "\r\n");
			out.println("</html>" + "\r\n");
			out.flush();
			
		
	}
}