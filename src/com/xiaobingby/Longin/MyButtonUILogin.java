package com.xiaobingby.Longin;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonUILogin extends BasicButtonUI {

	protected void installDefaults(AbstractButton b) {
		// TODO Auto-generated method stub
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}

	public void paint(Graphics g, JComponent c) {
		g.setColor(new Color(12,74,230,150));
		g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
		super.paint(g, c);
	}

	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		g.setColor(new Color(145,165,214,150));
		g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
	}

}