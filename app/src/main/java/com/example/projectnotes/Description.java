package com.example.projectnotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Description extends AppCompatActivity {
int id;
    EditText title1;
    EditText description;
    ImageView img;
ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        title1=(EditText)findViewById(R.id.editText);
        description=(EditText)findViewById(R.id.type);
        img=(ImageView)findViewById(R.id.imageView);
img2=(ImageView)findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Database db = new Database(getApplicationContext());
                db.deleteNote(id);
                Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Description.this,NotesList.class);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=description.getText().toString();
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_SEND);
                intent.setType("type/text");
                intent.putExtra(Intent.EXTRA_TEXT,data);
                startActivity(intent.createChooser(intent,"share"));
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Modelclass note = new Modelclass(title1.getText().toString(), description.getText().toString());
        Database sDB = new Database(this);
        long id = sDB.addNote(note);
        Modelclass check = sDB.getNote(id);
        Log.d("inserted", "Note: " + id + " -> Title:" + check.getTitle());

        Intent i = new Intent(Description.this,NotesList.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
    private int getRandomColor(){
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.blue);
        colorcode.add(R.color.orange);
        colorcode.add(R.color.purple);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.violet);
        colorcode.add(R.color.yellow);
        colorcode.add(R.color.darkyellow);
        colorcode.add(R.color.green);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.pink);

        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);
    }

}
