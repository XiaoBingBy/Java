package com.xiaobingby.chatui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonExitUI extends BasicButtonUI {
	protected void installDefaults(AbstractButton b) {
		// TODO Auto-generated method stub
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}

	//显示按钮颜色
	public void paint(Graphics g, JComponent c) {
		g.setColor(new Color(230,83,95,200));
		g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
		super.paint(g, c);
	}

	//按下按钮颜色
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		g.setColor(new Color(240,83,95,150));
		g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
	}
}