package it.fuesi.kidslearn;

import it.fuesi.kidslearn.model.FlipperItem;

import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

public abstract class AbstractFlipperActivity extends AbstractFullScreenActivity {
	

	private ViewFlipper mFlipper;
	private TextView mContentDescription;
	private View mContainer;
	private int mCount = 1;
	private GestureDetector mGestureDetector;
	
	private int min = 1;
	private int max = 1;
	
	SharedPreferences mPrefs;
	
	protected abstract List<FlipperItem> getItems();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_flipper);
		
		mContainer = (View)findViewById(R.id.flipper_parent);
		
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		max = getItems().size();
		
		mFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
		
		mContentDescription = (TextView)findViewById(R.id.content_description);
		
		createFlipper();
		
		bindContentDescription(mCount-1);
		
		
		mGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						
							if (velocityX < -10.0f) {
								nextItem();
							}else if(velocityX > 10.0f){
								previousItem();
							}
						
							bindContentDescription(mCount-1);
							
						
						return true;
					}
				});
		
	}
	
	protected SharedPreferences getSettings(){
		return mPrefs;
	}
	
	private int getColor(String prefKey, String defaultColor){
		final String colorString = mPrefs.getString(prefKey, defaultColor);
		
		int color = 0;
		
		if(colorString.equals("white"))
			color = R.color.white;
		else if(colorString.equals("black"))
			color = R.color.black;
		else if(colorString.equals("grey"))
			color = R.color.grey;
		else if(colorString.equals("yellow"))
			color = R.color.yellow;
		else if(colorString.equals("red"))
			color = R.color.red;
		else if(colorString.equals("green"))
			color = R.color.green;
		else if(colorString.equals("blue"))
			color = R.color.blue;
		
		
		return getResources().getColor(color);
	}
	
	protected abstract void setColors(View v, View container, int backgroundColor, int contentColor);
	
	
	protected void setContentDescription(String description){
		mContentDescription.setVisibility(View.VISIBLE);
		mContentDescription.setText(description);
	}
	
	protected void bindContentDescription(int itemPosition){
		final FlipperItem item = getItems().get(itemPosition);
		if(item.getLabel() != 0)
			setContentDescription(getString(item.getLabel()));
	}
	
	protected void createFlipper(){
		mFlipper.removeAllViews();
		
		final List<FlipperItem> items = getItems();
		
		for(FlipperItem item : items){
			View v = LayoutInflater.from(this).inflate(getFlipperItemLayout(), null);
			bindFlipperItemView(v, item, mFlipper);
			
			setColors(v, mContainer, getColor("background_color", "white"),getColor("content_color", "black")); //FIXME
			
			mFlipper.addView(v);
		}
	}
	
	
	
	protected int getFlipperItemLayout() {
		return R.layout.flipperitem_textview;
	}
	
	protected void bindFlipperItemView(View v, FlipperItem item, ViewFlipper flipper){
		TextView view = (TextView)v;
		view.setText(item.getName());
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	public void nextItem() {
		if(mCount >= max) {
			mFlipper.setDisplayedChild(0);
			mCount = 1;
			return;
		};
		

		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());
		
		mCount++;

		mFlipper.showNext();
		
	}
	
	public void previousItem() {
		if(mCount <= min) {
			mFlipper.setDisplayedChild(max-1);
			mCount = max;
			return;
		}
		
		mFlipper.setInAnimation(inFromLeftAnimation());
		mFlipper.setOutAnimation(outToRightAnimation());

		mCount--;

		mFlipper.showPrevious();
		
	}

	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new LinearInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new LinearInterpolator());
		return outtoLeft;
	}
	
	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new LinearInterpolator());
		return inFromLeft;
	}

	private Animation outToRightAnimation() {
		Animation outToRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToRight.setDuration(500);
		outToRight.setInterpolator(new LinearInterpolator());
		return outToRight;
	}
	
	
}


