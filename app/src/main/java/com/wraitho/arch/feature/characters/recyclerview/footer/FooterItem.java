package com.wraitho.arch.feature.characters.recyclerview.footer;

public class FooterItem {

    private final boolean mLoading;

    public FooterItem(boolean loading) {
        mLoading = loading;
    }

    public boolean isLoading() {
        return mLoading;
    }
}
