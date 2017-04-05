package com.wraitho.arch.feature.characters;

import com.wraitho.arch.PerFragment;
import com.wraitho.arch.ActivityComponent;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = CharactersModule.class)
public interface CharactersComponent {
	void inject(CharactersFragment charactersFragment);
}
