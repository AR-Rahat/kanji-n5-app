package com.example.kanji_n5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeekAdapter weekAdapter;
    private List<Week> weekList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.recyclerView);
        weekList = new ArrayList<>();
        weekAdapter = new WeekAdapter(weekList, new WeekAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Week selectedWord) {
                Intent intent = new Intent(Homepage.this, Weekwisekanji.class);
                intent.putExtra("name",selectedWord.getName());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weekAdapter);

        fetchweekFromFirestore();
    }
    private CollectionReference masterCollection;
    private void fetchweekFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("fields")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Week week = document.toObject(Week.class);
                                weekList.add(week);
                            }
                            weekAdapter.notifyDataSetChanged();
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}