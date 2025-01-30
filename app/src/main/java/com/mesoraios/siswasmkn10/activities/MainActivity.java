package com.mesoraios.siswasmkn10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Grid;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mesoraios.siswasmkn10.R;
import com.mesoraios.siswasmkn10.adapter.MenuAdapter;
import com.mesoraios.siswasmkn10.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMenu;

    int[]gambarMenu = {R.drawable.spp,R.drawable.un};
    String[] namaMenu = {"SPP","Pengumuman UN"};
    List<Menu>listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rvMenu = findViewById(R.id.rv_menu);

        GridLayoutManager glm = new GridLayoutManager(this,2);
        rvMenu.setLayoutManager(glm);

        listMenu = new ArrayList<>();

        for (int i=0;i<gambarMenu.length;i++){
            Menu menu = new Menu(gambarMenu[i],namaMenu[i]);
            listMenu.add(menu);
        }

        MenuAdapter adapter = new MenuAdapter(MainActivity.this,listMenu);
        rvMenu.setAdapter(adapter);
    }

}