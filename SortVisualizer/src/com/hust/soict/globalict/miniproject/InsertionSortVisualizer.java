package com.hust.soict.globalict.miniproject;

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
			}
			
			while (index >= 0 && element < array[index])
			{
				colorPair(index, index + 1, ColorManager.BAR_GREEN);
				bars[index].setColor(ColorManager.BAR_RED);
				bars[index + 1].setColor(ColorManager.BAR_ORANGE);
				swap(index, index+1);
				pause();
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
		}

		finishAnimation();

		g.dispose();
	}

	@Override
	public void renderInstructionSet() {
		super.renderInstructionSet();
		
		
		
		
	}
}
