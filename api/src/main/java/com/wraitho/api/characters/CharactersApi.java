package com.wraitho.api.characters;

import com.wraitho.data.Character;

import java.util.List;

import rx.Observable;

public interface CharactersApi {
	Observable<List<Character>> getCharacters();
}
