package com.example.projectnotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Updateactivity extends AppCompatActivity {
long id;
    TextView title1;
    TextView description;
    ImageView img1;
    ImageView img2;
GestureDetector gestureDetector;
Modelclass note;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);
        Intent i = getIntent();
        title1 = (TextView) findViewById(R.id.textView);
        description = (TextView) findViewById(R.id.type);
        img1 = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.img);
        id =  i.getLongExtra("ID",0);
        Database db = new Database(this);
        Modelclass note = db.getNote(id);
        title1.setText(note.getTitle());
        description.setText(note.getDesc());
        description.setMovementMethod(new ScrollingMovementMethod());
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Updateactivity.this,Edit.class);
                i.putExtra("ID",id);
                startActivity(i);
            }
        });
    description.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Updateactivity.this,Edit.class);
            i.putExtra("ID",id);
            startActivity(i);
        }
    });
     img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext());
                db.deleteNote(id);
                Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Updateactivity.this,NotesList.class);
                startActivity(i);
            }
        });

img2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String data=description.getText().toString();
        Intent intent=new Intent();
        intent.setAction(intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,data);
        startActivity(intent.createChooser(intent,"share"));
    }
});

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Updateactivity.this, NotesList.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}