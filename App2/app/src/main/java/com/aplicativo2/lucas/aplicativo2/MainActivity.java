package com.aplicativo2.lucas.aplicativo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button mainApp;
    private Button othersApps;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainApp = (Button)findViewById(R.id.sendMsgId);
        othersApps = (Button)findViewById(R.id.sendMsgsId);

        mainApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Message();
            }
        });

        othersApps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("Apps");
                startActivity(intent);
            }
        });
    }

    private void Message(){
        intent = new Intent("Apps");
        intent.addCategory("Apps");
        intent.putExtra("msg","A");

        if(intent.resolveActivity(getPackageManager()) != null){
            AlertMessage("Aplicativo principal");
        }else{
            Toast.makeText(getApplicationContext(),"Você não tem o aplicativo instalado", Toast.LENGTH_SHORT).show();
        }
    }

    private void AlertMessage (String app){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("O " + app + " será inciado")
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
}
