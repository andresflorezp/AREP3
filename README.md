# Laboratorio 2 ARSW


## Empezando

Para clonar el archivo:

>git clone https://github.com/andresflorezp/ARSW-LAB-2.git
>
### Prerrequisitos
* Maven
* Java
* Git

## Trabajando con URLs

### URL
URL es la abreviación de Uniform Resource Locator, y es básicamente una dirección para localizar recursos en internet. Una idea clara de
como son las URLs la encontramos en nuestro navegador de internet. Así, la forma general de una URL es la siguiente:

    <protocolo>://<servidor>:<puerto>/<dirección del recurso en el servidor>
    
### Métodos para leer información de un objeto URL

    * GetProtocol.

    * GetAuthority.

    * GetHost.

    * GetPort.

    * GetPath.

    * GetQuery.

    * GetFile.

    * GetRef.

## Ejercicio 1

Escriba un programa en el cual usted cree un objeto URL e imprima en pantalla cada uno de los datos que retornan los 8 métodos de la sección anterior.

### Codificación

```java
import java.io.*;
import java.net.*;

public class URLReader {

	public static void main(String[] args) throws Exception {
    
		URL google = new URL("http://www.google.com/");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				System.out.println(inputLine);
			}
			System.out.println(google.getFile());
			System.out.println(google.getAuthority());
			System.out.println(google.getProtocol());
			System.out.println(google.getPath());
			System.out.println(google.getHost());
			System.out.println(google.getPort());
			System.out.println(google.getQuery());
			System.out.println(google.getFile());
			System.out.println(google.getRef());
		} catch (IOException x) {
			System.err.println(x);
		}
	}
}
```

## Ejercicio 2

Escriba una aplicación browser que pregunte una dirección URL al usuario y que lea datos de esa dirección y que los almacene en un archivo con el nombre resultado.html. Luego intente ver este archivo en el navegador.

### Codificación

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class LectorURL {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String urluser = in.readLine();
		String data="";
	
	    	URL pagina = new URL("http://www."+urluser);
	    
	    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(pagina.openStream()))) {
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				data+=inputLine;
			}
		} catch (IOException x) {
			System.err.println(x);
		}
		
    		BufferedWriter bw = null;
	    	FileWriter fw = null;

	    try {
		File file = new File("resultado.html");
		// Si el archivo no existe, se crea!
		if (!file.exists()) {
		    file.createNewFile();
		}
		// flag true, indica adjuntar informacion al archivo.
		fw = new FileWriter(file.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);
		bw.write(data);
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
	        try {
                //Cierra instancias de FileWriter y BufferedWriter.
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
```

## Sockets

Los sockets son los puntos ﬁnales del enlace de comunicación entre dos programas ejecutándose en la red. Cada socket esta vinculado a un puerto especíﬁco.

Para que dos programas puedan comunicarse entre sí es necesario que se cumplan ciertos requisitos:

* Que un programa sea capaz de localizar al otro.

* Que ambos programas sean capaces de intercambiarse cualquier secuencia de octetos, es decir, datos relevantes a su finalidad.

Para ello son necesarios los dos recursos que originan el concepto de socket:

* Un par de direcciones del protocolo de red (dirección IP, si se utiliza el protocolo TCP/IP), que identifican la computadora de origen y la remota.

* Un par de números de puerto, que identifican a un programa dentro de cada computadora.

Los sockets permiten implementar una arquitectura cliente-servidor. La comunicación debe ser iniciada por uno de los programas que se denomina programa "cliente". El segundo programa espera a que otro inicie la comunicación, por este motivo se denomina programa "servidor".

## Ejercicio 4.3.1

Escriba un servidor que reciba un número y responda el cuadrado de este número.

### Codificacion Cliente

```java
import java.io.*;
import java.net.*;

public class SocketCliente {
	public static void main(String[] args) throws IOException {
	
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			echoSocket = new Socket("127.0.0.1", 8182);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Donâ€™t know about host!.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldnâ€™t get I/O for " + "the connection to: localhost.");
			System.exit(1);
		}
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}
}
```
### Codificacion Servidor

```java
import java.net.*;
import java.io.*;

public class SocketServidor {

	public static void main(String[] args) throws IOException {
	
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8081);
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
			System.out.println("Mensaje: " + inputLine);
			int number=Integer.parseInt(inputLine);
			outputLine = "Respuesta " + (number*number);
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
```

## Ejercicio 4.3.2

Escriba un servidor que pueda recibir un número y responda con un operación sobre este número. Este servidor puede recibir un mensaje que empiece por “fun:”, si recibe este mensaje cambia la operación a las especiﬁcada. El servidor debe responder las funciones seno, coseno y tangente. Por defecto debe empezar calculando el coseno. Por ejemplo, si el primer número que recibe es 0, debe responder 1, si después recibe π/2 debe responder 0, si luego recibe “fun:sin” debe cambiar la operación actual a seno, es decir a a partir de ese momento debe calcular senos. Si enseguida recibe 0 debe responder 0.

### Codificacion Cliente

```java
import java.io.*;
import java.net.*;

public class SocketCliente {
	public static void main(String[] args) throws IOException {
	
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			echoSocket = new Socket("127.0.0.1", 8182);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Donâ€™t know about host!.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldnâ€™t get I/O for " + "the connection to: localhost.");
			System.exit(1);
		}
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}
}
```

### Codificacion Servidor
```java
import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class SocketOperacionesServidor {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8182);
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
		String inputLine, outputLine, funcion;
		funcion="coseno";
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(inputLine);
			String temp=st.nextToken();
			double val =0.0;
			outputLine = "Respuesta ";
			if (isNumeric(temp)== true) {
				if(funcion.equals("coseno")) {
					Double fa = Double.parseDouble(temp);
					val = Math.cos(fa);
					outputLine+=val;
				}
				else if(funcion.equals("seno")) {
					Double fa = Double.parseDouble(temp);
					val = Math.sin(fa);
					outputLine+=val;
				}
				else {
					Double fa = Double.parseDouble(temp);
					val = Math.tan(fa);
					outputLine+=val;
				}
			}
			else {
				if(temp.equals("fun:sin")) {
					funcion="seno";
					outputLine+="funcion acutal seno";
				}
				else if(temp.equals("fun:cos") || temp.equals("fun:")) {
					funcion="coseno";
					outputLine+="funcion acutal coseno";
				}
				else if(temp.equals("fun:tan")) {
					funcion="tangente";
					outputLine+="funcion acutal tangente";
				}
			}
			out.println(outputLine);
			if (temp.equals("Bye"))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
	
	
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
```

## Autores

* **Andres Giovanne Florez Perez**  ARSW-LAB1 - [andresflorezp] (https://github.com/andresflorezp)

* **Juan Nicolas Nontoa Caballero**  ARSW-LAB1 - [nontoa] (https://github.com/nontoa)

Consulte también la lista de [colaboradores] (https://github.com/andresflorezp/ARSW-LAB-2/graphs/contributors) que participaron en este proyecto.

## licencia

Este proyecto está licenciado bajo la Licencia MIT - vea el archivo [LICENSE](LICENSE) para más detalles.
