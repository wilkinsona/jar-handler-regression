package com.example;

import java.net.URL;
import java.net.URLClassLoader;

import com.example.jar.Handler;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setProperty("java.protocol.handler.pkgs", "com.example");
		URLClassLoader loader = new URLClassLoader(new URL[] {
				new URL("jar:file:test.jar!/")});
		loader.findResource("test.txt");
		loader.close();
		System.out.println("URLs handled by custom handler: " + Handler.urls.size());
	}

}
