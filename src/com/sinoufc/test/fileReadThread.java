package com.sinoufc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class fileReadThread extends Thread {

	public void run() {
		FileInputStream fis = null;
		BufferedReader br =null;
		try {
			fis = new FileInputStream(new File("D:/filemon2.conf"));
			br = new BufferedReader(new InputStreamReader(fis));
			System.out.println("进来了");
			sleep(2000);
			System.out.println(br.readLine());
			br.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
