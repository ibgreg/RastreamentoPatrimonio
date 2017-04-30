package com.trabltp3.rastreamentopatrimonio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEntrar;
    private EditText txtEmail, txtSenha;
    private TextView txtCadastro;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Entrar");
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //verfica se tem algum usuário logado e inicia a tela principal
            finish();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        progressDialog = new ProgressDialog(this);

        btnEntrar.setOnClickListener(this);
        txtCadastro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnEntrar){
            fazerLogin();
        }

        if (v ==txtCadastro){
            finish();
            startActivity(new Intent(this, CadastroActivity.class));
        }
    }

    public void fazerLogin(){
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Insira o seu e-mail", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(senha)){
            Toast.makeText(this, "Digite a sua senha", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Entrando...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //inicia a interface principal de cadastro
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        }
                    }
                });
    }
}
