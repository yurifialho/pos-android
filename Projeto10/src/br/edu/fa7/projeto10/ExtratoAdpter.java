package br.edu.fa7.projeto10;

import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExtratoAdpter extends BaseAdapter{
	
	private Context ctx;
	private List<Lancamento> lancamentos;
	
	public ExtratoAdpter(Context ctx, List<Lancamento> lancamentos) {
		this.ctx = ctx;
		this.lancamentos = lancamentos;
	}
	
	@Override
	public int getCount() {
		return this.lancamentos.size();
	}

	@Override
	public Object getItem(int pos) {
		return this.lancamentos.get(pos);
	}

	@Override
	public long getItemId(int id) {
		return this.lancamentos.indexOf(this.lancamentos.get(id));
	}

	@Override
	public View getView(int pos, View convert, ViewGroup parent) {
		Lancamento lancamento = this.lancamentos.get(pos);
		TempObj obj = null;
		if(convert == null) {
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convert = inflater.inflate(R.layout.extrato_item, null);
			obj = new TempObj();
			
			obj.txLancamento = (TextView) convert.findViewById(R.id.txtListLancamento);
			obj.txLancamento.setText("");
			
			obj.txDescricao = (TextView) convert.findViewById(R.id.txtDescricao);
			obj.txDescricao.setText("");
			
			convert.setTag(obj);
		} else {
			obj =  (TempObj) convert.getTag();
		}
		
		obj.txDescricao.setText(lancamento.getTipoLancamento());
		obj.txLancamento.setText(lancamento.getValue().toString());
		if(lancamento.getIsReceita()) {
			obj.txLancamento.setTextColor(Color.GREEN);
		} else {
			obj.txLancamento.setTextColor(Color.RED);
		}
		
		return convert;
	}
	
	static class TempObj {
		TextView txLancamento;
		TextView txDescricao;
	}

}
