package com.hust.soict.globalict.miniproject.sort_visualizer;

import com.hust.soict.globalict.miniproject.entity.Bar;
import com.hust.soict.globalict.miniproject.entity.Instruction;
import com.hust.soict.globalict.miniproject.screen.SortCanvas;
import com.hust.soict.globalict.miniproject.screen.SortFrame;

public class InsertionSortVisualizer extends SortVisualizer {
	
	public InsertionSortVisualizer(SortCanvas sortCanvas, SortFrame sortFrame, int[] array) {
		super(sortCanvas, sortFrame, array);
	}

	/* INSERTION SORT */
	public void sort()
	{
		pause();
		// get graphics
		g = bs.getDrawGraphics();
		
		Bar bar;
		for (int i = 1; i < array.length; i++)
		{
			hightlightInstruction(instructions[0],instructions[1],instructions[2] );
			
			bars[i].setColor(ColorManager.BAR_RED);
			bars[i].draw(g);
			

			bs.show();
			bars[i].setColor(ColorManager.BAR_ORANGE);
			pause();
			// find the insertion location by comparing to its predecessor
			int index = i-1, element = array[i];
			
			if (index == 0 && element >= array[index]) {
				bars[index].setColor(getBarColor(index));
				bars[index].draw(g);
//				pause();
			}
			
			hightlightInstruction(instructions[3]);
			while (index >= 0 && element < array[index])
			{
				hightlightInstruction(instructions[3]);
				colorPair(index, index + 1, ColorManager.BAR_GREEN);
				
//				pause();
				bars[index].setColor(ColorManager.BAR_RED);
				bars[index + 1].setColor(ColorManager.BAR_ORANGE);
				
				swap(index, index+1);
				hightlightInstruction(instructions[4],instructions[5]);
				pause();
				hightlightInstruction(instructions[3]);
				index--;
				
			}
			if (index >= 0) {
				colorPair(index, index + 1, ColorManager.BAR_GREEN);
			}
			index++;

			bar = bars[index];
			bar.clear(g);
			bar.setValue(element);
			bar.setColor(getBarColor(index));
			bar.draw(g);

			bs.show();
			unhighlightIntructions();

		}

		finishAnimation();
		unhighlightIntructions();
		g.dispose();
	}

	@Override
	public void renderInstructionSet() {
		super.renderInstructionSet();
		
		int x = PADDING;
		int y = canvasHeight - 250 - PADDING;
		int instruction_width = 500;
		instructions = new Instruction[6];
		instructions[0] = new Instruction(x, y, instruction_width, "for (int i = 1; i < n; i++)", 0);
		instructions[1] = new Instruction(x, y + 34, instruction_width, "key = arr[i]", 1);
		instructions[2] = new Instruction(x, y + 34 * 2, instruction_width, "j = i - 1", 1);
		instructions[3] = new Instruction(x, y + 34 * 3, instruction_width, "while ( j >= 0 && arr[j] > key )", 1);
		instructions[4] = new Instruction(x, y + 34 * 4, instruction_width, "swap(a[j], a[i])", 2);
		instructions[5] = new Instruction(x, y + 34 * 5, instruction_width, "j = j - 1", 2);
		
		
	}
}
