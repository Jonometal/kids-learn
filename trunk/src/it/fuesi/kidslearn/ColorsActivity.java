package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.List;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ColorsActivity extends AbstractFlipperActivity {

	private static int[] COLORS = {
		R.color.red,
		R.color.green,
		R.color.yellow,
		R.color.blue
	};
	
	private static int[] COLORS_LABELS = {
		R.string.label_red,
		R.string.label_green,
		R.string.label_yellow,
		R.string.label_blue
	};
	
	private static List<FlipperItem> items = FlipperItem.arrayFactory(COLORS, COLORS_LABELS);
	
	@Override
	protected List<FlipperItem> getItems() {
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


