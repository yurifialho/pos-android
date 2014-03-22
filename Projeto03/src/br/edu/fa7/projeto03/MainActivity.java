package br.edu.fa7.projeto03;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	private CheckBox a;
	private CheckBox b;
	private CheckBox c;
	private RadioButton aa;
	private RadioButton bb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_layout);

		a = (CheckBox) findViewById(R.id.cb01);
		b = (CheckBox) findViewById(R.id.cb02);
		c = (CheckBox) findViewById(R.id.cb03);

		a.setOnClickListener(this);
		b.setOnClickListener(this);
		c.setOnClickListener(this);
		
		aa = (RadioButton) findViewById(R.id.rb01);
		bb = (RadioButton) findViewById(R.id.rb02);
		
		aa.setOnCheckedChangeListener(this);
		bb.setOnCheckedChangeListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cb01:
			if (a.isChecked()) {
				Toast.makeText(this, "A selecionado", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.cb02:
			if (b.isChecked()) {
				Toast.makeText(this, "B selecionado", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.cb03:
			if (c.isChecked()) {
				Toast.makeText(this, "C selecionado", Toast.LENGTH_SHORT).show();
			}
			break;
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean status) {
		if (status) {
			Toast.makeText(this, "RadioButton " + buttonView.getText() + " selecionado", Toast.LENGTH_SHORT).show();
		}
	}

}
