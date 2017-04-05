package com.wraitho.arch.feature.characters;

import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.wraitho.api.characters.CharactersApi;
import com.wraitho.arch.PerFragment;
import com.wraitho.arch.feature.characters.recyclerview.CharactersRecyclerAdapter;
import com.wraitho.arch.feature.characters.recyclerview.footer.FooterItem;
import com.wraitho.arch.feature.characters.recyclerview.header.HeaderItem;
import com.wraitho.base.LifecycleHandler;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@PerFragment
@Module
class CharactersModule {

	private final CharactersFragment charactersFragment;

	CharactersModule(CharactersFragment charactersFragment) {
		this.charactersFragment = charactersFragment;
	}

	@Provides
	CharactersPresenter providesCharactersPresenter(LifecycleHandler<FragmentEvent> lifecycleHandler,
													Scheduler viewScheduler, CharactersApi charactersApi,
													CharactersStore charactersStore) {
		return new CharactersPresenter(lifecycleHandler, viewScheduler, charactersApi, charactersStore);
	}

	@Provides
	LifecycleHandler<FragmentEvent> providesLifecycleHandler() {
		return new LifecycleHandler<>(charactersFragment.lifecycle());
	}

	@Provides
	CharactersRecyclerAdapter providesCharactersRecyclerAdapter(Picasso picasso) {
		return new CharactersRecyclerAdapter(new HeaderItem("def"), new FooterItem(false), picasso);
	}

	@Provides
	LinearLayoutManager providesLinearLayoutManager() {
		return new LinearLayoutManager(charactersFragment.getActivity());
	}

}
