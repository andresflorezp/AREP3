package edu.eci.arsw;

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
			/*
			System.out.println(google.getFile());
			System.out.println(google.getAuthority());
			System.out.println(google.getProtocol());
			System.out.println(google.getPath());
			System.out.println(google.getHost());
			System.out.println(google.getPort());
			System.out.println(google.getQuery());
			System.out.println(google.getFile());
			System.out.println(google.getRef());
			*/
			

		} catch (IOException x) {
			System.err.println(x);

		}
	}

}