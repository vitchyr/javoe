package com.javoe.firstrelease;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends Activity implements OnClickListener{
	ArrayList<Food> foods;
	int criteria;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);
		
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		foods = (ArrayList<Food>) getIntent().getExtras().get("foods");
		Food f = foods.get(getIntent().getExtras().getInt("foodIndex"));
		
		//Initialize Food Information
		ImageView foodImage = (ImageView)findViewById(R.id.imgFood);
		TextView foodName = (TextView)findViewById(R.id.txtFood);
		TextView txtCalories = (TextView)findViewById(R.id.txtCalories);
		TextView txtProtein = (TextView)findViewById(R.id.txtProtein);
		TextView txtCarbs = (TextView)findViewById(R.id.txtCarbs);
		TextView txtSugars = (TextView)findViewById(R.id.txtSugars);
		TextView txtFat = (TextView)findViewById(R.id.txtFat);
		TextView txtSaturatedFat = (TextView)findViewById(R.id.txtSaturatedFat);
		TextView txtTransFat = (TextView)findViewById(R.id.txtTransFat);
		TextView txtCholesterol = (TextView)findViewById(R.id.txtCholesterol);
		TextView txtSodium = (TextView)findViewById(R.id.txtSodium);
	
		foodImage.setImageResource(f.imgId);
		foodName.setText(f.name);
		txtCalories.setText("" + f.calories);
		txtProtein.setText("" + f.protein);
		txtCarbs.setText("" + f.carb);
		txtSugars.setText("" + f.sugars);
		txtFat.setText("" + f.total_fat);
		txtSaturatedFat.setText("" + f.saturated_fat);
		txtTransFat.setText("" + f.trans_fat);
		txtCholesterol.setText("" + f.chol);
		txtSodium.setText("" + f.sodium);	
		
		
		//Initialize Alternates
		ImageView alt1Image = (ImageView)findViewById(R.id.imgAlternate1);
		TextView alt1Text = (TextView)findViewById(R.id.txtAlternate1);
		ImageView alt2Image = (ImageView)findViewById(R.id.imgAlternate2);
		TextView alt2Text = (TextView)findViewById(R.id.txtAlternate2);
		
		
		
		Food alternates[] = getAlternates(f);
		criteria = getIntent().getExtras().getInt("criteria");
		
		alt1Image.setTag(alternates[0]);
		alt1Text.setTag(alternates[0]);
		alt2Image.setTag(alternates[1]);
		alt2Text.setTag(alternates[1]);
		
		switch(criteria){
			case 0:
				alt1Text.setText(alternates[0].name);
				alt2Text.setText(alternates[1].name);
				break;
			case 1:
				alt1Text.setText("Protein: " + alternates[0].protein);
				alt2Text.setText("Protein: " + alternates[1].protein);
				break;
			case 2:
				alt1Text.setText("Calories: " + alternates[0].calories);
				alt2Text.setText("Calories: " + alternates[1].calories);
				break;
			case 3:
				alt1Text.setText("Fat: " + alternates[0].total_fat);
				alt2Text.setText("Fat: " + alternates[1].total_fat);
				break;
			case 4:
				alt1Text.setText("Carbs: " + alternates[0].carb);
				alt2Text.setText("Carbs: " + alternates[1].carb);
				break;
			default:
				break;
		}
		
		alt1Image.setImageResource(alternates[0].imgId);
		alt2Image.setImageResource(alternates[1].imgId);
		
		
		alt1Image.setOnClickListener(this);
		alt1Text.setOnClickListener(this);
		alt2Image.setOnClickListener(this);
		alt2Text.setOnClickListener(this);
	}

	public Food[] getAlternates(Food f){
		Food[] fs = new Food[2];
		
		switch (foods.indexOf(f)){
			case 0:
				fs[0] = foods.get(1);
				fs[1] = foods.get(2);
				break;
			case 1:
				fs[0] = foods.get(0);
				fs[1] = foods.get(2);
				break;
			default:
				fs[0] = foods.get(foods.indexOf(f) - 2);
				fs[1] = foods.get(foods.indexOf(f) - 1);
				break;
		}
		return fs;
	}
	
	@Override
	public void onClick(View v) {
		Food f = ((Food)v.getTag());
		
		Intent i = new Intent("android.intent.action.Food");
		i.putExtra("foodIndex", foods.indexOf(f));
		i.putExtra("foods", foods);
		i.putExtra("criteria", criteria);
		startActivity(i);
	}
}
