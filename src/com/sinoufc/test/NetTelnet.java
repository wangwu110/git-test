package com.sinoufc.test;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class NetTelnet {    
    private TelnetClient telnet = new TelnetClient();    
    private InputStream in;    
    private PrintStream out;    
    private char prompt = '$';// 普通用户结束    
   
    public NetTelnet( String ip, int port, String user, String password ) {    
    try {    
        telnet.connect( ip, port );    
        in = telnet.getInputStream();    
        out = new PrintStream( telnet.getOutputStream() );    
        // 根据root用户设置结束符    
        this.prompt = user.equals( "root" ) ? '#' : '$';    
        login( user, password );    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    }    
   
    /**   
     * 登录   
     *    
     * @param user   
     * @param password   
     */   
    public void login( String user, String password ) {    
    readUntil( "login:" );    
    write( user );    
    readUntil( "Password:" );    
    write( password );    
    readUntil( prompt + "]" );    
    }    
   
    /**   
     * 读取分析结果   
     *    
     * @param pattern   
     * @return   
     */   
    public String readUntil( String pattern ) {  
    try {    
        char lastChar = pattern.charAt( pattern.length() - 1 );    
        StringBuffer sb = new StringBuffer();    
        char ch = ( char ) in.read();   
        while ( true ) {    
        sb.append( ch );    
        if (ch == lastChar) {    
            if (sb.toString().endsWith( pattern )) {    
            return sb.toString();    
            }    
        }
        ch = ( char ) in.read();    
        }    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    return null;    
    }    
   
    /**   
     * 写操作   
     *    
     * @param value   
     */   
    public void write( String value ) {    
    try {    
        out.println( value );    
        out.flush();    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    }    
   
    /**   
     * 向目标发送命令字符串   
     *    
     * @param command   
     * @return   
     */   
    public String sendCommand( String command ) {    
    try {    
        write( command );    
        return readUntil( prompt + "]" );    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    return null;    
    }    
   
    /**   
     * 关闭连接   
     */   
    public void disconnect() {    
    try {    
        telnet.disconnect();    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    }    
   
    public static void main( String[] args ) {    
    try {    
        System.out.println( "启动Telnet..." );    
        String ip = "10.20.144.134";    
        int port = 23;    
        String user = "duancj";    
        String password = "chaojiu";    
        NetTelnet telnet = new NetTelnet( ip, port, user, password );    
//        telnet.sendCommand( "export LANG=en" );    
        String r1 = telnet.sendCommand( "cd chaojiu" );    
        String r2 = telnet.sendCommand( "pwd" );    
        String r3 = telnet.sendCommand( "ls" );    
   
        System.out.println( "显示结果" );    
        System.out.println( r1 );    
        System.out.println( r2 );    
        System.out.println( r3 );    
   
        telnet.disconnect();    
    } catch ( Exception e ) {    
        e.printStackTrace();    
    }    
    }    
}   
