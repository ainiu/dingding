package com.fanc.mycar.view;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.fanc.mycar.R;

public class XiangQingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiang_qing);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xiang_qing, menu);
		return true;
	}

}
