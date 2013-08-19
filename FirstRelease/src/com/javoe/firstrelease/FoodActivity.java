package com.javoe.firstrelease;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FoodActivity extends Activity implements OnClickListener{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);
		
		getActionBar().hide();
		
		((Button)findViewById(R.id.btnFoodBack)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
		
	}
}
