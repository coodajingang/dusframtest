package com.dus.dusframework.communicate.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dus.dusframework.communicate.ICommunicateAdapter;

public class ServletCommunicateAdapter extends HttpServlet 
	implements ICommunicateAdapter<String, String, ServletCommunicateAdapterConfig>{

	private ServletCommunicateAdapterConfig config;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		InputStream in = req.getInputStream();
		byte[] bytebuffer = new byte[1024];
		int len = -1;
		
		BufferedInputStream reader = new BufferedInputStream(in);
		ByteArrayOutputStream writer = new ByteArrayOutputStream();
		
		while((len = reader.read(bytebuffer, 0, 1024)) != -1) {
			writer.write(bytebuffer, 0, len);
		}
		
		byte[] allBytes = writer.toByteArray();
		String msg = new String(allBytes, "utf-8");
		
		
	}
	
	
	public ServletCommunicateAdapterConfig getAdapterConfig() {
		
		if (this.config == null) {
			
		}
		
		return this.config;
	}

	public void setAdapterConfig(ServletCommunicateAdapterConfig config) {
		this.config = config;
		
	}

	public String doAdapter(String msg) {
		
		return null;
	}


}
