package com.xiaobingby.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerSocketThread extends Thread {

	int prot;

	public ServerSocketThread(int prot) {
		this.prot = prot;
	}

	@Override
	public void run() {
		try {
			int i = 1;
			// 启动服务
			ServerSocket ss = new ServerSocket(prot);
			JOptionPane.showMessageDialog(null, "服务启动成功 端口:" + prot);
			// 进入阻塞状态 如果有客服端 连接来 继续执行 阻塞状态解除 来一个客服端生成一个线程 为他服务
			while (true) {
				Socket s = ss.accept();
				// JOptionPane.showMessageDialog(null, "客服端上线 端口:"+prot); 用于测试
				SocketThread st = new SocketThread(s);
				st.start();
				SocketDLH.getDLH().addArrayList(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "停止服务成功!");
		}
	}
}