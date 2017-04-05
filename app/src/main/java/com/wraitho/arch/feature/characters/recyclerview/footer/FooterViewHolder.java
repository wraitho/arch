package com.wraitho.arch.feature.characters.recyclerview.footer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding.view.RxView;
import com.wraitho.arch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class FooterViewHolder extends RecyclerView.ViewHolder implements RecyclerFooterView {

    @BindView(R.id.footer_progress)
    ProgressBar progressBar;

    @BindView(R.id.footer_error)
    View errorView;

    @BindView(R.id.footer_error_button_retry)
    View retry;

    public FooterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Observable<Void> getRetryButtonClicks() {
        return RxView.clicks(retry);
    }

    // in this case this can only load or show error.
    // no such option it's not loading but no error as this item is not shown at all in such a case
    @Override
    public void render(FooterItem footerItem) {
        progressBar.setVisibility(footerItem.isLoading() ? View.GONE : View.VISIBLE);
        errorView.setVisibility(footerItem.isLoading() ? View.VISIBLE : View.GONE);
    }
}
