package com.trabltp3.rastreamentopatrimonio;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ítalo on 03/05/2017.
 */

public class ListaPatrimonio extends ArrayAdapter<InfoPatrimonio>{

    private Activity context;
    private List<InfoPatrimonio> listaPatrimonio;

    public ListaPatrimonio(Activity context, List<InfoPatrimonio> listaPatrimonio){
        super(context, R.layout.list_layout, listaPatrimonio);
        this.context = context;
        this.listaPatrimonio = listaPatrimonio;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewQuantidade = (TextView) listViewItem.findViewById(R.id.textViewQuantidade);
        TextView textViewDescricao = (TextView) listViewItem.findViewById(R.id.textViewDescrição);
        TextView textViewLocal = (TextView) listViewItem.findViewById(R.id.textViewLocal);
        TextView textViewQualidade = (TextView) listViewItem.findViewById(R.id.textViewQualidade);
        TextView textViewCusto = (TextView) listViewItem.findViewById(R.id.textViewCusto);

        InfoPatrimonio info = listaPatrimonio.get(position);

        textViewQuantidade.setText(info.getQuantidade());
        textViewDescricao.setText(info.getDescricao());
        textViewLocal.setText(info.getLocalizacao());
        textViewQualidade.setText(info.getQualidade());
        textViewCusto.setText(info.getCusto());

        return listViewItem;
    }
}
