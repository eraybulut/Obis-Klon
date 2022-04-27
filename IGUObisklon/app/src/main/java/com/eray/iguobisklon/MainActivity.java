package com.eray.iguobisklon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textView;
    private TextInputLayout textInput,textInputSifre;
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String gelenAd;
    private String gelenSifre;
    private String ad;
    private String sifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        textView=findViewById(R.id.textView);
        textInput=findViewById(R.id.textınputAd);
        button=findViewById(R.id.button);
        textInputSifre=findViewById(R.id.textInputSıfre);

        sharedPreferences=getSharedPreferences("Giris",MODE_PRIVATE);
        editor=sharedPreferences.edit();





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gelenAd=textInput.getEditText().getText().toString();
                gelenSifre=textInputSifre.getEditText().getText().toString();

                editor.putString("ad",gelenAd);
                editor.putString("sıfre",gelenSifre);

                if(gelenAd.equals("eray")&&gelenSifre.equals("123")){
                    Intent intent=new Intent(MainActivity.this,ObisActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Hatalı Kullancı adı veya Sifre",Toast.LENGTH_LONG).show();
                }



            }
        });


        toolbar.setTitle("   Obis Giris Ekranı");
        toolbar.setLogo(R.drawable.bina);
        setSupportActionBar(toolbar);

    }
    public void yardim(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Yardım");
        alert.setIcon(R.drawable.help);
        alert.setMessage("200111028@ogr.gelisim.edu.tr mail atin");
        alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.manin_en:
                Toast.makeText(MainActivity.this,"Language set to English",Toast.LENGTH_SHORT).show();
                textInput.setHelperText("Requisite*");
                textInput.setHint("Enter Name");
                textView.setText("Help?");
                textInputSifre.setHelperText("Requisite*");
                textInputSifre.setHint("Enter Password");
                button.setText("Log in");
                toolbar.setTitle("  Obis Login");
                break;
            case R.id.main_tr:
                Toast.makeText(MainActivity.this,"Dil Türkçe olarak ayarlandı",Toast.LENGTH_SHORT).show();
                textInput.setHelperText("Zorunlu*");
                textInput.setHint("Adınızı Girin");
                textView.setText("Yardım?");
                textInputSifre.setHelperText("Zorunlu*");
                textInputSifre.setHint("Sifrenizi Girin");
                button.setText("Giriş Yap");
                toolbar.setTitle("  Obis Giris");
                break;


        }


        return super.onOptionsItemSelected(item);
    }
}