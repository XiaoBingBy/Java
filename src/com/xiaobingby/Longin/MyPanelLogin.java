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
	 * ��������
	 * ���Ʋ���	�м�->������->�߿�->����������
	 */
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;

		//�������
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(6));

		//�����м� RGB ͸����
		g2d.setColor(new Color(255, 255, 255, 150));
		//g2d.setColor(Color.black);
		g2d.fillRoundRect(3, 3, getWidth()-(1+6), getHeight()-(1+6), 20, 20);

		//���Ʊ�����
		g2d.setColor(Color.gray);
		g2d.fillRoundRect(3, 3, getWidth()-7, getHeight()/10, 20, 20);

		//���Ʊ�Ե
		//g2d.setColor(Color.blue);
		g2d.setColor(new Color(178, 189, 217));
		g2d.setStroke(new BasicStroke(6));
		//6/2 = 3 x y ���3��ʼ    ������6���������� 6+1 6+1
		g2d.drawRoundRect(3, 3, getWidth()-(1+6), getHeight()-(1+6), 20, 20);

		//���Ʊ�������
		g2d.setColor(Color.black);
		g2d.setFont(new Font("����", Font.BOLD, 20));
		g2d.drawString("���ӷ�����                         v0.1", 10, 26);

		//�����ı���
		//IP
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 54, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 54, 141, 38, 20, 20);
		//�˿�
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 113, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 113, 141, 38, 20, 20);
		//�û���
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.drawRoundRect(152, 172, 141, 38, 20, 20);
		g2d.fillRoundRect(152, 172, 141, 38, 20, 20);
	}

}