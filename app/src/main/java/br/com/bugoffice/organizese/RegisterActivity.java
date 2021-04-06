package br.com.bugoffice.organizese;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.bugoffice.organizese.models.User;

public class RegisterActivity extends AppCompatActivity {

    private RadioGroup rbs;

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtConfSenha;

    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private RadioButton rbOutros;

    private CheckBox cbEntrar;

    private int    modo;
    private String nomeOriginal;
    private String emailOriginal;
    private String sexoOriginal;
    private String statusOriginal;

    public static final String MODO    = "MODO";
    public static final String NOME    = "NOME";
    public static final String EMAIL    = "EMAIL";
    public static final String SEXO    = "SEXO";
    public static final String STATUS    = "STATUS";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        rbs = findViewById(R.id.radioGroup);

        txtNome = findViewById(R.id.username2);
        txtEmail = findViewById(R.id.password);
        txtSenha = findViewById(R.id.password_register);
        txtConfSenha = findViewById(R.id.password_register_confir);

        rbMasculino = findViewById(R.id.rbMasculino);
        rbFeminino =  findViewById(R.id.rbFeminio);
        rbOutros = findViewById(R.id.rbOutros);
        cbEntrar = findViewById(R.id.checkBoxSalvar);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO){
                setTitle(getString(R.string.title_novo_registro));
            }else{
                nomeOriginal = bundle.getString(NOME);
                emailOriginal = bundle.getString(EMAIL);
                sexoOriginal = bundle.getString(SEXO);
                statusOriginal = bundle.getString(STATUS);

                txtNome.setText(nomeOriginal);
                txtEmail.setText(emailOriginal);

                rbMasculino.setChecked(sexoOriginal.equals("Masculino"));
                rbFeminino.setChecked(sexoOriginal.equals("Feminino"));
                rbOutros.setChecked(sexoOriginal.equals("Outros"));
                cbEntrar.setChecked(statusOriginal.equals("Logado"));

                setTitle(getString(R.string.title_editar_registro));
            }
        }
    }

    private void cancelar(){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    private void salvar(){

        String message = "";
        String nome = txtNome.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();
        String Senha = txtSenha.getText().toString().trim();
        String ConfirmSenha = txtConfSenha.getText().toString().trim();

        boolean logar = cbEntrar.isChecked();

        String sexo = "";

        if(rbMasculino.isChecked()){
            sexo = "Masculino";
        } else if (rbFeminino.isChecked()) {
            sexo = "Feminino";
        } else {
            sexo = "Outros";
        }

        if(nome.trim().isEmpty()) {
            message = "Campo nome não pode ser vazio!";
            txtNome.setFocusableInTouchMode(true);
            txtNome.requestFocus();
        } else if (Email.trim().isEmpty()) {
            message = "Campo email não pode ser vazio!";
            txtEmail.setFocusableInTouchMode(true);
            txtEmail.requestFocus();
        } else if (Senha.trim().isEmpty()) {
            message = "Campo senha não pode ser vazio!";
            txtSenha.setFocusableInTouchMode(true);
            txtSenha.requestFocus();
        } else if (ConfirmSenha.trim().isEmpty()) {
            message = "Campo confirma senha não pode ser vazio!";
            txtConfSenha.setFocusableInTouchMode(true);
            txtConfSenha.requestFocus();
        } else if (!rbMasculino.isChecked() && !rbFeminino.isChecked() && !rbOutros.isChecked()) {
            message = "Selecione um sexo!";
            rbOutros.setFocusableInTouchMode(true);
            rbOutros.requestFocus();
        }

        if(message.trim().isEmpty()) {
            message = "Salvo com sucesso!";
            // Put the String to pass back into an Intent and close this activity
            Intent intent = new Intent();

            intent.putExtra("nome", nome);
            intent.putExtra("email", Email);
            intent.putExtra("sexo", sexo);
            intent.putExtra("status", logar ? "Logado" : "Deslogado");

            setResult(RESULT_OK, intent);
            finish();
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbMasculino:
                if (checked) {
                    rbFeminino.setChecked(false);
                    rbOutros.setChecked(false);
                }
                    break;
            case R.id.rbFeminio:
                if (checked) {
                    rbMasculino.setChecked(false);
                    rbOutros.setChecked(false);
                }
                    break;
            case R.id.rbOutros:
                if (checked) {
                    rbMasculino.setChecked(false);
                    rbFeminino.setChecked(false);
                }
                    break;
        }
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registro_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                salvar();
                return true;

            case android.R.id.home:
            case R.id.menuItemCancelar:
                cancelar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}