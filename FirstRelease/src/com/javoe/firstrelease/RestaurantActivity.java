package com.javoe.firstrelease;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class RestaurantActivity extends Activity implements OnClickListener {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.restaurant);
		getActionBar().hide();
		
		/*((LinearLayout)findViewById(R.id.food1)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food2)).setOnClickListener(this);
		*/
		
		((LinearLayout)findViewById(R.id.food3)).setOnClickListener(this);
		
		/*((LinearLayout)findViewById(R.id.food4)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food5)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food6)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food7)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food8)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food9)).setOnClickListener(this);
		((LinearLayout)findViewById(R.id.food10)).setOnClickListener(this);
		*/
		((Button)findViewById(R.id.btnRestaurantBack)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnRestaurantBack)
			finish();
		else
			startActivity(new Intent("android.intent.action.Food"));
	}
}
