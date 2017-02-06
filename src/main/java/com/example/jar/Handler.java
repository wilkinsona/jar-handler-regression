package com.example.jar;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class Handler extends URLStreamHandler {

	public static final List<URL> urls = new ArrayList<URL>();

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		urls.add(url);
		return new JarURLConnection(url) {

			@Override
			public void connect() throws IOException {

			}

			@Override
			public JarFile getJarFile() throws IOException {
				return null;
			}
		};
	}

}
