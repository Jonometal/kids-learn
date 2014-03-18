package it.fuesi.kidslearn;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public abstract class AbstractFullScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	}
	
	
	private void manageUIHide(){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
			kitHide();
		else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) 
			icsHide();
		else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			honeyHide();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void honeyHide(){
		getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
			
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void icsHide(){
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void kitHide(){
		getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	        super.onWindowFocusChanged(hasFocus);
	    if (hasFocus) {
	    	manageUIHide();
	    }
	}
	
}
