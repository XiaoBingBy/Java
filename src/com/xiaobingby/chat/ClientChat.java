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

	//存储屏幕大小
	private int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;  
	private int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	//存储鼠标按下坐标
	private int mx, my;
	//存储窗口坐标
	private int jfx, jfy;
	private MyPanelChat contentPane;
	private JTextArea texJl;
	private JTextField texMess;
	private String name = "XiaoBingBy";

	public static void main(String[] args) {

	}

	public ClientChat() {
		//设置无边框
		setUndecorated(true);
		//设置JFrame背景                                        透明
		setBackground(new Color(255, 255, 255, 0));
		//设置关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口位置  窗口大小
		setBounds(100, 100, 600, 400);
		//设置窗口初始化大小
		setLocation((screen_width-getWidth())/2, (screen_height-getHeight())/2);
		//创建自定义Panel
		contentPane = new MyPanelChat();

		/**
		 * 设置窗口拖动
		 *给自定义面板添加 鼠标拖动事件 
		 */
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen()-mx+jfx, e.getYOnScreen()-my+jfy);
			}
		});

		//给自定义面板添加  鼠标按下 事件
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getXOnScreen();
				my = e.getYOnScreen();
				jfx = getX();
				jfy = getY();
			}
		});

		//设置自定义面板透明
		contentPane.setOpaque(false);
		setContentPane(contentPane);	//类似this.add(contentPane);
		contentPane.setLayout(null);

		//聊天记录
		texJl = new JTextArea();
		texJl.setForeground(Color.blue);
		texJl.setFont(new Font("楷体", Font.BOLD, 15));
		texJl.setBackground(Color.WHITE);
		texJl.setOpaque(false);

		JScrollPane yScrollPane = new JScrollPane(texJl);		
		yScrollPane.setBounds(10, 40, 581, 306);
		contentPane.add(yScrollPane);

		//发送聊天信息区域
		texMess = new JTextField();
		texMess.setFont(new Font("楷体", Font.BOLD, 15));

		texMess.setBounds(13, 356, 486, 35);
		texMess.setOpaque(false);
		contentPane.add(texMess);

		JButton btnSend = new JButton("发送");
		btnSend.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ThreadClientIO.getThreadClientIO().send(texMess.getText()+"\n");
			}

		});
		btnSend.setUI(new MyButtonUI());
		btnSend.setBounds(508, 355, 85, 35);
		contentPane.add(btnSend);


		//关闭程序
		JButton btnExit = new JButton("X");
		btnExit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("退出");
				System.exit(0);
			}

		});
		btnExit.setFont(new Font("黑体", Font.BOLD, 20));
		btnExit.setUI(new MyButtonExitUI());
		btnExit.setBounds(536, 5, 55, 25);
		contentPane.add(btnExit);

		ThreadClientIO.getThreadClientIO().send("上线!"+"\n");
	}

	public void addTexJl(String mess) {
		texJl.append(mess);
	}

}