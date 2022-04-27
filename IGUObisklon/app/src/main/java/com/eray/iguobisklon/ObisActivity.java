package com.eray.iguobisklon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ObisActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private  BottomNavigationView bottomNav;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String ad;
    private String sifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obis);



        toolbar=findViewById(R.id.toolbar2);
        bottomNav=findViewById(R.id.bottomNav);
        navigationView=findViewById(R.id.navigaitonView);
        drawer=findViewById(R.id.drawer);

        sharedPreferences=getSharedPreferences("Giris",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        ad=sharedPreferences.getString("ad","İsim yok");
        sifre=sharedPreferences.getString("sifre","Sifre Yok");


NavHostFragment navHostFragment=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.navHost);
NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());

NavigationUI.setupWithNavController(navigationView,navHostFragment.getNavController());


        toolbar.setTitle("  OBİS");
        toolbar.setLogo(R.drawable.bina);
        setSupportActionBar(toolbar);



        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(ObisActivity.this,drawer,toolbar,0,0);
        drawer.setDrawerListener(toogle);
        toogle.syncState();
        View baslık =navigationView.inflateHeaderView(R.layout.custom_drawer);
/*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profilFragment:
                        Navigation.findNavController(baslık).navigate(R.id.profilFragment);
                }
                return false;
            }
        });
*/







    }

    @Override
    public void onBackPressed() {//drawer kapatma
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
//Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.toolbar_yardim:
                AlertDialog.Builder alert2=new AlertDialog.Builder(ObisActivity.this);
                alert2.setTitle("Yardım");
                alert2.setIcon(R.drawable.help);
                alert2.setMessage("200111028@ogr.gelisim.edu.tr Mail atınız");
                alert2.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert2.create().show();
                break;
            case R.id.toollBar_bilgi:
                Toast.makeText(ObisActivity.this,"Henuz Bir Bilgilendirme Yok",Toast.LENGTH_LONG).show();
                break;

            case R.id.toolbar_yenile:
                Toast.makeText(ObisActivity.this,"Yenilendi",Toast.LENGTH_LONG).show();
                break;
            case R.id.toolbar_cıkıs:
                editor.remove("ad");
                editor.remove("sifre");
                editor.commit();
                startActivity(new Intent(ObisActivity.this,MainActivity.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


}