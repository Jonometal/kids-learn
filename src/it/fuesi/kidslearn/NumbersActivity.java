package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class NumbersActivity extends AbstractFlipperActivity {
	
	private static List<FlipperItem> items = null;
	
	private final String[] fillNumbers(){
		final String[] numbers = new String[getMaxNumber()];
		for(int i=0; i<numbers.length; i++)
			numbers[i] = String.valueOf((i+1));
		return numbers;
	}
	
	@Override
	protected List<FlipperItem> getItems() {
		if(items == null)
			items = FlipperItem.arrayFactory(fillNumbers());
		
		return items;
	}
		
	
	@Override
	protected void setColors(View v, View container, int backgroundColor, int contentColor) {
		final TextView text = (TextView)v;
		text.setTextColor(contentColor);
		container.setBackgroundColor(backgroundColor);
	}
	
	private int getMaxNumber(){
		return Integer.parseInt(getSettings().getString("max_number", "10"));
	}
	
}


