package com.wraitho.arch.feature.characters;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wraitho.arch.R;
import com.wraitho.arch.feature.characters.recyclerview.CharactersRecyclerAdapter;
import com.wraitho.arch.feature.characters.recyclerview.character.CharacterListItem;
import com.wraitho.arch.feature.home.HomeActivity;
import com.wraitho.base.PresenterFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class CharactersFragment extends PresenterFragment implements CharacterView {

	@Inject CharactersPresenter charactersPresenter;
	@Inject CharactersRecyclerAdapter charactersRecyclerAdapter;
	@Inject LinearLayoutManager linearLayoutManager;

	@BindView(R.id.characters_list) RecyclerView charactersListView;

	private View rootView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_characters, container, false);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		charactersListView.setAdapter(charactersRecyclerAdapter);
		charactersListView.setLayoutManager(linearLayoutManager);
		charactersListView.setHasFixedSize(true);
	}

	@Override
	protected void injectDependencies() {
		if (!(getActivity() instanceof HomeActivity)) {
			throw new RuntimeException("CharactersFragment should be in the HomeActivity, but it's not.");
		}

		CharactersComponent charactersComponent = DaggerCharactersComponent.builder()
				.activityComponent(((HomeActivity) getActivity()).getActivityComponent())
				.charactersModule(new CharactersModule(this))
				.build();
		charactersComponent.inject(this);
	}

	@Override
	protected void initialisePresenters() {
		charactersPresenter.init(this);
	}

	@Override
	public void updateCharactersList(List<CharacterListItem> items, int refreshFrom, int refreshTo, boolean nextPage) {
		charactersRecyclerAdapter.setItems(items);
		refreshAdapter(refreshFrom, refreshTo, nextPage);
	}

	@Override
	public Observable<Void> getLoadMoreIntention() {
		return null;
	}

	private void refreshAdapter(final int refreshFrom, final int refreshTo, final boolean isThereNextPage) {
		Handler handler = new Handler();

		handler.post(() -> {
			charactersRecyclerAdapter.notifyItemRangeInserted(refreshFrom, refreshTo);
			Log.d("RX", "adapter notified from " + refreshFrom + " to " + refreshTo);
			if (isThereNextPage) {
				charactersRecyclerAdapter.onDataReady(true);
			} else {
				charactersRecyclerAdapter.setUseFooter(false);
				charactersRecyclerAdapter.onDataReady(false);
			}
		});
	}

}
