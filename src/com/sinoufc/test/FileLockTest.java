package com.sinoufc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*FileOutputStream fos = null;
		FileInputStream fis = null;
		BufferedReader br = null;
		FileChannel fc = null;
		FileLock fl = null;
		String line = null;
		try {
			fos = new FileOutputStream(new File("D:/filemon2.conf"));
			fc = fos.getChannel();
			fl = fc.tryLock();
			System.out.println(fl.isValid()+":"+fl.isShared());
			fis = new FileInputStream(new File("D:/filemon2.conf"));
			br = new BufferedReader(new InputStreamReader(fis));
			while((line = br.readLine())!=null){
				System.out.println(line);
			}
			fl.release();
			fos.close();
			br.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		fileWriteThread th1= new fileWriteThread();
		fileReadThread th2 = new fileReadThread();
		th1.start();
		th2.start();
	}

}
