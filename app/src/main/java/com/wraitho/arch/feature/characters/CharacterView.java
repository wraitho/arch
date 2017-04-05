package com.wraitho.arch.feature.characters;

import com.wraitho.arch.feature.characters.recyclerview.character.CharacterListItem;

import java.util.List;

import rx.Observable;

public interface CharacterView {

	/*
	Showing a list of characters, two featured planned for now:
		- showing the list
		- handling clicks on ListItems --> opening a character details view ? might be placed in the listview's presenter?
	 */

	void updateCharactersList(List<CharacterListItem> items, int refreshFrom, int refreshTo, boolean nextPage);
	Observable<Void> getLoadMoreIntention();

}
