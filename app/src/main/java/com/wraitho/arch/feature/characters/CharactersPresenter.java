package com.wraitho.arch.feature.characters;

import android.util.Log;

import com.trello.rxlifecycle.android.FragmentEvent;
import com.wraitho.api.RetrofitException;
import com.wraitho.api.characters.CharactersApi;
import com.wraitho.arch.MarvelImageVariants;
import com.wraitho.arch.feature.characters.recyclerview.character.CharacterListItem;
import com.wraitho.base.LifecycleHandler;
import com.wraitho.base.Presenter;
import com.wraitho.data.Character;
import com.wraitho.data.Error;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

class CharactersPresenter extends Presenter<CharacterView, FragmentEvent> {

	private final CharactersApi charactersApi;
	private final CharactersStore charactersStore;
	private String TAG = "Charactersss";

	/**
	 * Basic constructor for presenters.
	 *
	 * @param lifecycleHandler This can be provided from the joining Activity/Fragment
	 * @param viewScheduler    In case of android it is {@link AndroidSchedulers#mainThread()} but it's better to provide
	 */
	public CharactersPresenter(LifecycleHandler<FragmentEvent> lifecycleHandler, Scheduler viewScheduler,
							   CharactersApi charactersApi, CharactersStore charactersStore) {
		super(lifecycleHandler, viewScheduler);
		this.charactersApi = charactersApi;
		this.charactersStore = charactersStore;
	}

	@Override
	protected void initLifecycleCallbacks() {}

	@Override
	protected void initUiInteractions() {

		charactersStore.getCharacters()
				.observeOn(getViewScheduler())
				.distinctUntilChanged()
				.map(characters -> {
					Log.d(TAG, "initUiInteractions: chars updated in store, size:" + characters.size());
					List<CharacterListItem> characterListItemList = new ArrayList<>();
					for (Character character : characters) {
						characterListItemList.add(
								new CharacterListItem(character.getThumbnail().getPath(),
										character.getThumbnail().getExtension(), MarvelImageVariants.LANDSCAPE_INCREDIBLE));
					}
					return characterListItemList;
				})
				.subscribe(characters -> getView().updateCharactersList(characters, 0, characters.size(), true));

		charactersApi.getCharacters().subscribe(charactersStore::addItems, this::handleError);
	}

	private void handleError(Throwable throwable) {
		throwable.printStackTrace();
		if (throwable instanceof RetrofitException) {
			RetrofitException exception = (RetrofitException) throwable;
			try {
				Error error = exception.getErrorBodyAs(Error.class);
				Log.d(TAG, "Error Status: " + error.getMessage());
				Log.d(TAG, "Error Code: " + error.getCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
