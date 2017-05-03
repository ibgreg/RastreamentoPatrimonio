package com.trabltp3.rastreamentopatrimonio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ítalo on 29/04/2017.
 */

public class TelaAdicionar extends Fragment implements View.OnClickListener{

    private DatabaseReference databasePatrimonio;
    private FirebaseAuth firebaseAuth;
    private EditText editQualidadeAdd, editDescricaoAdd, editLocalizacaoAdd, editQuantidadeAdd, editCustoAdd;
    private Button btAdicionar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.telaadicionar, container, false);

        editQuantidadeAdd = (EditText) view.findViewById(R.id.editQuantidadeAdd);
        editDescricaoAdd = (EditText) view.findViewById(R.id.editDescricaoAdd);
        editLocalizacaoAdd = (EditText) view.findViewById(R.id.editLocalizacaoAdd);
        editQualidadeAdd = (EditText) view.findViewById(R.id.editQualidadeAdd);
        editCustoAdd = (EditText) view.findViewById(R.id.editCustoAdd);
        btAdicionar = (Button) view.findViewById(R.id.btAdicionar);

        firebaseAuth = firebaseAuth.getInstance();
        FirebaseUser usuario =  firebaseAuth.getCurrentUser();
        databasePatrimonio = FirebaseDatabase.getInstance().getReference(usuario.getUid());


        btAdicionar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Tela 1");


    }

    private void salvarDados(){
        String quantidade = editQuantidadeAdd.getText().toString().trim();
        String descricao = editDescricaoAdd.getText().toString().trim();
        String localizacao = editLocalizacaoAdd.getText().toString().toString();
        String qualidade = editQualidadeAdd.getText().toString().trim();
        String custo = editCustoAdd.getText().toString();

        if(TextUtils.isEmpty(quantidade)){
            Toast.makeText(getActivity(), "O campo Quantidade está em branco", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(descricao)){
            Toast.makeText(getActivity(), "O campo Descrição está em branco", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(localizacao)){
            Toast.makeText(getActivity(), "O campo Localização está em branco", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(qualidade)){
            Toast.makeText(getActivity(), "O campo Qualidade está em branco", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(custo)){
            Toast.makeText(getActivity(), "O campo Custo está em branco", Toast.LENGTH_SHORT).show();
        }
        else{
            String id = databasePatrimonio.push().getKey();

            InfoPatrimonio info = new InfoPatrimonio(id, quantidade, descricao, localizacao, qualidade, custo);
            databasePatrimonio.child(id).setValue(info);
            Toast.makeText(getActivity(), "Dados salvos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btAdicionar){
            salvarDados();
        }
    }
}