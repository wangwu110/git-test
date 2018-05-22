package com.sinoufc.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ftpTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FTPClient ftpcli = new FTPClient();
		ftpcli.connect("10.20.144.134", 21);
		boolean b = ftpcli.login("duancj", "chaojiu");
		//		System.out.println(ftpcli.getReplyCode()+"--"+ftpcli.getReplyString());

		//		FileOutputStream fos = null;
		System.out.println(ftpcli.isConnected());
		if (ftpcli.isConnected()) {
			System.out.println("连接成功了");
			/*fos = new FileOutputStream("D:/check_ibs.sh");
			boolean c = ftpcli.retrieveFile("chaojiu/check_ibs.sh", fos);
			System.out.println("---c = "+c + "---");
			System.out.println(ftpcli.getReplyCode()+"--"+ftpcli.getReplyString());
			fos.close();*/

			/*boolean d = ftpcli.storeFile("chaojiu/123.txt", new FileInputStream(new File("D:/123.txt")));
			System.out.println("---d = "+d + "---");
			System.out.println(ftpcli.getReplyCode()+"--"+ftpcli.getReplyString());*/
			/*System.out.println(ftpcli.getRemoteAddress() + ":"
					+ ftpcli.getRemotePort());
			System.out.println(ftpcli.sendCommand("cd chaojiu")+":"+ftpcli.getReplyString());
			System.out.println(ftpcli.printWorkingDirectory());*/
			/*InputStream is = ftpcli.retrieveFileStream("chaojiu/check_ibs.sh");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String lineTemp = null;
			while((lineTemp = br.readLine())!=null){
				System.out.println(lineTemp);
			}*/
			/*OutputStream os = ftpcli.storeFileStream("chaojiu/sh.sh");
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write("print \"hello everybody!\"\r\n".getBytes());
			bos.flush();
			os.close();
			bos.close();*/
			System.out.println(ftpcli.getDefaultTimeout()+"--"+ftpcli.getSoTimeout()+"--"+ftpcli.getDefaultTimeout());
			/*ftpcli.cwd("bafe/src/ibs/busi");
			ftpcli.pwd();
			FTPFile[] ffiles = ftpcli.listFiles();
			System.out.println(ffiles.length);
			for(FTPFile file:ffiles){
				System.out.println(file.getName());
			}*/
			/*String[] files = ftpcli.listNames();
			for(int i=0;i<files.length;i++){
				System.out.println(files[i]);
			}*/
			/*BufferedReader br = new BufferedReader(new InputStreamReader(ftpcli.getInputStream()));
			String lineTemp = null;
			while((lineTemp = br.readLine())!=null){
				System.out.println(lineTemp);
			}
			br.close();*/
			ftpcli.logout();
			ftpcli.disconnect();
		}
	}

}
