package br.edu.fa7.projetofinal;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaComprasAdapter extends BaseAdapter{
	
	private Context contexto;
	private List<Produto> produtos;
	
	public ListaComprasAdapter(Context contexto, List<Produto> produtos) {
		this.contexto = contexto;
		this.produtos = produtos;
	}
	
	@Override
	public int getCount() {
		return this.produtos.size();
	}

	@Override
	public Object getItem(int position) {
		return this.produtos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.produtos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Produto produto = this.produtos.get(position);
		ProdutoTemp pTemp = null;
		
		if(convertView ==  null) {
			LayoutInflater inflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.activity_item_lista_compras, null);
			pTemp = new ProdutoTemp();
			
			pTemp.imgIcon = (ImageView) convertView.findViewById(R.id.imgLogo);
			pTemp.imgIcon.setImageResource(R.drawable.ic_launcher);
			
			pTemp.txDescricao = (TextView) convertView.findViewById(R.id.lbItem);
			pTemp.txDescricao.setText("");
			
			pTemp.txQuantidade = (TextView) convertView.findViewById(R.id.txQuantidade);
			pTemp.txQuantidade.setText("  Qtd.: 0  ");
			
			pTemp.txtValorUnitario = (TextView) convertView.findViewById(R.id.txVlrUnitario);
			pTemp.txtValorUnitario.setText("  VU.: R$: 00  ");
			
			pTemp.txtValorTotal = (TextView) convertView.findViewById(R.id.txVlrTotal);
			pTemp.txtValorTotal.setText("  VT.: R$: 00  ");
			
			convertView.setTag(pTemp);
		} else {
			pTemp = (ProdutoTemp) convertView.getTag();
		}
		
		pTemp.txDescricao.setText(getDescricao(produto.getTipo()));
		pTemp.txQuantidade.setText("  Qtd.: "+produto.getQuantidade()+"  ");
		pTemp.txtValorUnitario.setText("  VU.: R$: "+produto.getValorUnitario()+"  ");
		pTemp.txtValorTotal.setText("  VT.: R$: "+(produto.getQuantidade() * produto.getValorUnitario())+"  ");
		
		return convertView;
	}
	
	
	private String getDescricao(Integer tipo) {
		switch (tipo) {
		case 0:
			return "Mel‹o";
		case 1:
			return "Morango";
		case 2: 
			return "Laranja";
		}
		return "";
	}


	static class ProdutoTemp {
		ImageView imgIcon;
		TextView txDescricao;
		TextView txQuantidade;
		TextView txtValorUnitario;
		TextView txtValorTotal;
	}
}
