package com.wraitho.widgets.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Extends the basic {@link android.support.v7.widget.RecyclerView.Adapter} to enable Header view and Footer view
 *
 * @param <DefaultViewHolder> ViewHolder class extends {@link android.support.v7.widget.RecyclerView.ViewHolder} for "default" items.
 * @param <FooterViewHolder> ViewHolder class extends {@link android.support.v7.widget.RecyclerView.ViewHolder} for the footer item.
 * @param <HeaderViewHolder> ViewHolder class extends {@link android.support.v7.widget.RecyclerView.ViewHolder} for the header item.
 */
public abstract class ExtendedRecyclerViewAdapter<DefaultViewHolder extends RecyclerView.ViewHolder,
        FooterViewHolder extends RecyclerView.ViewHolder,
        HeaderViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean useHeader;
    private boolean useFooter;

    private enum ViewType {
        HEADER(1),
        DEFAULT(2),
        FOOTER(3);

        private int code;

        ViewType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && useHeader) {
            return ViewType.HEADER.getCode();
        } else if (position == getItemCount()-1 && useFooter) {
            return ViewType.FOOTER.getCode();
        }
        return ViewType.DEFAULT.getCode();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.FOOTER.getCode()) {
            return onCreateFooterViewHolder(parent);
        } else if (viewType == ViewType.HEADER.getCode()) {
            return onCreateHeaderViewHolder(parent);
        }
        return onCreateDefaultViewHolder(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ViewType.FOOTER.getCode()) {
            onBindFooterView((FooterViewHolder) holder);
        } else if (holder.getItemViewType() == ViewType.HEADER.getCode()) {
            onBindHeaderView((HeaderViewHolder) holder);
        } else {
            onBindDefaultView((DefaultViewHolder) holder, useHeader ? position-1 : position);
        }
    }

    @Override
    public final int getItemCount() {
        int itemCount = getRealItemCount();
        if (useHeader) {
            itemCount += 1;
        }
        if (useFooter) {
            itemCount += 1;
        }
        return itemCount;
    }

    public void setUseHeader(boolean useHeader) {
        this.useHeader = useHeader;
    }

    public void setUseFooter(boolean useFooter) {
        this.useFooter = useFooter;
    }

    protected abstract FooterViewHolder onCreateFooterViewHolder(ViewGroup parent);

    protected abstract HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    protected abstract DefaultViewHolder onCreateDefaultViewHolder(ViewGroup parent);

    protected abstract void onBindFooterView(FooterViewHolder holder);

    protected abstract void onBindHeaderView(HeaderViewHolder holder);

    protected abstract void onBindDefaultView(DefaultViewHolder holder, int position);

    protected abstract int getRealItemCount();
}
