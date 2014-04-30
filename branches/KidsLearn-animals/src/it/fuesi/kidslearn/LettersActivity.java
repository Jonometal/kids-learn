package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class LettersActivity extends AbstractFlipperActivity {
	
	private static final String[] LETTERS = fillLetters();
	private static final List<FlipperItem> items = FlipperItem.arrayFactory(LETTERS);
	
	private static final String[] fillLetters(){
		final String[] letters = new String[26];
		for(int i=0; i<letters.length; i++)
			letters[i] = String.valueOf(Character.toChars(i+65));
		return letters;
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


