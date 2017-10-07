package com.example.maximiliano.prac09_llamada;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txnumPhone;
    ImageButton btnllamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txnumPhone=(EditText)findViewById(R.id.tx_numPhone);
        btnllamar=(ImageButton)findViewById(R.id.btn_llamar);
        //click boton llamar
        btnllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamar();
            }
        });
    }

    private void llamar() {
        if (txnumPhone.getText().toString().isEmpty()){
            Toast.makeText(this,"no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else {
            String phone=txnumPhone.getText().toString();
            //comprobar version del sistema
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            }else{
                olderVersion(phone);
            }
        }
    }

    private void olderVersion(String phone) {
        Intent intentImplicito=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
        if(checarPermisos(Manifest.permission.CALL_PHONE)){
            startActivity(intentImplicito);
        }else {
            Toast.makeText(this,"no hay permisos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checarPermisos(String permiso) {
        int result=this.checkCallingOrSelfPermission(permiso);
        return result== PackageManager.PERMISSION_GRANTED;
    }
}
