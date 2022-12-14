package com.example.mytodoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserBagAddapter extends ArrayAdapter<Tutorial> {

    public UserBagAddapter(Context context, ArrayList<Tutorial> tutorials) {
        super(context, 0, tutorials);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tutorial tuto = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.object_user_bag,parent,false);
        }

        ImageView logoImage = (ImageView) convertView.findViewById(R.id.tutologoimage);
        TextView title = (TextView) convertView.findViewById(R.id.tutotitle);
        TextView description = (TextView) convertView.findViewById(R.id.tutodescription);
        ImageView deleteBtn = (ImageView) convertView.findViewById(R.id.deleteTuto) ;


        logoImage.setImageResource(tuto.getLogoImage());
        title.setText(tuto.getTitle());
        description.setText(tuto.getDescription());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the user :
                UserApp user =(UserApp) ((Activity) view.getContext()).getIntent().getSerializableExtra("user");

                MyDataBaseHelper dbHelper = new MyDataBaseHelper(view.getContext());

                dbHelper.deleteUserTutorial(user.getId(),tuto);
                Toast.makeText(view.getContext(), "Tutorial deleted successfully", Toast.LENGTH_SHORT).show();

                ((Activity)view.getContext()).getIntent().putExtra("user",user);
                ((Activity) view.getContext()).overridePendingTransition(0,0);
                view.getContext().startActivity(((Activity) view.getContext()).getIntent());
                ((Activity) view.getContext()).overridePendingTransition(0,0);


            }
        });

        return convertView;

    }
}