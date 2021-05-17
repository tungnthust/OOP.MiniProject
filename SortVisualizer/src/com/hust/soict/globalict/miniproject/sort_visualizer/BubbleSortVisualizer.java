package com.hust.soict.globalict.miniproject.sort_visualizer;

import com.hust.soict.globalict.miniproject.entity.Instruction;
import com.hust.soict.globalict.miniproject.screen.SortCanvas;
import com.hust.soict.globalict.miniproject.screen.SortFrame;

public class BubbleSortVisualizer extends SortVisualizer{

	public BubbleSortVisualizer(SortCanvas sortCanvas, SortFrame sortFrame, int[] array) {
		super(sortCanvas, sortFrame, array);
	}
	
	/* BUBBLE SORT */
	@Override
	public void sort()
	{
		pause();
		// get graphics
        g = bs.getDrawGraphics();

		
		int count = 0;
		
		for (int i = array.length - 1; i >= 0; i--)
		{
			hightlightInstruction(instructions[0]);
			
			pause();
			count = 0;
			
			for (int j = 0; j < i; j++)
			{
				bars[j].setColor(comparingColor);
				bars[j].draw(g);
				
				hightlightInstruction(instructions[1]);
				pause();
				
				hightlightInstruction(instructions[2]);

				bars[j].setColor(originalColor);
				colorPair(j, j+1, ColorManager.BAR_GREEN);
				
				if (array[j] > array[j+1])
				{
					hightlightInstruction(instructions[3]);
					
					swap(j, j+1);
					colorPair(j, j+1, ColorManager.BAR_GREEN);
					
					count++;
				}

			}

			bars[i].setColor(ColorManager.BAR_ORANGE); //bar in correct position
			bars[i].draw(g);
			bs.show();

			if (count == 0)  // the array is sorted
				break;
		}

		finishAnimation();
		unhighlightIntructions();
		bs.show();
		g.dispose();
	}
	
	@Override
	public void renderInstructionSet() {
		int x = PADDING;
		int y = canvasHeight - 250 - PADDING;
		int instruction_width = 300;
		instructions = new Instruction[4];
		instructions[0] = new Instruction(x, y, instruction_width, "for (int i = n - 1; i >= 0 ; i--)", 0);
		instructions[1] = new Instruction(x, y + 34, instruction_width, "for (int j = 0; j < i ; j++)", 1);
		instructions[2] = new Instruction(x, y + 34 * 2, instruction_width, "if (a[j] > a[j + 1])", 2);
		instructions[3] = new Instruction(x, y + 34 * 3, instruction_width, "swap(a[j], a[j + 1])", 3);
	}

}
