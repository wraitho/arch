package com.wraitho.arch.feature.characters.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wraitho.arch.R;
import com.wraitho.arch.feature.characters.recyclerview.character.CharacterListItem;
import com.wraitho.arch.feature.characters.recyclerview.character.CharacterViewHolder;
import com.wraitho.arch.feature.characters.recyclerview.footer.FooterItem;
import com.wraitho.arch.feature.characters.recyclerview.footer.FooterViewHolder;
import com.wraitho.arch.feature.characters.recyclerview.header.HeaderItem;
import com.wraitho.arch.feature.characters.recyclerview.header.HeaderViewHolder;
import com.wraitho.widgets.recycler.LoadMoreRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CharactersRecyclerAdapter extends LoadMoreRecyclerViewAdapter<CharacterViewHolder, FooterViewHolder, HeaderViewHolder> {

    private final List<CharacterListItem> items;
    private final FooterItem footerItem;
    private final HeaderItem headerItem;
    private final Picasso picasso;

    public CharactersRecyclerAdapter(HeaderItem headerItem, FooterItem footerItem, Picasso picasso) {
        super(true);
        this.picasso = picasso;
        this.items = new ArrayList<>();
        this.headerItem = headerItem;
        this.footerItem = footerItem;

        setUseFooter(true);
        setUseHeader(false);
    }

    public void setItems(List<CharacterListItem> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    protected FooterViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_footer, parent, false));
    }

    @Override
    protected HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_header, parent, false));
    }

    @Override
    protected CharacterViewHolder onCreateDefaultViewHolder(ViewGroup parent) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_default, parent, false), picasso);
    }

    @Override
    protected void onBindDefaultView(CharacterViewHolder holder, int position) {
        holder.render(items.get(position));
    }

    @Override
    protected void onBindFooterView(FooterViewHolder holder) {
        super.onBindFooterView(holder);
        holder.render(footerItem);
    }

    @Override
    protected void onBindHeaderView(HeaderViewHolder holder) {
        // no-op for now
    }

    @Override
    protected int getRealItemCount() {
        return items == null ? 0 : items.size();
    }

}
