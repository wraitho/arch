package com.wraitho.arch.feature.characters;

import com.wraitho.data.Character;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class CharactersStore {

	private PublishSubject<List<Character>> charactersData = PublishSubject.create();

	public void addItems(List<Character> characters) {
		charactersData.onNext(characters);
	}

	public Observable<List<Character>> getCharacters() {
		return charactersData.asObservable();
	}
}
