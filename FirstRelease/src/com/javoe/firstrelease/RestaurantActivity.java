package com.javoe.firstrelease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.javoe.firstrelease.MainActivity.Restaurant;
import com.javoe.firstrelease.MainActivity.RestaurantView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RestaurantActivity extends Activity implements OnClickListener{
	
			
	// FoodView Class
	class FoodView{
		private LinearLayout linLay;
		private ImageView imgView;
		private TextView txtView;
		private Food food;
		
		public FoodView(Context c, Food f){
			food = f;
			
			linLay = new LinearLayout(c);
			linLay.setOrientation(LinearLayout.HORIZONTAL);
			linLay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			linLay.setGravity(Gravity.LEFT);
			linLay.setPadding(0, 5, 0, 5);
			
			imgView = new ImageView(c);
			imgView.setLayoutParams(new LayoutParams((int)(60 * getResources().getDisplayMetrics().density), (int)(60 * getResources().getDisplayMetrics().density)));
			imgView.setImageResource(f.imgId);
			txtView = new TextView(c);
			txtView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			txtView.setText(f.name);
			txtView.setTextSize(30);
			txtView.setGravity(Gravity.CENTER);
			txtView.setPadding(5, 0, 0, 0);
			
			linLay.addView(imgView);
			linLay.addView(txtView);
			linLay.setGravity(Gravity.LEFT);
			
			imgView.setVisibility(1);
			txtView.setVisibility(1);
			linLay.setVisibility(1);
			
		}
		
		// Sets the OnClickListener for the object
		public void setOnClickListener(OnClickListener l){
			linLay.setOnClickListener(l);
		}
		
		// Returns the object's LinearLayout
		public LinearLayout getLinLay(){
			return linLay;
		}
	}
	
	//Fields
	ArrayList<Food> foods;
	boolean descending = false;
	Spinner menuSpinner1;
	ToggleButton menuToggle1;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.restaurant);
		
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	
		foods = getFoods();
		addFoodViews(foods);
	}
	
	public void addFoodViews(ArrayList<Food> foods){
		for(int i = 0; i < foods.size(); i++){
			Food f = foods.get(i);
			FoodView fView = new FoodView(this, f);
			
			fView.getLinLay().setTag(f);
			
			fView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					openFood(v);
				}
			});
			((LinearLayout)findViewById(R.id.linRestaurant)).addView(fView.getLinLay());
		}
	}
	
	public void sortItems (){
		ArrayList<Integer> a;
		Integer[] r;
		ArrayList<Food> nu = new ArrayList<Food>();
		
		switch (menuSpinner1.getSelectedItemPosition()){
			case 0:
				//sort by name
				ArrayList<String> b = new ArrayList<String>();
				for(Food f : foods)
					b.add(f.name);
				String[] s = b.toArray(new String[b.size()]);
				Arrays.sort(s);
				b.clear();
				
				if(descending)
					for(int i = 1; i <= s.length; i++){
						for(Food o : foods)
							if (o.name.equals(s[s.length - i]) && !(nu.contains(o)))
								nu.add(o);
					}
				else
					for(int i = 0; i < s.length; i++){
						for(Food o : foods)
							if (o.name.equals(s[i]) && !(nu.contains(o)))
								nu.add(o);
					}
				break;
			case 1:
				a = new ArrayList<Integer>();
				for(Food f : foods)
					a.add(f.protein);
				r = a.toArray(new Integer[a.size()]);
				Arrays.sort(r);
				a.clear();
				
				if(descending)
					for(int i = 1; i <= r.length; i++){
						for(Food o : foods)
							if (o.protein == r[r.length - i] && !(nu.contains(o)))
								nu.add(o);
					}
				else
					for(int i = 0; i < r.length; i++){
						for(Food o : foods)
							if (o.protein == r[i] && !(nu.contains(o)))
								nu.add(o);
					}
				break;
			case 2:
				a = new ArrayList<Integer>();
				for(Food f : foods)
					a.add(f.calories);
				r = a.toArray(new Integer[a.size()]);
				Arrays.sort(r);
				a.clear();
				
				if(descending)
					for(int i = 1; i <= r.length; i++){
						for(Food o : foods)
							if (o.calories == r[r.length - i] && !(nu.contains(o)))
								nu.add(o);
					}
				else
					for(int i = 0; i < r.length; i++){
						for(Food o : foods)
							if (o.calories == r[i] && !(nu.contains(o)))
								nu.add(o);
					}
				break;
			case 3:
				a = new ArrayList<Integer>();
				for(Food f : foods)
					a.add(f.total_fat);
				r = a.toArray(new Integer[a.size()]);
				Arrays.sort(r);
				a.clear();
				
				if(descending)
					for(int i = 1; i <= r.length; i++){
						for(Food o : foods)
							if (o.total_fat == r[r.length - i] && !(nu.contains(o)))
								nu.add(o);
					}
				else
					for(int i = 0; i < r.length; i++){
						for(Food o : foods)
							if (o.total_fat == r[i] && !(nu.contains(o)))
								nu.add(o);
					}
				//sort by fat
				break;
			case 4:
				a = new ArrayList<Integer>();
				for(Food f : foods)
					a.add(f.carb);
				r = a.toArray(new Integer[a.size()]);
				Arrays.sort(r);
				a.clear();
				
				if(descending)
					for(int i = 1; i <= r.length; i++){
						for(Food o : foods)
							if (o.carb == r[r.length - i] && !(nu.contains(o)))
								nu.add(o);
					}
				else
					for(int i = 0; i < r.length; i++){
						for(Food o : foods)
							if (o.carb == r[i] && !(nu.contains(o)))
								nu.add(o);
					}
				//sort by carbs
				break;
			default:
				break;
		}
		foods = nu;
		((LinearLayout)findViewById(R.id.linRestaurant)).removeAllViews();
		addFoodViews(foods);
	}
	
	public void openFood(View v){
		//Pass Food information
		Food f = ((Food)(v.getTag()));
		Intent i = new Intent("android.intent.action.Food");
		i.putExtra("foodIndex", foods.indexOf(f));
		i.putExtra("foods", foods);
		
		i.putExtra("criteria", menuSpinner1.getSelectedItemPosition());
		startActivity(i);
	}
	
	public ArrayList<Food> getFoods(){
		ArrayList<Food> foods = new ArrayList<Food>();
		
		// ----- T E S T I N G  -------
		foods.add(new Food(0, R.drawable.alaskan_fish, "Alaskan Fish", 16,0,0,54,0,0,0,0,0,28,530,0));
		foods.add(new Food(0, R.drawable.bacon_burger, "Bacon Burger", 14,0,0,31,0,0,0,0,0,17,320,0));
		foods.add(new Food(0, R.drawable.caesar_wrap, "Caesar Wrap", 21,0,0,37,0,0,0,0,0,18,390,0));
		foods.add(new Food(0, R.drawable.cheeseburger, "Cheeseburger", 14,0,0,28,0,0,0,0,0,13,290,0));
		foods.add(new Food(0, R.drawable.chickn_crisp, "Chick'n Crisp", 13,0,0,40,0,0,0,0,0,30,470,0));
		foods.add(new Food(0, R.drawable.double_stacker, "Double Stacker", 26,0,0,32,0,0,0,0,0,30,490,0));
		foods.add(new Food(0, R.drawable.original_chicken, "Original Chicken", 24,0,0,46,0,0,0,0,0,39,630,0));
		foods.add(new Food(0, R.drawable.single_stacker, "Single Stacker", 16,0,0,32,0,0,0,0,0,21,370,0));
		foods.add(new Food(0, R.drawable.tendercrisp, "Tendercrisp", 30,0,0,58,0,0,0,0,0,45,750,0));
		foods.add(new Food(0, R.drawable.tendergrill, "Tendergrill", 31,0,0,43,0,0,0,0,0,22,510,0));
		foods.add(new Food(0, R.drawable.whopper, "Whopper", 25,0,0,57,0,0,0,0,0,35,630,0));
		
		return foods;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.restaurant_activity_actionbar, menu);
		
		//Set Spinner options
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filters, R.layout.spinner_item);
		menuSpinner1 = (Spinner)(((MenuItem)(menu.findItem(R.id.menu_spinner1))).getActionView());
		menuSpinner1.setAdapter(adapter);
		
		//Set ToggleButton text
		menuToggle1 =  (ToggleButton)(((MenuItem)(menu.findItem(R.id.menu_toggle1))).getActionView());
		menuToggle1.setText("▲");
		menuToggle1.setTextOn("▼");
		menuToggle1.setTextOff("▲");
		menuToggle1.setTextSize(30);
		
		menuSpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
		    int count=0;
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		        if(count >= 1){
		    		if(menuToggle1.isChecked())
		    			descending = true;
		    		else
		    			descending = false;
		    		sortItems();		        	
		        }
		        else
		        	count++;
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		    }
		});
		
		menuToggle1.setOnClickListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		if(menuToggle1.isChecked())
			descending = true;
		else
			descending = false;
		sortItems();
	}
}
