package com.sinoufc.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 读写文件
 * 
 * @author chaojiu
 * 
 */
public class readWriteFile {

	/**
	 * <p>
	 * 获取当前系统时间
	 * 
	 * @return date当前时间
	 */
	public static String getCurrentDate() {
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sDate = sdf.format(date);
		return sDate;
	}

	/**
	 * <p>
	 * 生成文件
	 * 
	 * @param fileName文件名
	 */
	public void createFile(String fileName) {
		analysisProperty anal = new analysisProperty();
		String path = anal.getPropertyByTag("FILE_BASE_PATH");
		String fileFullPath = path + "/" + fileName;
		File file = new File(fileFullPath);
		if (!file.exists()) {
			System.out.println(fileFullPath + "不存在，创建该文件");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <p>读定长文件
	 * @param fileName文件名
	 */
	public void readFixFile(String fileName) {
		analysisProperty anal = new analysisProperty();
		String path = anal.getPropertyByTag("FILE_BASE_PATH");
		String fileFullPath = path + "/" + fileName;
		File file = new File(fileFullPath);
		FileReader fr = null;
		BufferedReader br = null;
		String lineTemp = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while ((lineTemp = br.readLine()) != null) {
				String name = lineTemp.substring(0, 10);
				String sex = lineTemp.substring(10, 20);
				String age = lineTemp.substring(20, 30);
				String idType = lineTemp.substring(30, 40);
				String idNo = lineTemp.substring(40, 50);
				System.out.println(name + "|" + sex + "|" + age + "|" + idType
						+ "|" + idNo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		readWriteFile rwf = new readWriteFile();
//		rwf.createFile(getCurrentDate() + ".txt");
		rwf.readFixFile(getCurrentDate() + ".txt");
	}

}
