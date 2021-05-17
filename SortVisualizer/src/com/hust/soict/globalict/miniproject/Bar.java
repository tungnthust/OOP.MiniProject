package com.hust.soict.globalict.miniproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Bar
{
	private final int MARGIN = 2;
	private int x, y, width, value;
	private Color color;

	// y: the bottom left corner
	public Bar(int x, int y, int width, int value, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.value = value;
		this.color = color;
	}


	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x + MARGIN, y-(value*10), width - MARGIN * 2, value * 10);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 16));
		int bottom_margin = 0;
		if (value < 4) {
			bottom_margin = value * 10;
		}
		String valueString = String.valueOf(value);
		int padding;
		if (valueString.length() == 2) {
			padding = (int) (width - 20) / 2;
		} else {
			padding = (int) (width - 14) / 2;
		}
		g.drawString(valueString, x + MARGIN + padding, y - bottom_margin - 7);
	}


	public void clear(Graphics g)
	{
		// clear the space
		g.setColor(ColorManager.BAR_WHITE);
		g.fillRect(x + MARGIN, y-(25*10), width - MARGIN * 2, 25 * 10);
	}


	public void setValue(int value) { this.value = value; }

	public int getValue() { return value; }

	public void setColor(Color color) { this.color = color; }

	public Color getColor() { return color; }
}
