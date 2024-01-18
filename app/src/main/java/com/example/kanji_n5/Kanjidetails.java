package com.example.kanji_n5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

//import com.example.kanji_n5.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kanjidetails extends AppCompatActivity {

    //ActivityMainBinding binding;
    //private BottomNavigationView bview;
    BottomNavigationView bottomNavigationView;
    private String audio;
    private String kanji;
    private String meaning;
    private String pdf;
    private String usage;
    private String video;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanjidetails);
        audio = getIntent().getStringExtra("audio");
        kanji = getIntent().getStringExtra("kanji");
        meaning = getIntent().getStringExtra("meaning");
        pdf = getIntent().getStringExtra("pdf");
        usage = getIntent().getStringExtra("usage");
        video= getIntent().getStringExtra("video");
        word = getIntent().getStringExtra("word");

        //BottomNav
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Function for navigationBottom
        funBottomNavigation();
        /*//bview = findViewById(R.id.bottomNavigationView);

        //ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_kanjidetails);

        binding.NavigationBarView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.details:
                    replaceFragment(new detailsFragment());
                    break;
                case R.id.play:
                    replaceFragment(new playFragment());
                    break;
                case R.id.similar:
                    replaceFragment(new similarFragment());
                    break;
                case R.id.usage:
                    replaceFragment(new usageFragment());
                    break;
            }

            return true;
        });*/
    }

    private  void funBottomNavigation(){
        //Set Default item: when app open
        bottomNavigationView.setSelectedItemId(R.id.details);
        //Set Default Fragment with item: when app open
        replaceFragment(new detailsFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                // in my project "menuHome" is id in Menu file
                if (id==R.id.details){
                    replaceFragment(new detailsFragment());
                } else if (id==R.id.play) {
                    replaceFragment(new playFragment());
                } else if (id==R.id.similar) {
                    replaceFragment(new similarFragment());
                } else {
                    replaceFragment(new usageFragment());
                }
                // true then changes effect of items
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment){

        Bundle args = new Bundle();
        args.putString("audio",audio);
        args.putString("kanji",kanji);
        args.putString("meaning",meaning);
        args.putString("pdf",pdf);
        args.putString("usage",usage);
        args.putString("video",video);
        args.putString("word",word);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}