package com.wraitho.arch;

import com.wraitho.api.ApiModule;
import com.wraitho.api.characters.CharactersApi;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = { ArchModule.class, ApiModule.class})
public interface ArchComponent {

    void inject(MainActivity mainActivity);

    // EXPOSING GENERAL STUFFS
    Scheduler scheduler();

    // EXPOSING CLIENTS
    CharactersApi charactersApi();

}
