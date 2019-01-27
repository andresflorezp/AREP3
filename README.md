# Laboratorio 2 ARSW


## Empezando

>Para clonar el archivo 

git clone https://github.com/andresflorezp/ARSW-LAB2.git
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

## Autores

* **Andres Giovanne Florez Perez**  ARSW-LAB1 - [andresflorezp] (https://github.com/andresflorezp)

* **Juan Nicolas Nontoa Caballero**  ARSW-LAB1 - [nontoa] (https://github.com/nontoa)

Consulte también la lista de [colaboradores] (https://github.com/andresflorezp/ARSW-LAB2/graphs/contributors) que participaron en este proyecto.

## licencia

Este proyecto está licenciado bajo la Licencia MIT - vea el archivo [LICENSE](LICENSE) para más detalles.
