package com.xiaobingby.server;

import java.util.ArrayList;

public class SocketDLH {
	// 单例化 本对象只存在一个
	private static final SocketDLH DLH = new SocketDLH();

	private SocketDLH() {
	}

	public static SocketDLH getDLH() {
		return DLH;
	}

	ArrayList<SocketThread> al = new ArrayList<SocketThread>();

	// 添加一个客服端生成对象 SocketThread类
	public void addArrayList(SocketThread st) {
		al.add(st);
	}

	public void removeAL(SocketThread st) {
		al.remove(st);
	}

	// 向所有客服端发送消息

	public void sendAll(String mess) {
		for (int i = 0; i < al.size(); i++) {
			al.get(i).send(mess);
		}
	}
}