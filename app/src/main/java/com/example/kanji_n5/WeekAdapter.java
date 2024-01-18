package com.example.kanji_n5;
// WordAdapter.java
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WordViewHolder> {

    private List<Week> wordList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Week selectedWord);
    }

    public WeekAdapter(List<Week> wordList, OnItemClickListener onItemClickListener) {
        this.wordList = wordList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklist, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        final Week word = wordList.get(position);
        holder.textWord.setText(word.getName());
        holder.cardView.setCardElevation(0.5f);

        String [] color = {"#C5FFF8","#9BE8D8"};
        int a = holder.getAdapterPosition();
        Log.d("item",String.valueOf(a));
        String cc = color[a%2];
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
            textWord = itemView.findViewById(R.id.weekWord);
            cardView = itemView.findViewById(R.id.weekCard);
            // Initialize other TextViews if needed
        }
    }
}
