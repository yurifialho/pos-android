package br.edu.fa7.projeto04;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	
	private ListView listView;
	//private String[] cidades;
	
	List<Cidade> cidades;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//ArrayAdapter<T>
		
		Cidade c1 = new Cidade();
			c1.setNome("Fortaleza");
			c1.setImagem(R.drawable.ic_launcher);
			
		Cidade c2 = new Cidade();
			c2.setNome("Natal");
			c2.setImagem(R.drawable.ic_launcher);
			
		Cidade c3 = new Cidade();
			c3.setNome("Recife");
			c3.setImagem(R.drawable.ic_launcher);
		
		cidades = new ArrayList<Cidade>();
			cidades.add(c1);
			cidades.add(c2);
			cidades.add(c3);
		
		CustomAdapter adapter = new CustomAdapter(this, cidades);	
			
		listView = (ListView) findViewById(R.id.main_listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Toast.makeText(this, "Voce clicou na cidade " + cidades.get(position).getNome(), Toast.LENGTH_SHORT).show();
	}

}




