package com.xiaobingby.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

class ServerGUI extends JFrame{
	
	private JLabel labelProt;
	private JTextArea contentArea;
	private JTextField txtMessage;
	private JTextField txtPort;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnSend;
	private JPanel northPanel;
	private JPanel southPanel;
	private JScrollPane rightPanel;
	private JScrollPane leftPanel;
	private JSplitPane centerSplit;
	private JList userList;
	public DefaultListModel listModel;
	//��������������
	//���������socket���񡣲�����һ���˿ڡ�
	ServerSocket ss = null;
	Socket s = null;
	private int prot;
	private static ServerGUI Win;

	public ServerGUI() {
		super();
	}

	public ServerGUI(String title) {
		super(title);
		//���ô�������
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		labelProt = new JLabel("                  �˿�");
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		contentArea.setForeground(Color.blue);
		txtMessage = new JTextField();

		txtPort = new JTextField("8888");
		btnStart = new JButton("��������");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prot = Integer.parseInt(txtPort.getText());
				new ServerSocketThread(prot).start();
			}
		});
//ֹͣ����
		btnStop = new JButton("�رշ�����");
		btnStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);;
			}
		});
		btnSend = new JButton("����");
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SocketDLH.getDLH().sendAll("������: "+txtMessage.getText()+"\n");
				contentArea.append("������: "+txtMessage.getText()+"\n");
			}
		});
		listModel = new DefaultListModel();
		userList = new JList(listModel);
		southPanel = new JPanel(new BorderLayout());
		southPanel.setBorder(new TitledBorder("д��Ϣ"));
		southPanel.add(txtMessage, BorderLayout.CENTER);


		southPanel.add(btnSend, BorderLayout.EAST);
		leftPanel = new JScrollPane(userList);
		leftPanel.setBorder(new TitledBorder("�����û�"));

		rightPanel = new JScrollPane(contentArea);
		rightPanel.setBorder(new TitledBorder("��Ϣ��¼"));
		centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftPanel, rightPanel);
		centerSplit.setDividerLocation(100);
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 6));
		northPanel.add(labelProt);
		northPanel.add(txtPort);
		northPanel.add(btnStart);
		northPanel.add(btnStop);
		northPanel.setBorder(new TitledBorder("������Ϣ"));
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerSplit, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.setSize(600, 400);
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth())/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	public void AddTxtArea(String mess) {
		contentArea.append(mess);
	}
	
	public static void main(String[] args) {
		ServerGUI gui = new ServerGUI("������");
		SocketThread.setWin(gui);
		SocketThread.gui = gui;
	}
	
}