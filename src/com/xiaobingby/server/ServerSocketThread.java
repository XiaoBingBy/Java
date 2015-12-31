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
			// ��������
			ServerSocket ss = new ServerSocket(prot);
			JOptionPane.showMessageDialog(null, "���������ɹ� �˿�:" + prot);
			// ��������״̬ ����пͷ��� ������ ����ִ�� ����״̬��� ��һ���ͷ�������һ���߳� Ϊ������
			while (true) {
				Socket s = ss.accept();
				// JOptionPane.showMessageDialog(null, "�ͷ������� �˿�:"+prot); ���ڲ���
				SocketThread st = new SocketThread(s);
				st.start();
				SocketDLH.getDLH().addArrayList(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ֹͣ����ɹ�!");
		}
	}
}