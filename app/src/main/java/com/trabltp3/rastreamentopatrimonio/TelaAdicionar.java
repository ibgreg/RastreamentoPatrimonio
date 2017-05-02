package com.trabltp3.rastreamentopatrimonio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private DatabaseReference databaseReference;
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
        databaseReference = FirebaseDatabase.getInstance().getReference();
       //    FirebaseUser usuario = firebaseAuth.getCurrentUser();


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

        InfoUsuario infoUsuario = new InfoUsuario(quantidade, descricao, localizacao, qualidade, custo);

        FirebaseUser usuario =  firebaseAuth.getCurrentUser();
        databaseReference.child(usuario.getUid()).setValue(infoUsuario);

        Toast.makeText(getActivity(), "Dados salvos", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        if (v == btAdicionar){
            salvarDados();
        }
    }
}