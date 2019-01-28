package edu.eci.arsw;

import java.net.*;
import java.io.*;

public class HttpServer {

	private static ServerSocket serverSocket = null;

	private static boolean continuar = true;

	private static Socket clientSocket = null;

	public static void main(String[] args) throws IOException {
		//Iniciar el serverSocket
		try {
		   serverSocket = new ServerSocket(38000);
		   /*Siempre espera*/
		   int counter = 0;
		   while (continuar) {
			 counter ++;
			 System.out.println("Listo para recibir ... " + counter);
			 clientSocket = serverSocket.accept();
//			 writeRequest(clientSocket.getInputStream());
			 String path = getPageRequest(clientSocket.getInputStream());
			 if (path.equals("/dog.xhtml"))  putImage(clientSocket.getOutputStream());
			 else if (path.equals("/index.xhtml")) putIndex(clientSocket.getOutputStream());
			 else putPath(clientSocket.getOutputStream());
			 clientSocket.close();
			}//while	  
		 } catch (IOException e) {
			   System.err.println("Could not listen on port: 35000.");
			   System.exit(1);
		} finally {
		  serverSocket.close();
		}	  
	 }
	
	public static String getPageRequest(InputStream is) throws IOException {
		is.mark(0);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null ;
		while((inputLine=in.readLine())!=null) {
			if (!in.ready()) break;
			if (inputLine.contains("GET")) {
				String [] get = inputLine.split(" ");
				return get[1];
			}
			break;
		}
		return "path"	;
	}
	
	public static void putPath(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" +"\r\n");
		response.println("<!DOCTYPE html>" +"\r\n");
		response.println("<html>" +"\r\n");
		response.println("<head>" +"\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Title of the document</title>" + "\r\n");
		response.println("</head>" +"\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1>My Web Site</h1>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n"); 
		response.flush();
		response.close();
	}

	public static void putIndex(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" +"\r\n");
		response.println("<!DOCTYPE html>" +"\r\n");
		response.println("<html>" +"\r\n");
		response.println("<head>" +"\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" +"\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1 style=\"backgroung=green\">Index</h1>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n"); 
		response.flush();
		response.close();
	}
	
	public static void putImage(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" +"\r\n");
		response.println("<!DOCTYPE html>" +"\r\n");
		response.println("<html>" +"\r\n");
		response.println("<head>" +"\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" +"\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"https://c.stocksy.com/a/p8M600/z9/1515083.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n"); 
		response.flush();
		response.close();
	}
	
	public static void writeRequest(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null ;
		while((inputLine=in.readLine())!=null) {
			if (!in.ready()) break;
			System.out.println("Received: " + inputLine);
		}
		//if (in!=null) in.close();
	}


}