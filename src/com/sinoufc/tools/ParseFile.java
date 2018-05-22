package com.sinoufc.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

public class ParseFile {
	/*
	 * 该类只解析如下文件格式的文件（有头和没有头均可） 
	 * [HEAD] name1=value1 name2=value2
	 * 
	 * [serviceName1] key1=value1 key2=value2 key3=value3 key4=value4
	 * 
	 * [serviceName2] key1=value1 key2=value2 key3=value3 key4=value4
	 * 
	 * [serviceName3] key1=value1 key2=value2 key3=value3 key4=value4
	 *  . . .
	 */

	/**
	 * 
	 * @param ftpCli
	 *            ftp客户端实例
	 * @param filepath
	 *            文件绝对路径名（包含文件名）
	 * @param headFlag
	 *            文件头标志（Y：有文件头；N：无文件头）
	 * @param headText
	 *            文件头内容
	 * @return Map
	 */
	public Map readRemoteFile(FTPClient ftpCli, String filepath,
			String headFlag, String headText) {
		Map hmap = new HashMap();
		InputStream is = null;
		BufferedReader br = null;
		String lineTemp = null;
		Map<String, String> hm = null;
		List list = new ArrayList();
		char isHead = 'N';
		int flag = 0;
		try {
			is = ftpCli.retrieveFileStream(filepath);
			br = new BufferedReader(new InputStreamReader(is));
			while ((lineTemp = br.readLine()) != null) {
				if (lineTemp.trim().length() == 0) {
					continue;
				}
				String[] str = lineTemp.split("#");
				if (str[0].trim().length() == 0) {
					continue;
				}
				if ("Y".equals(headFlag)) {
					if (headText.equals(str[0].trim())) {
						isHead = 'Y';
						continue;
					}
				}
				if ('[' == (str[0].charAt(0))
						&& ']' == (str[0].charAt(str[0].length() - 1))) {
					isHead = 'N';
					if (flag != 0) {
						list.add(hm);
					}
					hm = new HashMap<String, String>();
					hm.put("ServiceName", str[0].substring(1,
							str[0].length() - 1));
					flag++;
				} else {
					if (isHead == 'N') {
						String[] str2 = str[0].split("=");
						hm.put(str2[0].trim(), str2[1].trim());
					} else {
						String[] str3 = str[0].split("=");
						hmap.put(str3[0].trim(), str3[1].trim());
					}
				}
			}
			list.add(hm);
			hmap.put("list", list);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hmap;
	}
}
