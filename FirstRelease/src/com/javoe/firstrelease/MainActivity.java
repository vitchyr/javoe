package com.javoe.firstrelease;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity{

	//Restaurant Class
	class Restaurant{
		int imgId;
		String name;
		
		public Restaurant(int i, String s){
			imgId = i;
			name = s;
		}
	}
	
	// RestaurantView Class
	class RestaurantView{
		private LinearLayout linLay;
		private ImageView imgView;
		private TextView txtView;
		
		public RestaurantView(Context c, int imageId, String text){
			linLay = new LinearLayout(c);
			linLay.setOrientation(LinearLayout.HORIZONTAL);
			linLay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			linLay.setGravity(Gravity.LEFT);
			linLay.setPadding(0, 5, 0, 5);
			
			imgView = new ImageView(c);
			imgView.setLayoutParams(new LayoutParams((int)(60 * getResources().getDisplayMetrics().density), (int)(60 * getResources().getDisplayMetrics().density)));
			imgView.setImageResource(imageId);
			txtView = new TextView(c);
			txtView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			txtView.setText(text);
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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//  D A T A B A S E
		List<Restaurant> restaurants = getRestaurants();
		for(int i = 0; i < restaurants.size(); i++){
			Restaurant r = restaurants.get(i);
			RestaurantView rView = createView(r);
			rView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					openRestaurant(v);
				}
			});
			addView(rView);
		}
	}
	
	private void addView(RestaurantView rView) {
		((LinearLayout)findViewById(R.id.linMain)).addView(rView.getLinLay());
	}

	public void openRestaurant(View v){
		startActivity(new Intent("android.intent.action.Restaurant"));
	}
	
	//  D A T A B A S E
	public RestaurantView createView(Restaurant r){
		RestaurantView rView = new RestaurantView(this, r.imgId, r.name);
		return rView;
	}
	
	public List<Restaurant> getRestaurants(){

		
		//  Fix for D A T A B A S E
		List<Restaurant> restaurants = new ArrayList<Restaurant>();;
		//DatabaseHandler db = new DatabaseHandler(this);
		//restaurants = db.getAllCompanies();
		
		// ------    T E S T I N G  -------
		restaurants.add(new Restaurant(R.drawable.burger_king, "Burger King"));
		restaurants.add(new Restaurant(R.drawable.mcdonalds, "McDonalds"));
		restaurants.add(new Restaurant(R.drawable.bojangles, "Bojangles"));
		restaurants.add(new Restaurant(R.drawable.chick_fil_a, "Chick-Fil-A"));
		restaurants.add(new Restaurant(R.drawable.five_guys, "Five Guys"));
		restaurants.add(new Restaurant(R.drawable.jimmy_johns, "Jimmy John's"));
		restaurants.add(new Restaurant(R.drawable.starbucks, "Starbucks"));
		restaurants.add(new Restaurant(R.drawable.cook_out, "Cook Out"));
		restaurants.add(new Restaurant(R.drawable.subway, "Subway"));
		restaurants.add(new Restaurant(R.drawable.wendys, "Wendy's"));
		return restaurants;
		
	}

}
