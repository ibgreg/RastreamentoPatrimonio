package com.trabltp3.rastreamentopatrimonio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ítalo on 29/04/2017.
 */

public class TelaPrincipal extends Fragment{

    private DatabaseReference databasePatrimonio;
    private FirebaseAuth firebaseAuth;

    ListView listViewPatrimonios;
    List<InfoPatrimonio> listaPatrimonio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.telaprincipal, container, false);

        firebaseAuth = firebaseAuth.getInstance();
        FirebaseUser usuario = firebaseAuth.getCurrentUser();
        databasePatrimonio = FirebaseDatabase.getInstance().getReference(usuario.getUid());

        listViewPatrimonios = (ListView) view.findViewById(R.id.listViewPatrimonios);
        listaPatrimonio = new ArrayList<>();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Tela principal");


    }

    @Override
    public void onStart() {
        super.onStart();

        databasePatrimonio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //será executado ao alterar uma entrada armazenada no BD

                listaPatrimonio.clear();
                for (DataSnapshot patrimonioSnapshot : dataSnapshot.getChildren()){
                    InfoPatrimonio info = patrimonioSnapshot.getValue(InfoPatrimonio.class);

                    listaPatrimonio.add(info);
                }

                ListaPatrimonio adapter = new ListaPatrimonio(getActivity(), listaPatrimonio);
                listViewPatrimonios.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //executado quando acontecer algum erro ao obter os dados

            }
        });
    }
}
