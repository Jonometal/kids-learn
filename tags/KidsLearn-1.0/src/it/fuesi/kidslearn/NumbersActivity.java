package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class NumbersActivity extends AbstractFlipperActivity {
	
	private static final String[] NUMBERS = fillNumbers();
	private static final List<FlipperItem> items = FlipperItem.arrayFactory(NUMBERS);
	
	private static final String[] fillNumbers(){
		final String[] numbers = new String[10];
		for(int i=0; i<numbers.length; i++)
			numbers[i] = String.valueOf((i+1));
		return numbers;
	}
	
	@Override
	protected List<FlipperItem> getItems() {
		return items;
	}
		
	
	@Override
	protected void setColors(View v, View container, int backgroundColor, int contentColor) {
		final TextView text = (TextView)v;
		text.setTextColor(contentColor);
		container.setBackgroundColor(backgroundColor);
	}
	
}


