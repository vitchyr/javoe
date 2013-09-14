import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class Javoe extends Activity 
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_javoe);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		Company companyA = new Company(1,2,"Burger King");
		Company companyB = new Company(4,5,"McDonalds");
		
		Log.d("Insert: ", "Inserting .."); 
		db.addCompany(companyA);
		db.addCompany(companyB);
        
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts.."); 
        List<Company> allCompanies = db.getAllCompanies();       
        
        for (Company cp : allCompanies) 
        {
        	String log = "Id: "+ cp.getId()+" ,Image Id: " + cp.getImageId() + " ,Name: " + cp.getName();
                // Writing Contacts to log
        	Log.d("Name: ", log);
        }

	}

}
