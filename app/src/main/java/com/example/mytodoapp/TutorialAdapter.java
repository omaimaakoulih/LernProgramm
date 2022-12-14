package com.example.mytodoapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TutorialAdapter extends ArrayAdapter<Tutorial> {

    public TutorialAdapter(Context context, ArrayList<Tutorial> tutorials) {
        super(context, 0, tutorials);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tutorial tuto = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.object_user_home,parent,false);
        }

        ImageView logoImage = (ImageView) convertView.findViewById(R.id.logoimage);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        Button viewMore =(Button) convertView.findViewById(R.id.viewmore);


        logoImage.setImageResource(tuto.getLogoImage());
        title.setText(tuto.getTitle());
        description.setText(tuto.getDescription());

        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                UserApp user =(UserApp) ((Activity) view.getContext()).getIntent().getSerializableExtra("user");


                if(tuto.getTitle().equals("Photoshop")) {
                    intent = new Intent(view.getContext(), PsViewMore.class);
                }
                else if(tuto.getTitle().equals("Lightroom")){
                    intent = new Intent(view.getContext(), LrViewMore.class);
                }
                else if(tuto.getTitle().equals("Premiere Pro")){
                    intent = new Intent(view.getContext(), PrViewMore.class);
                }
                else if(tuto.getTitle().equals("Adobe Stock")){
                    intent = new Intent(view.getContext(), StViewMore.class);
                }
                else{
                    intent = new Intent(view.getContext(), IlluViewMore.class);
                }


                intent.putExtra("user",user);
                view.getContext().startActivity(intent);

            }
        });

        return convertView;

    }
}


