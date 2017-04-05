package com.wraitho.arch.feature.characters.recyclerview.character;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wraitho.arch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final Picasso picasso;

    @BindView(R.id.character_horizontal_list_item_image)
    ImageView characterImageView;

    public CharacterViewHolder(View itemView, Picasso picasso) {
        super(itemView);
        this.picasso = picasso;
        ButterKnife.bind(this, itemView);
    }

    public void render(CharacterListItem item) {
        Log.d(getClass().getSimpleName(), "render imageview with url " + item.getImageUrl());
		picasso.load(item.getImageUrl()).into(characterImageView);
    }
}
