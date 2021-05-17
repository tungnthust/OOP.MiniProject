package com.hust.soict.globalict.miniproject.sort_visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.hust.soict.globalict.miniproject.entity.Bar;
import com.hust.soict.globalict.miniproject.entity.Instruction;
import com.hust.soict.globalict.miniproject.screen.SortCanvas;
import com.hust.soict.globalict.miniproject.screen.SortFrame;

public class SortVisualizer {
	protected static final int PADDING = 20;
	protected int[] array;
	protected int size;
	protected int speed = 100;
	protected Bar[] bars;
	protected Instruction[] instructions;
	protected boolean hasArray = false;
	protected BufferStrategy bs;
	protected Graphics g;
	protected SortFrame sortFrame;
	protected Color originalColor, swappingColor, comparingColor;
	protected int canvasWidth, canvasHeight;
	protected volatile boolean isContinue = false;
	protected volatile boolean isContinuous = false;
	
	public static class ColorManager
	{
		public static Color BAR_WHITE = new Color(249, 249, 249);
		public static Color BAR_BLUE = new Color(105, 198, 255);
		public static Color BAR_CYAN = new Color(160, 246, 255);
		public static Color BAR_RED = new Color(255, 177, 244);
		public static Color BAR_YELLOW = new Color(251, 238, 125);
		public static Color BAR_ORANGE = new Color(255, 177, 51);
		public static Color BAR_GREEN = new Color(118, 244, 118);

	}

	
	public SortVisualizer(SortCanvas sortCanvas, SortFrame sortFrame, int array[]) {
		this.sortFrame = sortFrame;
		bs = sortFrame.getBufferStrategy();
		originalColor = ColorManager.BAR_CYAN;
		comparingColor = ColorManager.BAR_YELLOW;
		swappingColor = ColorManager.BAR_GREEN;
		this.array = array;
		this.size = array.length;
		this.canvasWidth = sortCanvas.getWidth();
		this.canvasHeight = sortCanvas.getHeight();
		renderArray();	
		renderInstructionSet();
	}
	
	
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}


	public void renderBar(Bar bar, Color color) {
		bar.setColor(color);
		bar.draw(g);
		bs.show();
		
	}
	public void renderArray()
	{
		bars = new Bar[size];
		hasArray = true;

		// initial position
		int x = PADDING;
		int y = canvasHeight - 300 - PADDING;

		// width of all bars
		double width = (double) (canvasWidth - PADDING*2) / size;

		// get graphics
        g = bs.getDrawGraphics();
		g.setColor(ColorManager.BAR_WHITE);
		g.fillRect(0, 0, canvasWidth, canvasHeight);

		int value;
		Bar bar;
		for (int i = 0; i < array.length; i++)
		{
			value = array[i];
			bar = new Bar((int)x, y, (int) width, value, originalColor);
			bar.draw(g);
			bars[i] = bar;

			// move to the next bar
			x += width;
		}

		bs.show();
		g.dispose();
	}

	public void sort() {
		
	};
	
	public void renderInstructionSet() {
	}
	
	
	public void drawArray()
	{
		if (!hasArray)
			return;

		g = bs.getDrawGraphics();

		for (int i = 0; i < bars.length; i++)
		{
			bars[i].draw(g);
		}
		if (instructions != null) {
			for (int i = 0; i < instructions.length; i++) {
				instructions[i].draw(g);
			}			
		}
		bs.show();
		g.dispose();
	}

	public void unhighlightIntructions() {
		for (int i = 0; i < instructions.length; i++) {
			instructions[i].unhightlight(g);
		}
	}
		
	public void pause() {
		if (!isContinuous) {
			while (!isContinue) {
				if (isContinuous) break;
				continue;
			}
			isContinue = false;			
		} else {
			try {
				Thread.sleep(speed);				
			} catch (Exception e) {
			}
		}
	}
	
	public void setContinous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}
	
	// swap 2 elements given 2 indexes
	protected void swap(int i, int j)
	{
		// swap the elements
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;

		// clear the bar
		bars[i].clear(g);
		bars[j].clear(g);

		// swap the drawings
		bars[j].setValue(bars[i].getValue());
		bars[i].setValue(temp);
		bars[i].draw(g);
		bars[j].draw(g);

		bs.show();
	}


	protected void colorPair(int i, int j, Color color)
	{
		Color color1 = bars[i].getColor(), color2 = bars[j].getColor();
		// drawing
		bars[i].setColor(color);
		bars[i].draw(g);

		bars[j].setColor(color);
		bars[j].draw(g);

		bs.show();

		pause();

		recoverColor(i, j, color1, color2);
		
	}
	
	
	
	protected void recoverColor(int i, int j, Color color1, Color color2) {
		bars[i].setColor(color1);
		bars[i].draw(g);

		bars[j].setColor(color2);
		bars[j].draw(g);

		bs.show();
	}
	
	protected Color getBarColor(int value)
	{
		return ColorManager.BAR_ORANGE;

	}
	
	protected void finishAnimation()
	{
		// swiping to green
		for (int i = 0; i < bars.length; i++)
		{
			bars[i].setColor(ColorManager.BAR_CYAN);
			bars[i].draw(g);
			bs.show();
		}
		
		sortFrame.setBtnPlayText("Play");
		isContinuous = false;		
	}
	

	// color the bar in speed time and put it
	// back to its original color
	protected void colorBar(int index, Color color)
	{
		Bar bar = bars[index];
		Color oldColor = bar.getColor();

		bar.setColor(color);
		bar.draw(g);
		bs.show();

		pause();
		
		bar.setColor(oldColor);
		bar.draw(g);

		bs.show();
		
	}
	
	protected void hightlightInstruction(Instruction instruction) {
		unhighlightIntructions();
		instruction.hightlight(g);
		bs.show();	
	}
	
	protected void hightlightInstruction(Instruction instruction1,Instruction instruction2) {
		unhighlightIntructions();
		instruction1.hightlight(g);
		instruction2.hightlight(g);
		bs.show();	
	}
	protected void hightlightInstruction(Instruction instruction1,Instruction instruction2, Instruction instruction3) {
		unhighlightIntructions();
		instruction1.hightlight(g);
		instruction2.hightlight(g);
		instruction3.hightlight(g);
		bs.show();	
	}
	
	protected void hightlight_quicksort(Instruction instruction) {
		instruction.hightlight(g);
		bs.show();	
	}
	
	protected void unhightlight_quicksort(Instruction instruction) {
		instruction.unhightlight(g);
		bs.show();	
	}
}
