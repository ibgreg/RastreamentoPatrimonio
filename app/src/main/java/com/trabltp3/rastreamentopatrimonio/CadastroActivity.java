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

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistro;
    private EditText txtEmail, txtSenha;
    private TextView txtLogin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Crie uma nova conta");
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
        progressDialog = new ProgressDialog(this);

        btnRegistro.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegistro){
            registrarUsuario();
        }

        if(v == txtLogin){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void registrarUsuario(){
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //exibir banner de alerta na parte inferior
            Toast.makeText(this, "Insira o seu e-mail", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(senha)){
            //exibir banner de alerta na parte inferior
            Toast.makeText(this, "Digite uma senha válida", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registrando usuário");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, senha) //cria um novo usuário com o email e senha inseridos
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //ação que será feita ao autenticar com sucesso
                            Toast.makeText(CadastroActivity.this, "Usuário criado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        }
                        else{
                            Toast.makeText(CadastroActivity.this, "Erro ao registrar",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
