package com.xiaobingby.Longin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class MyPanelLogin extends JPanel {

	/**
	 * 绘制自身
	 * 绘制步骤	中间->标题栏->边框->标题栏字体
	 */
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;

		//消除锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(6));

		//绘制中间 RGB 透明度
		g2d.setColor(new Color(255, 255, 255, 150));
		//g2d.setColor(Color.black);
		g2d.fillRoundRect(3, 3, getWidth()-(1+6), getHeight()-(1+6), 20, 20);

		//绘制标题栏
		g2d.setColor(Color.gray);
		g2d.fillRoundRect(3, 3, getWidth()-7, getHeight()/10, 20, 20);

		//绘制边缘
		//g2d.setColor(Color.blue);
		g2d.setColor(new Color(178, 189, 217));
		g2d.setStroke(new BasicStroke(6));
		//6/2 = 3 x y 轴从3开始    绘制了6个像素所以 6+1 6+1
		g2d.drawRoundRect(3, 3, getWidth()-(1+6), getHeight()-(1+6), 20, 20);

		//绘制标题字体
		g2d.setColor(Color.black);
		g2d.setFont(new Font("楷体", Font.BOLD, 20));
		g2d.drawString("连接服务器                         v0.1", 10, 26);

		//绘制文本框
		//IP
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 54, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 54, 141, 38, 20, 20);
		//端口
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 113, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 113, 141, 38, 20, 20);
		//用户名
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 172, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 172, 141, 38, 20, 20);
	}

}