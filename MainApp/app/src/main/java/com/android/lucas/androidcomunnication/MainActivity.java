package com.android.lucas.androidcomunnication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final String PREFS_KEY = "Preferences";
    private SharedPreferences sharedpreferences;
    private Button sendMsg1;
    private Button sendMsg2;
    private Button sendMsg3;
    private Button sendMsg4;
    private TextView N;
    private TextView A;
    private TextView V;
    private TextView E;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMsg1 = (Button)findViewById(R.id.btnSendMsg1Id);
        sendMsg2 = (Button)findViewById(R.id.btnSendMsg2Id);
        sendMsg3 = (Button)findViewById(R.id.btnSendMsg3Id);
        sendMsg4 = (Button)findViewById(R.id.btnSendMsg4Id);
        N = (TextView)findViewById(R.id.textViewNId);
        A = (TextView)findViewById(R.id.textViewAId);
        V = (TextView)findViewById(R.id.textViewVId);
        E = (TextView)findViewById(R.id.textViewEId);

        sharedpreferences = getSharedPreferences(PREFS_KEY,0);

        Boolean bN =  sharedpreferences.getBoolean("N",false);
        Boolean bA =  sharedpreferences.getBoolean("A",false);
        Boolean bV =  sharedpreferences.getBoolean("V",false);
        Boolean bE =  sharedpreferences.getBoolean("E",false);

        if(bN){
            N.setVisibility(View.VISIBLE);
        }
        if(bA){
            A.setVisibility(View.VISIBLE);
        }
        if(bV){
            V.setVisibility(View.VISIBLE);
        }
        if(bE){
            E.setVisibility(View.VISIBLE);
        }

        Intent i = getIntent();

        if(i != null){
            Bundle extra = i.getExtras();
            if(extra != null){
                String app = extra.getString("msg");
                if(app != null){
                    ViewText(app);
                }
            }
        }

        sendMsg1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Message("App1","Aplicativo 1", "1");
            }
        });

        sendMsg2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Message("App2","Aplicativo 2","2");
            }
        });

        sendMsg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message("App3","Aplicativo 3","3");
            }
        });

        sendMsg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message("App4","Aplicativo 4","4");
            }
        });
    }

    private void Message(String category, String message, String app){
        intent = new Intent("Apps");
        intent.addCategory(category);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        if(intent.resolveActivity(getPackageManager()) != null){
            AlertMessage(app);
        }else{
            Toast.makeText(getApplicationContext(),"Você não tem o aplicativo " + app +  " instalado",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void AlertMessage (String app){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("O aplicativo " + app + " será inciado")
                .setTitle("Atenção");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void ViewText(String letter){
        SharedPreferences.Editor editor = sharedpreferences.edit();

        switch (letter){
            case"N":
                N.setVisibility(View.VISIBLE);
                editor.putBoolean("N",true);
                break;
            case"A":
                A.setVisibility(View.VISIBLE);
                editor.putBoolean("A",true);
                break;
            case"V":
                V.setVisibility(View.VISIBLE);
                editor.putBoolean("V",true);
                break;
            case"E":
                E.setVisibility(View.VISIBLE);
                editor.putBoolean("E",true);
                break;
        }
        editor.apply();
        Toast.makeText(this,"Letra inserida com sucesso",Toast.LENGTH_LONG).show();
    }
}

