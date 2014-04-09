package mx.pig.collector;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ContainerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.container, menu);
		return true;
	}

}
