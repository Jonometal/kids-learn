package it.fuesi.kidslearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
	

	private View btnNumbers;
	private View btnLetters;
	private View btnColors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		btnNumbers = findViewById(R.id.btn_numbers);
		btnLetters = findViewById(R.id.btn_letters);
		btnColors = findViewById(R.id.btn_colors);
		
		btnNumbers.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
				startActivity(intent);
			}
		});
		
		btnLetters.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, LettersActivity.class);
				startActivity(intent);
			}
		});
		
		btnColors.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings:{
				openSettings();
			}
			break;
		}
		return true;
	}
	
	private void openSettings(){
		final Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	
	
}
