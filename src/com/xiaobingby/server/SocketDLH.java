package com.xiaobingby.server;

import java.util.ArrayList;

public class SocketDLH {
	// ������ ������ֻ����һ��
	private static final SocketDLH DLH = new SocketDLH();

	private SocketDLH() {
	}

	public static SocketDLH getDLH() {
		return DLH;
	}

	ArrayList<SocketThread> al = new ArrayList<SocketThread>();

	// ���һ���ͷ������ɶ��� SocketThread��
	public void addArrayList(SocketThread st) {
		al.add(st);
	}

	public void removeAL(SocketThread st) {
		al.remove(st);
	}

	// �����пͷ��˷�����Ϣ

	public void sendAll(String mess) {
		for (int i = 0; i < al.size(); i++) {
			al.get(i).send(mess);
		}
	}
}