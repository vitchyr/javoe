package com.javoe.firstrelease;

import android.app.Activity;
import android.os.Bundle;

public class FoodActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);
		
		getActionBar().hide();
	}
}
