package com.xiaobingby.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ThreadClientIO {

	/**
	 * 单列化本对象
	 */
	private static final ThreadClientIO tcio = new ThreadClientIO();
	ClientChat cc;	//获取窗口属性
	private ThreadClientIO() {}
	public void setClientChat(ClientChat clientChat) {
		this.cc = clientChat;
	}
	public static ThreadClientIO getThreadClientIO() {
		return tcio;
	}

	private Socket s;	//Soket 套接字 连接服务器
	private InputStream in;	//输入流	用于读取消息
	private OutputStream out;	//输出流	用于发送消息
	private String name;	//用户名

	public void startClient() {
		try {
			//in out  从socket获取 输入输出流
			in = s.getInputStream();
			out = s.getOutputStream();
			System.err.println("管道 建立成功");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//接收消息线程
		new Thread() {
			public void run() {
				while(true) {
					try {
						byte[] buf = new byte[1024];
						int len = in.read(buf);
						cc.addTexJl(new String(buf, 0, len));
					} catch (IOException e) {
						e.printStackTrace();
						//断开连接 做处理
						JOptionPane.showMessageDialog(null, "服务器断开!  强制退出程序!");
						System.exit(0);
					}
				}
			};
		}.start();
	}

	public void send(String mess) {
		try {
			out.write((name+": "+mess).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket s) {
		this.s = s;
	}

	public void setName(String name) {
		this.name = name;
	}
}