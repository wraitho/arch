package com.wraitho.arch.feature.characters.recyclerview.header;

import rx.Observable;

public interface RecyclerHeaderView {
    Observable<Void> getListHeaderClicks();
    void changeBackgroundColor(int color);
}
