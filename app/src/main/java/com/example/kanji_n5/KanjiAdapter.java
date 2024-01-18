package com.example.kanji_n5;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.WordViewHolder> {
    private List<Kanji> wordList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Kanji selectedWord);
    }

    public KanjiAdapter(List<Kanji> wordList, OnItemClickListener onItemClickListener) {
        this.wordList = wordList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kanji_list, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        final Kanji word = wordList.get(position);
        String FinalKanji = word.getKanji() +" ["+word.getWord()+"]";
        holder.textWord.setText(FinalKanji);
        holder.cardView.setCardElevation(0.5f);

        String [] color = {"#C5FFF8","#A6F6FF","#9BE8D8","#E2F6CA","#F8FDCF"};
        int a = holder.getAdapterPosition();
        Log.d("item",String.valueOf(a));
        String cc = color[a%5];
        holder.cardView.setCardBackgroundColor(Color.parseColor(cc));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(word);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView textWord;
        CardView cardView;

        WordViewHolder(View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.textWord);
            cardView = itemView.findViewById(R.id.kanjiCard);

        }
    }

}
