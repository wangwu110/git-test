package com.sinoufc.tools;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import ch.ethz.ssh2.Connection;

/**
 * <p>远程连接类
 * @author 段朝久
 *
 */
public class RemoteConnection {
	/**
	 * <p>FTP方式连接
	 * @param ip IP地址
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @return FTPClient
	 */
	public FTPClient ftpConnection(String ip,String port,String username,String password){
		FTPClient ftpCli = new FTPClient();
		try {
			ftpCli.connect(ip, Integer.parseInt(port));
			ftpCli.login(username, password);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpCli;
	}
	
	/**
	 * <p>关闭FTP连接
	 * @param ftpCli
	 */
	public void ftpCloseConn(FTPClient ftpCli){
			try {
				if(ftpCli.isConnected() && ftpCli!=null){
					ftpCli.logout();
					ftpCli.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * <p>SSH2方式连接
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @return Connection
	 */
	public Connection sshConnection(String ip,String port,String username,String password){
		Connection conn = new Connection(ip,Integer.parseInt(port));
		try {
			conn.connect();
			conn.authenticateWithPassword(username, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * <p>关闭SSH2连接
	 * @param conn
	 */
	public void sshCloseConn(Connection conn){
		if(conn!=null){
			conn.close();
		}
	}
}
