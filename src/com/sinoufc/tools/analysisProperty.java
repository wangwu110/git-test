package com.sinoufc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * 解析属性文件
 * 
 * @author chaojiu
 * 
 */
public class analysisProperty {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 根据标签名获取对应的属性值
	 * 
	 * @param tabName标签名
	 * @return 标签对应的属性值
	 */
	public String getPropertyByTag(String tabName) {
		File file = new File(".");
		String basePath = ""; /* 项目当前的基础路径 */

		FileInputStream fis = null;
		Properties pro = new Properties();
		String tabValue = "";
		try {
			basePath = file.getCanonicalPath();
			basePath.replace("\\", "/"); /* 将路径中的"\"替换为"/" */
			fis = new FileInputStream(basePath
					+ "/WebRoot/WEB-INF/file-property.conf");
			pro.load(fis);
			tabValue = pro.getProperty(tabName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tabValue;
	}

}
