package com.wraitho.widgets.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Adapter class which deals with pagination on a view level. It shows the custom footer view, and requests for new data.
 */
public abstract class LoadMoreRecyclerViewAdapter<DefaultViewHolder extends RecyclerView.ViewHolder,
        FooterViewHolder extends RecyclerView.ViewHolder,
        HeaderViewHolder extends RecyclerView.ViewHolder>
        extends ExtendedRecyclerViewAdapter<DefaultViewHolder,FooterViewHolder, HeaderViewHolder> {

    private final PublishSubject<Void> loadMoreSubject = PublishSubject.create();

    private final AtomicBoolean keepOnAppending;
    private final AtomicBoolean dataPending;

    public LoadMoreRecyclerViewAdapter(boolean keepOnAppending) {
        this.keepOnAppending = new AtomicBoolean(keepOnAppending);
        this.dataPending = new AtomicBoolean(false);
    }

    /**
     * Let the adapter know that data is load and ready to view.
     *
     * @param keepOnAppending whether the adapter should request to load more when scrolling to the bottom.
     */
    public void onDataReady(boolean keepOnAppending) {
        dataPending.set(false);
        this.keepOnAppending.set(keepOnAppending);
        notifyDataSetChanged();
    }

    /**
     * Let the sub adapter know we need more data.
     * @param holder ViewHolder holding the footer view;
     */
    @Override
    protected void onBindFooterView(FooterViewHolder holder) {
        Log.d("ExtendedAdapter", "onBindViewHolder: " + "binding footer");
        if (!dataPending.get() && dataPending.compareAndSet(false, true)) {
            loadMoreSubject.onNext(null);
        }
    }

    public Observable<Void> getLoadMoreIntention() {
        Log.d("ExtendedAdapter", "onBindViewHolder: " + "getLoadMoreIntention");
        return loadMoreSubject;
    }

}
