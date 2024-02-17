package com.example.kanji_n5;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        holder.textKanji.setText(word.getKanji());
        holder.textWord.setText(word.getWord());
        holder.cardView.setCardElevation(8f);

        // Set background color with some transparency for a nicer look
        int backgroundColor = getBackgroundColor(position);
        holder.cardView.setCardBackgroundColor(backgroundColor);

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
        TextView textKanji;
        CardView cardView;

        WordViewHolder(View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.textWord);
            textKanji = itemView.findViewById(R.id.textKanji);
            cardView = itemView.findViewById(R.id.kanjiCard);
        }
    }

    // Method to get background color for each item
    private int getBackgroundColor(int position) {
        int[] colors = {Color.parseColor("#B5C0D0"), Color.parseColor("#CCD3CA"),
                Color.parseColor("#F5E8DD"), Color.parseColor("#C7FFC7")};
        return colors[position % colors.length];
    }
}
