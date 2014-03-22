package br.edu.fa7.projeto04;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	
	private Context context;
	private List<Cidade> cidades;
	
	public CustomAdapter(Context context, List<Cidade> cidades) {
		this.context = context;
		this.cidades = cidades;
	}
	
	@Override
	public int getCount() {
		return this.cidades.size();
	}

	@Override
	public Object getItem(int pos) {
		return this.cidades.get(pos);
	}

	@Override
	public long getItemId(int id) {
		return this.cidades.indexOf(this.cidades.get(id));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Cidade c = this.cidades.get(position);
		HolderView holder = null;
		
		if(convertView == null) {
			holder = new HolderView();
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.linear_layout, null);
			
			
			holder.tvCidade = (TextView) convertView.findViewById(R.id.list_tvCidade);
			holder.ivCidade = (ImageView) convertView.findViewById(R.id.list_ivCidade);
			
			convertView.setTag(holder);
		} else {
			holder = (HolderView) convertView.getTag();
		}
		
		holder.tvCidade.setText(c.getNome());
		holder.ivCidade.setImageResource(c.getImagem());
		
		return convertView;
	}

	static class HolderView {
		TextView tvCidade;
		ImageView ivCidade;
	}
 }
