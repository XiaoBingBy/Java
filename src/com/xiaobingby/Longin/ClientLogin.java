package com.xiaobingby.Longin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.xiaobingby.chat.ClientChat;
import com.xiaobingby.chat.ThreadClientIO;

public class ClientLogin extends JFrame {

	//��ȡ��Ļ �� ��
	private int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;  
	private int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	//�洢�������Ļ��λ��
	private int mx, my;
	//�洢��ǰ�Ĵ���λ��
	private int jfx, jfy;
	private MyPanelLogin contentPane;
	private JTextField texProt;
	private JTextField texIP;
	private JButton button;
	private JTextField textName;

	private String ip;
	private int prot;
	private String name;
	private Socket s;
	private static ClientLogin winLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					winLogin = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientLogin() {
		//�����޴��ڱ߿�
		setUndecorated(true);
		//����JFrame͸��  ��ɫ
		setBackground(new Color(0,0,0,0));
		//������ʾ
		this.setVisible(true);
		//���ùرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڴ�С JFrame
		setBounds(100, 100, 450, 300);
		//���������   ��дJPanel
		contentPane = new MyPanelLogin();

		//����϶�
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen()-mx+jfx, e.getYOnScreen()-my+jfy);
			}
		});

		//��갴��
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getXOnScreen();
				my = e.getYOnScreen();
				jfx = getX();
				jfy = getY();

				System.out.println("mx: "+mx);
				System.out.println("my: "+my);
				System.out.println("jfx: "+jfx);
				System.out.println("jfy: "+jfy);
			}
		});
		contentPane.setOpaque(false);//����contentPane͸��
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);//
		//���ô��ھ���
		setLocation((screen_width-getWidth())/2, (screen_height-getHeight())/2);
		contentPane.setLayout(null);//���ֿ�

		//IP:��ǩ
		JLabel labelIP = new JLabel("IP:");
		labelIP.setForeground(Color.BLACK);
		labelIP.setBounds(88, 57, 54, 27);
		labelIP.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(labelIP);

		//�˿�:��ǩ
		JLabel labelProt = new JLabel("�˿�:");
		labelProt.setBounds(88, 116, 54, 27);
		labelIP.setForeground(Color.BLACK);
		labelProt.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(labelProt);

		//�˿�:�û���
		JLabel labelName = new JLabel("�û���:");
		labelName.setBounds(66, 172, 74, 27);
		labelName.setForeground(Color.BLACK);
		labelName.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(labelName);

		//IP:�ı���
		texIP = new JTextField();
		texIP.setOpaque(false);
		texIP.setFont(new Font("����", Font.BOLD, 20));
		texIP.setColumns(10);
		texIP.setBounds(152, 54, 141, 38);
		texIP.setText("127.0.0.1");
		contentPane.add(texIP);

		//�˿�:�ı���
		texProt = new JTextField();
		texProt.setOpaque(false);
		texProt.setFont(new Font("����", Font.BOLD, 20));
		texProt.setBounds(152, 113, 141, 38);
		contentPane.add(texProt);
		texProt.setText("8888");
		texProt.setColumns(10);

		//�û���
		textName = new JTextField();
		textName.setOpaque(false);
		textName.setFont(new Font("����", Font.BOLD, 20));
		textName.setColumns(10);
		textName.setBounds(152, 172, 141, 38);
		textName.setText("XiaoBingBy");
		contentPane.add(textName);

		//�˳���ť
		JButton btnExit = new JButton("�˳�");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("�˳�");
				System.exit(0);
			}
		});
		btnExit.setUI(new MyButtonUILogin());
		btnExit.setBounds(352, 251, 83, 33);
		contentPane.add(btnExit);

		//���ӷ�����
		button = new JButton("���ӷ�����");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = 1;
				ip = texIP.getText();
				prot = Integer.parseInt(texProt.getText());
				name = textName.getText();


				try {
					s = new Socket(ip, prot);
					if(i == 1) {
						ThreadClientIO.getThreadClientIO().setSocket(s);
						ThreadClientIO.getThreadClientIO().setName(name);
						JOptionPane.showMessageDialog(null, "���ӳɹ���  ��ӭ��:" + name );
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ClientChat frame = new ClientChat();
									ThreadClientIO.getThreadClientIO().setClientChat(frame);
									frame.setVisible(true);
									winLogin.setVisible(false);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						//�����߳�
						ThreadClientIO.getThreadClientIO().startClient();
					}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "YY");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "����ʧ��");
					i = 0;
				}

			}
		});
		button.setUI(new MyButtonUILogin());
		button.setBounds(177, 220, 100, 38);
		contentPane.add(button);

	}
}