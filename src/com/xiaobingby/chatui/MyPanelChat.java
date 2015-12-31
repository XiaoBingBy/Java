package com.xiaobingby.chatui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class MyPanelChat extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;

		//消除锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(6));  //BasicStroke

		//绘制中间
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

		//绘制标题栏
		g2d.setColor(Color.GRAY);
		g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()/12, 20, 20);

		//绘制边缘
		g2d.setColor(Color.red);
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

		//绘制标题栏字体
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("楷体", Font.BOLD, 20));
		g2d.drawString("简单聊天界面                               V0.1", 10, 25);

		//绘制yScrollPane背景
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fillRoundRect(157, 39, 436, 314, 20, 20);

		//绘制聊天文本框
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fillRoundRect(13, 356, 486, 35, 20, 20);

	}

}