package com.example.projectnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Edit extends AppCompatActivity{
EditText title;
EditText description;
ImageView img;
ImageView img2;
long Nid;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit);
            Intent intent = getIntent();
  title=(EditText)findViewById(R.id.editText);
  description=(EditText)findViewById(R.id.type);
  img=(ImageView) findViewById(R.id.imageView);
    img2=(ImageView)findViewById(R.id.img);
            Nid = intent.getLongExtra("ID",0);
           Database db = new Database(this);
            Modelclass note = db.getNote(Nid);
            final String edittitle = note.getTitle();
            String editcontent = note.getDesc();
            title.setText(edittitle);
            description.setText(editcontent);
            img.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
         Database db = new Database(getApplicationContext());
          db.deleteNote(Nid);
          Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
          Intent intent=new Intent(Edit.this,NotesList.class);
          startActivity(intent);
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
   Modelclass     note = new Modelclass(Nid, title.getText().toString(), description.getText().toString());
        Database sDB = new Database(getApplicationContext());
        long id = sDB.editNote(note);

        Intent intent=new Intent(Edit.this,NotesList.class);
        startActivity(intent);

    }

}