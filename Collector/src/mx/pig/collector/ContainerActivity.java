package mx.pig.collector;

import mx.pig.database.PIGDatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContainerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		PIGDatabaseHelper.getInstance(this).initializeTables();
		Button buttonPhotoLauncher=(Button)findViewById(R.id.buttonPhotoActivity);
		buttonPhotoLauncher.setOnClickListener(clickListenerPhotoLauncher);
	}

	OnClickListener clickListenerPhotoLauncher= new OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			Intent intent= new Intent(getApplicationContext() ,PhotoActivity.class);
			startActivity(intent);
			
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.container, menu);		
		return true;
	}

}
