package com.hust.soict.globalict.miniproject.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.hust.soict.globalict.miniproject.sort_visualizer.SortVisualizer.ColorManager;

public class Instruction {
	private final int MARGIN = 35;
	private int x, y, width, height;
	String instruction;
	private Color color;
	private int margin;

	public Instruction(int x, int y, int width, String instruction, int margin)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = 34;
		this.instruction = instruction;
		this.margin = margin;
	}


	public void draw(Graphics g)
	{
		g.setColor(ColorManager.BAR_CYAN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 16));
		g.drawString(instruction, x + MARGIN + margin * 25, y + 20);
	}


	public void clear(Graphics g)
	{
		// clear the space
		g.setColor(ColorManager.BAR_WHITE);
		g.fillRect(x, y, width, height);;
	}

	public void setColor(Color color) { this.color = color; }

	public Color getColor() { return color; }
	
	public void hightlight(Graphics g) {
		g.setColor(ColorManager.BAR_GREEN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 16));
		g.drawString(instruction, x + MARGIN + margin * 25, y + 20);
	}
	public void unhightlight(Graphics g) {
		g.setColor(ColorManager.BAR_CYAN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 16));
		g.drawString(instruction, x + MARGIN + margin * 25, y + 20);
	}
}
