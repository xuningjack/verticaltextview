package com.example.android_verticaltextview;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

	private VerticalTextView timeFileterTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timeFileterTextView = (VerticalTextView)findViewById(R.id.timeFileterTextView);
		StringBuilder sBuilder = new StringBuilder();
		//加空格垂直方向的间隙
		sBuilder.append("2011  ");
		sBuilder.append("2012  ");
		sBuilder.append("2013  ");
		sBuilder.append("2014  ");
		timeFileterTextView.setText(sBuilder.toString());
	}
}
