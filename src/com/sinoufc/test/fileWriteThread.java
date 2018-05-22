package com.sinoufc.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class fileWriteThread extends Thread {

	public void run() {
		FileOutputStream fos  =null;
		FileChannel fc = null;
		FileLock fl = null;
		try {
			fos = new FileOutputStream(new File("D:/filemon2.conf"));
			fc = fos.getChannel();
			fl = fc.tryLock();
			System.out.println("休眠开始--");
			sleep(5000);
			System.out.println("休眠结束--");
			fl.release();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
