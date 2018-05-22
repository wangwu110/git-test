package com.sinoufc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class sshTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con= new Connection("10.20.144.134",22);
		String lineTemp = null;
		try {
			con.connect();
			con.authenticateWithPassword("duancj", "chaojiu");
			/*SCPClient scp = con.createSCPClient();
			FileOutputStream os = new FileOutputStream("D:/3.txt");
			scp.get("chaojiu/123.txt", os);*/
			/*SFTPv3Client sftp = new SFTPv3Client(con);
			sftp.rm("chaojiu/222.txt");
			sftp.close();*/
			Session son = con.openSession();
			son.execCommand("cd bafe/src/ibs/busi;ls *.pc");
			InputStream is = new StreamGobbler(son.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while((lineTemp = br.readLine())!=null){
				System.out.println(lineTemp);
			}
			son.close();
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
