package com.sinoufc.tools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 解析文件
 * 
 * @author duanchaojiu
 * 
 */
public class ListFile {

	/**
	 * 
	 * @param filename
	 *            文件绝对路径名（包含文件名）
	 * @param headFlag
	 *            文件头标志（Y：有文件头；N：无文件头）
	 * @param headText
	 *            文件头内容
	 * @return Map
	 */
	public Map readFile(String filename, String headFlag, String headText) {
		Map hmap = new HashMap();
		File file = new File(filename);
		FileReader fr = null;
		BufferedReader br = null;
		String lineTemp = null;
		Map<String, String> hm = null;
		List list = new ArrayList();
		char isHead = 'N';
		int flag = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
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
			br.close();
			fr.close();
			hmap.put("list", list);
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
		return hmap;
	}

	public Map selectFileContextBySerName(String filename, String headFlag,
			String headText, String serviceName) {
		Map hmap = new HashMap();
		List list = new ArrayList();
		Map<String, String> hm = null;
		File file = new File(filename);
		BufferedReader br = null;
		String lineTemp = null;
		String SerName = "[" + serviceName.trim() + "]";
		char isHead = 'N';
		char isFind = 'N';
		try {
			br = new BufferedReader(new FileReader(file));
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
					isFind = 'N';
					if (SerName.equals(str[0].trim())) {
						isFind = 'Y';
						hm = new HashMap<String, String>();
						hm.put("ServiceName", str[0].substring(1, str[0]
								.length() - 1));
					}
				} else {
					if (isHead == 'N') {
						if (isFind == 'Y') {
							String[] str2 = str[0].split("=");
							hm.put(str2[0].trim(), str2[1].trim());
						}
					} else {
						String[] str3 = str[0].split("=");
						hmap.put(str3[0].trim(), str3[1].trim());
					}
				}
			}
			list.add(hm);
			hmap.put("list", list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hmap;
	}
	
	public void writeFile(String readFileName,String writeFileName){
		File file = new File(readFileName);
		BufferedReader br =null;
		FileOutputStream fos = null;
		BufferedReader br2 =null;
		BufferedOutputStream bos = null;
		BufferedReader br3 =null;
		FileOutputStream fos3 = null;
		String lineTemp = null;
		long begin = 0;
		long end = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			begin = System.currentTimeMillis();
			fos= new FileOutputStream(new File(writeFileName));
			while((lineTemp=br.readLine())!=null){
				fos.write((lineTemp+"\r\n").getBytes());
			}
			fos.close();
			br.close();
			end = System.currentTimeMillis();
			System.out.println("FileOutputStream耗时："+(end-begin));
			
			
			br2 = new BufferedReader(new FileReader(file));
			begin = System.currentTimeMillis();
			bos = new BufferedOutputStream(new FileOutputStream(new File("D:/2.conf")));
			while((lineTemp=br2.readLine())!=null){
				bos.write((lineTemp+"\r\n").getBytes());
			}
			bos.close();
			br2.close();
			end = System.currentTimeMillis();
			System.out.println("BufferedOutputStream耗时："+(end-begin));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListFile lf = new ListFile();
		/*
		 * Map hmap = lf.readFile("D:/FtsCom.conf", "Y", "[COMMONDATA]");
		 * System.out.println("IP=" + hmap.get("IP"));
		 * System.out.println("PORT=" + hmap.get("PORT")); System.out.println();
		 * List list = (List) hmap.get("list"); for (int i = 0; i < list.size();
		 * i++) { Map hmp = (Map) list.get(i); System.out.println("ServiceName=" +
		 * hmp.get("ServiceName")); System.out.println("IN=" + hmp.get("IN"));
		 * System.out.println("OUT=" + hmp.get("OUT"));
		 * System.out.println("OUT_BZ=" + hmp.get("OUT_BZ"));
		 * System.out.println("TASKID=" + hmp.get("TASKID"));
		 * System.out.println("ENDFLAG=" + hmp.get("ENDFLAG"));
		 * System.out.print("\n"); }
		 */
		/*
		 * Map hmap = lf.readFile("D:/filemon.conf","N",""); List list = (List)
		 * hmap.get("list"); for (int i = 0; i < list.size(); i++) { Map hmp =
		 * (Map) list.get(i); System.out.println("ServiceName=" +
		 * hmp.get("ServiceName")); System.out.println("_FACTHTIME=" +
		 * hmp.get("_FACTHTIME")); System.out.println("_OPTCODE=" +
		 * hmp.get("_OPTCODE")); System.out.println("_SYSNAME=" +
		 * hmp.get("_SYSNAME")); System.out.println("_OUTSYSNUM=" +
		 * hmp.get("_OUTSYSNUM")); System.out.println("PATHFILENAME=" +
		 * hmp.get("PATHFILENAME")); System.out.println("SUBPATHNAME=" +
		 * hmp.get("SUBPATHNAME")); System.out.println("EXCEPTER=" +
		 * hmp.get("EXCEPTER")); System.out.println("FUNCTIONNAME=" +
		 * hmp.get("FUNCTIONNAME")); System.out.println("LIBFILENAME=" +
		 * hmp.get("LIBFILENAME")); System.out.println("BAKFLAG=" +
		 * hmp.get("BAKFLAG")); System.out.println("CDFLAG=" +
		 * hmp.get("CDFLAG")); System.out.println("CDENDFLAG=" +
		 * hmp.get("CDENDFLAG")); System.out.print("\n"); }
		 */
		/*Map hmap = lf.selectFileContextBySerName("D:/FtsCom.conf", "Y",
				"[COMMONDATA]", "BAFE_WK_REPT_SEND");
		System.out.println("IP=" + hmap.get("IP"));
		System.out.println("PORT=" + hmap.get("PORT"));
		System.out.println();
		List list = (List) hmap.get("list");
		for (int i = 0; i < list.size(); i++) {
			Map hmp = (Map) list.get(i);
			if (hmp == null) {
				continue;
			}
			System.out.println("ServiceName=" + hmp.get("ServiceName"));
			System.out.println("IN=" + hmp.get("IN"));
			System.out.println("OUT=" + hmp.get("OUT"));
			System.out.println("OUT_BZ=" + hmp.get("OUT_BZ"));
			System.out.println("TASKID=" + hmp.get("TASKID"));
			System.out.println("ENDFLAG=" + hmp.get("ENDFLAG"));
			System.out.print("\n");
		}*/
		lf.writeFile("D:/filemon.conf", "D:/1.conf");
	}

}
