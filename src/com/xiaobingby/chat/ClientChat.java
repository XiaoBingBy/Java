package com.xiaobingby.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.xiaobingby.chatui.MyButtonExitUI;
import com.xiaobingby.chatui.MyButtonUI;
import com.xiaobingby.chatui.MyPanelChat;

public class ClientChat extends JFrame{

	//�洢��Ļ��С
	private int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;  
	private int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	//�洢��갴������
	private int mx, my;
	//�洢��������
	private int jfx, jfy;
	private MyPanelChat contentPane;
	private JTextArea texJl;
	private JTextField texMess;
	private String name = "XiaoBingBy";

	public static void main(String[] args) {

	}

	public ClientChat() {
		//�����ޱ߿�
		setUndecorated(true);
		//����JFrame����                                        ͸��
		setBackground(new Color(255, 255, 255, 0));
		//���ùرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô���λ��  ���ڴ�С
		setBounds(100, 100, 600, 400);
		//���ô��ڳ�ʼ����С
		setLocation((screen_width-getWidth())/2, (screen_height-getHeight())/2);
		//�����Զ���Panel
		contentPane = new MyPanelChat();

		/**
		 * ���ô����϶�
		 *���Զ��������� ����϶��¼� 
		 */
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen()-mx+jfx, e.getYOnScreen()-my+jfy);
			}
		});

		//���Զ���������  ��갴�� �¼�
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getXOnScreen();
				my = e.getYOnScreen();
				jfx = getX();
				jfy = getY();
			}
		});

		//�����Զ������͸��
		contentPane.setOpaque(false);
		setContentPane(contentPane);	//����this.add(contentPane);
		contentPane.setLayout(null);

		//�����¼
		texJl = new JTextArea();
		texJl.setForeground(Color.blue);
		texJl.setFont(new Font("����", Font.BOLD, 15));
		texJl.setBackground(Color.WHITE);
		texJl.setOpaque(false);

		JScrollPane yScrollPane = new JScrollPane(texJl);		
		yScrollPane.setBounds(10, 40, 581, 306);
		contentPane.add(yScrollPane);

		//����������Ϣ����
		texMess = new JTextField();
		texMess.setFont(new Font("����", Font.BOLD, 15));

		texMess.setBounds(13, 356, 486, 35);
		texMess.setOpaque(false);
		contentPane.add(texMess);

		JButton btnSend = new JButton("����");
		btnSend.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ThreadClientIO.getThreadClientIO().send(texMess.getText()+"\n");
			}

		});
		btnSend.setUI(new MyButtonUI());
		btnSend.setBounds(508, 355, 85, 35);
		contentPane.add(btnSend);


		//�رճ���
		JButton btnExit = new JButton("X");
		btnExit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("�˳�");
				System.exit(0);
			}

		});
		btnExit.setFont(new Font("����", Font.BOLD, 20));
		btnExit.setUI(new MyButtonExitUI());
		btnExit.setBounds(536, 5, 55, 25);
		contentPane.add(btnExit);

		ThreadClientIO.getThreadClientIO().send("����!"+"\n");
	}

	public void addTexJl(String mess) {
		texJl.append(mess);
	}

}