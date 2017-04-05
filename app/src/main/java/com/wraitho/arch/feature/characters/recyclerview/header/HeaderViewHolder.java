package com.wraitho.arch.feature.characters.recyclerview.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.wraitho.arch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class HeaderViewHolder extends RecyclerView.ViewHolder implements RecyclerHeaderView {

    @BindView(R.id.recycler_view_header_text)
    TextView textView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void render(HeaderItem headerItem) {
        textView.setText(headerItem.getData());
    }

    @Override
    public Observable<Void> getListHeaderClicks() {
        return RxView.clicks(itemView);
    }

    @Override
    public void changeBackgroundColor(int color) {
        itemView.setBackgroundColor(color);
    }
}
