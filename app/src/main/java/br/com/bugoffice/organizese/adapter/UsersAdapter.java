package br.com.bugoffice.organizese.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.bugoffice.organizese.R;
import br.com.bugoffice.organizese.models.Financas;
import br.com.bugoffice.organizese.models.User;

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlayout, parent, false);
        }

        // Lookup view for data population
        TextView tNome = convertView.findViewById(R.id.nome);
        TextView tvEmail = convertView.findViewById(R.id.email);
        TextView tvSexo = convertView.findViewById(R.id.sexo);
        TextView tvStatus = convertView.findViewById(R.id.status);

        // Populate the data into the template view using the data object
        tNome.setText(user.getNome());
        tvEmail.setText(user.getEmail());
        tvSexo.setText(user.getSexo());
        tvStatus.setText(user.getStatus());

        // Return the completed view to render on screen
        return convertView;

    }

}
