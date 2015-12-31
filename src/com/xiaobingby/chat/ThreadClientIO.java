package com.xiaobingby.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ThreadClientIO {

	/**
	 * ���л�������
	 */
	private static final ThreadClientIO tcio = new ThreadClientIO();
	ClientChat cc;	//��ȡ��������
	private ThreadClientIO() {}
	public void setClientChat(ClientChat clientChat) {
		this.cc = clientChat;
	}
	public static ThreadClientIO getThreadClientIO() {
		return tcio;
	}

	private Socket s;	//Soket �׽��� ���ӷ�����
	private InputStream in;	//������	���ڶ�ȡ��Ϣ
	private OutputStream out;	//�����	���ڷ�����Ϣ
	private String name;	//�û���

	public void startClient() {
		try {
			//in out  ��socket��ȡ ���������
			in = s.getInputStream();
			out = s.getOutputStream();
			System.err.println("�ܵ� �����ɹ�");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//������Ϣ�߳�
		new Thread() {
			public void run() {
				while(true) {
					try {
						byte[] buf = new byte[1024];
						int len = in.read(buf);
						cc.addTexJl(new String(buf, 0, len));
					} catch (IOException e) {
						e.printStackTrace();
						//�Ͽ����� ������
						JOptionPane.showMessageDialog(null, "�������Ͽ�!  ǿ���˳�����!");
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