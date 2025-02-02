package com.example.tvcharactersapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvcharactersapp.R;
import com.example.tvcharactersapp.model.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private List<Character> originalCharacterList;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
        this.originalCharacterList = new ArrayList<>(characterList);
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.characterName.setText(character.getName());
        holder.characterDescription.setText(character.getDescription());
        holder.characterImage.setImageResource(character.getImageResId());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "Clicked: " + character.getName(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView characterName, characterDescription;
        ImageView characterImage;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.characterName);
            characterDescription = itemView.findViewById(R.id.characterDescription);
            characterImage = itemView.findViewById(R.id.characterImage);
        }
    }

    public void filter(String text) {
        characterList.clear();
        if (text.isEmpty()) {
            characterList.addAll(originalCharacterList);
        } else {
            for (Character character : originalCharacterList) {
                if (character.getName().toLowerCase().contains(text.toLowerCase())) {
                    characterList.add(character);
                }
            }
        }
        notifyDataSetChanged();
    }
}
