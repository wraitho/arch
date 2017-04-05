package com.wraitho.arch.feature.characters.recyclerview.footer;

import rx.Observable;

public interface RecyclerFooterView {
    Observable<Void> getRetryButtonClicks();
    void render(FooterItem footerItem);
}
