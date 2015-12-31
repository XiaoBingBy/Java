package com.xiaobingby.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketThread extends Thread {

	Socket s;
	OutputStream out;
	InputStream in;
	public static ServerGUI gui;
	public String name;

	public SocketThread(Socket s) {
		this.s = s;
	}

	public void send(String mess) {
		try {
			out.write(mess.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setWin(ServerGUI g) {
		gui = g;
	}

	@Override
	public void run() {
		try {
			out = s.getOutputStream();
			in = s.getInputStream();
			// 上线列表功能
			byte temp[] = new byte[128];
			int tlen = in.read(temp);
			String sx = new String(temp, 0, tlen);
			String user[] = sx.split(":");
			name = user[0];
			SocketDLH.getDLH().sendAll(sx);
			gui.AddTxtArea(sx);
			gui.listModel.addElement(this);

			while (true) {
				byte[] buf = new byte[1024];
				int len = in.read(buf);
				System.err.println(new String(buf, 0, len));
				gui.AddTxtArea(new String(buf, 0, len));
				// 向所有客服端转发
				SocketDLH.getDLH().sendAll(new String(buf, 0, len));
			}
		} catch (IOException e) {
			// gui.listModel.removeElement("123");
			SocketDLH.getDLH().removeAL(this);
			gui.listModel.removeElement(this);

		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}