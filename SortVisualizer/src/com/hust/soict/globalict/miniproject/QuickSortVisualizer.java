package com.hust.soict.globalict.miniproject;

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
		renderBar(bars[pivotIndex], ColorManager.BAR_YELLOW);
		pause();
		int storeIndex = l + 1;
	    for (int i = l + 1; i <= r; i++) {
	    	renderBar(bars[i], ColorManager.BAR_RED);
	    	bars[i].setColor(ColorManager.BAR_BLUE);
	    	if (array[i] < array[pivotIndex]) {
	    		pause();
	    		swap(i, storeIndex);
	    		renderBar(bars[storeIndex], ColorManager.BAR_GREEN);
	    		pause();
	    		storeIndex ++;
	    		continue;
	    	}
	    	pause();
	    	renderBar(bars[i], ColorManager.BAR_BLUE);
	    }
	    
	    int newPivotIndex = storeIndex - 1;
	    pause();
		bars[pivotIndex].setColor(ColorManager.BAR_GREEN);
		bars[newPivotIndex].setColor(ColorManager.BAR_YELLOW);
	    swap(pivotIndex, newPivotIndex);
		bars[newPivotIndex].setColor(ColorManager.BAR_ORANGE);
	    pause();
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
		super.renderInstructionSet();
		
		
		
	}
}
