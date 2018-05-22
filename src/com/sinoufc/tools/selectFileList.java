package com.sinoufc.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class selectFileList {

	public static List getFiles(String filedir) {
		List fileNames = null;
		File[] files = null;
		File dir = new File(filedir);
		if (dir.exists()) {
			fileNames = new ArrayList();
			files = dir.listFiles();
			Arrays.sort(files, new CompratorByLastModified());
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				fileNames.add(files[i].getName());
			}
		}
		return fileNames;
	}

	public static class CompratorByLastModified implements Comparator<File> {

		public int compare(File f1, File f2) {
			if (f1.lastModified() > f2.lastModified()) {
				return 1;
			} else if (f1.lastModified() == f2.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}

		public boolean equals(Object obj) {
			return true;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List fileNames = selectFileList.getFiles("E:/bafe/src/call/busi/");
		for (int i = 0; i < fileNames.size(); i++) {
			System.out.println(fileNames.get(i));
		}
	}

}
