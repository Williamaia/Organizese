package br.com.bugoffice.organizese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText txtNome = (EditText) findViewById(R.id.username2);
        final EditText txtEmail = (EditText) findViewById(R.id.password);
        final EditText txtSenha = (EditText) findViewById(R.id.password_register);
        final EditText txtConfSenha = (EditText) findViewById(R.id.password_register_confir);

        final RadioButton rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        final RadioButton rbFeminino = (RadioButton) findViewById(R.id.rbFeminio);
        final RadioButton rbOutros = (RadioButton) findViewById(R.id.rbOutros);

        final Button btnLimpar = (Button) findViewById(R.id.limpar);
        final Button btnSalvar = (Button) findViewById(R.id.salvar);

        final CheckBox cbEntrar = (CheckBox) findViewById(R.id.checkBoxSalvar);

        /*public void clearRadioChecked(){
            rbMasculino.setChecked(false);
            rbFeminino.setChecked(false);
            rbOutros.setChecked(false);
        }*/

        Toast.makeText(getApplicationContext(),"Botão limpar pressionado!", Toast.LENGTH_LONG).show();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Botão limpar pressionado!", Toast.LENGTH_LONG).show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Botão salvar pressionado!", Toast.LENGTH_LONG).show();
            }
        });

        rbMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFeminino.setChecked(false);
                rbOutros.setChecked(false);
            }
        });

        rbFeminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMasculino.setChecked(false);
                rbOutros.setChecked(false);
            }
        });

        rbOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMasculino.setChecked(false);
                rbFeminino.setChecked(false);
            }
        });


    }
}