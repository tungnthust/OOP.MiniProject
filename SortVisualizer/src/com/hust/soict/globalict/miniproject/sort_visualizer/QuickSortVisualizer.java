package com.hust.soict.globalict.miniproject.sort_visualizer;

import com.hust.soict.globalict.miniproject.entity.Instruction;
import com.hust.soict.globalict.miniproject.screen.SortCanvas;
import com.hust.soict.globalict.miniproject.screen.SortFrame;

public class QuickSortVisualizer extends SortVisualizer{

	public QuickSortVisualizer(SortCanvas sortCanvas, SortFrame sortFrame, int[] array) {
		super(sortCanvas, sortFrame, array);
	}
	
	/* QUICK SORT */
	public void sort()
	{
		pause();

		g = bs.getDrawGraphics();

		quickSort(0, array.length - 1);

		finishAnimation();
		g.dispose();
	}


	// recursive quick sort
	private void quickSort(int start, int end)
	{
		if (start <= end)
		{
			// place pivot in correct spot
			int pivot = partition(start, end);

			// sort the left half
			quickSort(start, pivot-1);

			// sort the right half
			quickSort(pivot+1, end);
		}
	}


	// quick sort partition
	private int partition(int l, int r)
	{
		int pivotIndex = l;
		hightlight_quicksort(instructions[0]);
		hightlight_quicksort(instructions[1]);
		hightlight_quicksort(instructions[2]);
		renderBar(bars[pivotIndex], ColorManager.BAR_YELLOW);
		pause();
		unhightlight_quicksort(instructions[0]);
		unhightlight_quicksort(instructions[1]);
		unhightlight_quicksort(instructions[2]);
		int storeIndex = l + 1;
	    for (int i = l + 1; i <= r; i++) {
	    	renderBar(bars[i], ColorManager.BAR_RED);
	    	hightlight_quicksort(instructions[3]);
	    	hightlight_quicksort(instructions[4]);
	    	bars[i].setColor(ColorManager.BAR_BLUE);
	    	if (array[i] < array[pivotIndex]) {
	    		pause();
	    		swap(i, storeIndex);
	    		unhightlight_quicksort(instructions[4]);
	    		hightlight_quicksort(instructions[5]);
	    		renderBar(bars[storeIndex], ColorManager.BAR_GREEN);
	    		pause();
	    		unhightlight_quicksort(instructions[5]);
	    		hightlight_quicksort(instructions[4]);
	    		storeIndex ++;
	    		continue;
	    	}
	    	pause();
	    	unhightlight_quicksort(instructions[4]);
	    	renderBar(bars[i], ColorManager.BAR_BLUE);
	    }
	    
	    int newPivotIndex = storeIndex - 1;
	    unhightlight_quicksort(instructions[4]);
	    pause();
	    unhightlight_quicksort(instructions[3]);
	    hightlight_quicksort(instructions[6]);
		bars[pivotIndex].setColor(ColorManager.BAR_GREEN);
		bars[newPivotIndex].setColor(ColorManager.BAR_YELLOW);
	    swap(pivotIndex, newPivotIndex);
		bars[newPivotIndex].setColor(ColorManager.BAR_ORANGE);
	    pause();
	    unhightlight_quicksort(instructions[6]);
	    for (int i = l; i <= r; i++) {
	    	if (i != newPivotIndex) {
	    		bars[i].setColor(ColorManager.BAR_CYAN);
	    	}
	    	bars[i].draw(g);
	    }
	    bs.show();
	    pause();

		return newPivotIndex;
	}
	
	@Override
	public void renderInstructionSet() {
//		super.renderInstructionSet();
		int x = PADDING ;
		int y = canvasHeight - 250 - PADDING;
		int instruction_width = 360;
		instructions = new Instruction[7];
		instructions[0] = new Instruction(x, y, instruction_width, "for each (unsorted) partition", 0);
		instructions[1] = new Instruction(x, y + 34, instruction_width, "set first element as pivot", 0);
		instructions[2] = new Instruction(x, y + 34 * 2, instruction_width, "storeIndex = pivotIndex + 1", 1);
		instructions[3] = new Instruction(x, y + 34 * 3, instruction_width, "for i = pivoteIndex + 1 to rightmostIndex", 1);
		instructions[4] = new Instruction(x, y + 34 * 4, instruction_width, "if element[i] < element[pivot]", 2);
		instructions[5] = new Instruction(x, y + 34 * 5, instruction_width, "swap(i, storeIndex); storeIndex++", 3);
		instructions[6] = new Instruction(x, y + 34 * 6, instruction_width, "swap(pivot, storeIndex - 1)", 1);
		
	}
}
