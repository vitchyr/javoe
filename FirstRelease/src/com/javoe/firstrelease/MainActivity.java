package com.javoe.firstrelease;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
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
		String text;
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
			
			imgView = new ImageView(c);
			imgView.setImageResource(imageId);
			txtView = new TextView(c);
			txtView.setText(text);
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
		
		ArrayList<Restaurant> restaurants = getRestaurants();
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
		((ScrollView)findViewById(R.id.scrMain)).addView(rView.getLinLay());
	}

	public void openRestaurant(View v){
		startActivity(new Intent("android.intent.action.Restaurant"));
	}
	
	public RestaurantView createView(Restaurant r){
		RestaurantView rView = new RestaurantView(this, r.imgId, r.text);
		return rView;
	}
	
	public ArrayList<Restaurant> getRestaurants(){
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		// ... Get Restaurants from database ...
		
		return restaurants;
	}

}
