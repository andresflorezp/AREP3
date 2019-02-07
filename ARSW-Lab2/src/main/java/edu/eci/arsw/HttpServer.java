package edu.eci.arsw;

import java.net.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;

public class HttpServer {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static ObjectInputStream entrada;
	public static void main(String[] args) throws IOException {

		try {
			serverSocket = new ServerSocket(39000);

			for (int cnt = 1;; cnt++) {
				System.out.println("Listo para recibir ... " + cnt);
				clientSocket = serverSocket.accept();
				String path = getPageRequest(clientSocket.getInputStream());
				System.out.println(path);
				//System.out.println(LeerFichero.muestraContenido("Resources/index.html"));
				PrintWriter response = new PrintWriter(clientSocket.getOutputStream());
				//response.println("HTTP/1.1 200 OK");
				//response.println("Content-Type: text/html" + "\r\n");
				if(path.endsWith(".jpeg")) {
					response.println("HTTP/1.1 200 OK");
					response.println("Content-Type: image/jpeg\r\n");
					procesarImagen(clientSocket.getOutputStream());
					
					
					
				}
				response.println(LeerFichero.muestraContenido("Resources"+path));
				

/*
				if (path.equals("/html1"))
					HTML(clientSocket.getOutputStream());
				else if (path.equals("/html2"))
					html2(clientSocket.getOutputStream());
				else if (path.equals("/image1"))
					image1(clientSocket.getOutputStream());
				else if (path.equals("/image2"))
					image2(clientSocket.getOutputStream());
				else if (path.equals("/image3"))
					image3(clientSocket.getOutputStream());
				else if (path.equals("/image4"))
					image4(clientSocket.getOutputStream());
				else if (path.equals("/css1"))
					css1(clientSocket.getOutputStream());
				*/
				response.flush();
				response.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 38000.");
			System.exit(1);
		} finally {
			serverSocket.close();
		}
	}

	
	private static void procesarImagen(OutputStream os) throws IOException {
        do { // procesar los mensajes enviados por el cliente
 
            // leer el mensaje y mostrarlo en pantalla
            try {
                byte[] bytesImagen = (byte[]) entrada.readObject();
                ByteArrayInputStream entradaImagen = new ByteArrayInputStream(bytesImagen);
                BufferedImage bufferedImage = ImageIO.read(entradaImagen);
 
                String nombreFichero=System.getProperty("user.home")+File.separatorChar+"captura.jpeg";
                System.out.println("Generando el fichero: "+nombreFichero );
                FileOutputStream out = new FileOutputStream(nombreFichero);
                // esbribe la imagen a fichero
                ImageIO.write(bufferedImage, "jpeg", out);
                PrintWriter response = new PrintWriter(os, true);
                response.println(out);
                
            }
 
            // atrapar problemas que pueden ocurrir al tratar de leer del cliente
            catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
                System.out.println( "\nSe recibió un tipo de objeto desconocido" );
            }
 
        } while ( true );
 
    } // fin del método pro
	
	public static String getPageRequest(InputStream is) throws IOException {
		is.mark(0);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null;
		while ((inputLine = in.readLine()) != null) {
			if (!in.ready())
				break;
			if (inputLine.contains("GET")) {
				String[] get = inputLine.split(" ");
				return get[1];
			}
			break;
		}
		return "path";
	}
	


	// HTML
	public static void HTML(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Title of the document</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1>My Web Site</h1>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void html2(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>CÃ³mo hacer una pÃ¡gina web con HTML</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1>CÃ³mo hacer una pÃ¡gina web con HTML</h1>" + "\r\n");
		response.println(
				"<p> En el post de hoy voy a enseÃ±arte <strong>cÃ³mo hacer una pÃ¡gina web con HTML</strong>, pero antes â€¦</p>"
						+ "\r\n");
		response.println("<h2>Conceptos bÃ¡sicos sobre pÃ¡ginas web</h2>" + "\r\n");
		response.println("<p>Â¿CuÃ¡l es entonces la diferencia entre una pÃ¡gina web y un sitio web?â€¦</p>" + "\r\n");
		response.println("<h3>Diferencias entre una pÃ¡gina web y un sitio web</h3>" + "\r\n");
		response.println(
				"<p>Una <a href=â€�https://es.wikipedia.org/wiki/P%C3%A1gina_webâ€�>pÃ¡gina web</a> es un <strong>Ãºnico documento electrÃ³nico</strong> queâ€¦</p>"
						+ "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	// IMAGENES

	public static void image1(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"https://c.stocksy.com/a/p8M600/z9/1515083.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image2(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println(
				"<img src=\"www.yamaha.com/YECDealerMedia/adgraphs/logos/nvideocd.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image3(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println(
				"<img src=\"http://www.yamaha.com/YECDealerMedia/adgraphs/logos/nvideocd.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image4(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"http://chandra.harvard.edu/photo/2017/arp299/arp299.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	// CSS

	public static void css1(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/css" + "\r\n");
		response.println("p {" + "\r\n");
		response.println("color: red;" + "\r\n");
		response.println("width: 500px;" + "\r\n");
		response.println(" border: 1px solid black;" + "\r\n");
		response.println("}" + "\r\n");
		response.flush();
		response.close();

	}

	public static void writeRequest(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null;
		while ((inputLine = in.readLine()) != null) {
			if (!in.ready())
				break;
			System.out.println("Received: " + inputLine);
		}
	}

}