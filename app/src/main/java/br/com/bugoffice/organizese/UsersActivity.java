package br.com.bugoffice.organizese;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import br.com.bugoffice.organizese.adapter.UsersAdapter;
import br.com.bugoffice.organizese.models.User;

public class UsersActivity extends AppCompatActivity {

    private ListView listview;
    private ConstraintLayout results;

    private static ArrayList<User> usersList = new ArrayList();
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static UsersAdapter adapter;
    private View       viewSelecionada;
    private ActionMode actionMode;
    private int        posicaoSelecionada = -1;


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.menu_item, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch(item.getItemId()){
                case R.id.menuItemAlterar:
                    alterarRegistro();
                    mode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    excluirRegistro();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode         = null;
            viewSelecionada    = null;

            listview.setEnabled(true);
        }
    };

    @Override
    protected void  onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_users);

        listview = findViewById(android.R.id.list);
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        posicaoSelecionada = position;
                        alterarRegistro();
                    }
                });

        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null){
                    return false;
                }

                posicaoSelecionada = position;
                view.setBackgroundColor(Color.LTGRAY);
                viewSelecionada = view;
                listview.setEnabled(false);
                actionMode = startActionMode(mActionModeCallback);

                return true;
            }
        });

        popularLista();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(usersList == null){
            usersList = new ArrayList<>();
        }

        if (adapter == null){
            adapter = new UsersAdapter(this, usersList);
            listview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private void popularLista(){
        adapter = new UsersAdapter(this, usersList);
        listview.setAdapter(adapter);
    }

    private void alterarRegistro(){

        User user = usersList.get(posicaoSelecionada);
        Intent intent = new Intent(UsersActivity.this, RegisterActivity.class);

        intent.putExtra(RegisterActivity.MODO, RegisterActivity.ALTERAR);
        intent.putExtra(RegisterActivity.NOME, user.getNome());
        intent.putExtra(RegisterActivity.EMAIL, user.getEmail());
        intent.putExtra(RegisterActivity.SEXO, user.getSexo());
        intent.putExtra(RegisterActivity.STATUS, user.getStatus());

        startActivityForResult(intent, RegisterActivity.ALTERAR);
    }

    private void novoRegistro(){
        Intent intent = new Intent(UsersActivity.this, RegisterActivity.class);
        intent.putExtra(RegisterActivity.MODO, RegisterActivity.NOVO);
        startActivityForResult(intent, RegisterActivity.NOVO);
    }


    private void excluirRegistro(){
        usersList.remove(posicaoSelecionada);
        adapter.notifyDataSetChanged();
    }

    private void telaSobre(){
        Intent intent = new Intent(UsersActivity.this, AutoriaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Get String data from Intent
            String nome = data.getStringExtra("nome");
            String email = data.getStringExtra("email");
            String sexo = data.getStringExtra("sexo");
            String status = data.getStringExtra("status");
            User user = new User(nome, email, sexo, status);

            if (requestCode == RegisterActivity.ALTERAR){
                usersList.set(posicaoSelecionada, user);
                posicaoSelecionada = -1;
            }else{
                usersList.add(user);
            }
        }

        // use your custom layout
        adapter.notifyDataSetChanged();
    }


//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        User item = (User) getListAdapter().getItem(position);
//        Toast.makeText(this, item.getNome() + " clicado!!!", Toast.LENGTH_LONG).show();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemAdicionar:
                novoRegistro();
                return true;

            case R.id.menuItemSobre:
                telaSobre();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}