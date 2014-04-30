package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.LinkedList;
import java.util.List;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ColorsActivity extends AbstractFlipperActivity {

	private static String[] COLORS = {
		"red",
		"blue",
		"yellow",
		"green",
		"purple"
	};
	
	private static int[] COLORS_ID = {
		R.color.red,
		R.color.blue,
		R.color.yellow,
		R.color.green,
		R.color.purple
	};
	
	private static int[] COLORS_LABELS = {
		R.string.label_red,
		R.string.label_blue,
		R.string.label_yellow,
		R.string.label_green,
		R.string.label_purple
	};
	
	@Override
	protected List<FlipperItem> getItems() {
		
		final List<FlipperItem> items = new LinkedList<FlipperItem>();
		int i = 0;
		for(String color : COLORS){
			if(getSettings().getBoolean("available_color_" + color, false)){
				FlipperItem item = new FlipperItem(COLORS_ID[i]);
				item.setLabel(COLORS_LABELS[i]);
				items.add(item);
			}
			i++;
		}
		
		return items;
	}
	
	@Override
	protected int getFlipperItemLayout() {
		return R.layout.flipperitem_imageview;
	}
		
	@Override
	protected void bindFlipperItemView(View v, FlipperItem item, ViewFlipper flipper) {
		
		ImageView image = (ImageView)v;

		ShapeDrawable rect = new ShapeDrawable(new RectShape());
		rect.getPaint().setColor(getResources().getColor(item.getId()));
		rect.setIntrinsicHeight(100);
		rect.setIntrinsicWidth(100);
		
		image.setImageDrawable(rect);
		
	}
	
	@Override
	protected void setColors(View v, View container, int backgroundColor, int contentColor) {
		final RelativeLayout rel = (RelativeLayout)container;
		final TextView contentDescription = (TextView)rel.findViewById(R.id.content_description);
		contentDescription.setTextColor(contentColor);
		rel.setBackgroundColor(backgroundColor);
	}
	
}


