package com.wraitho.arch;

import com.squareup.picasso.Picasso;
import com.wraitho.api.ApiModule;
import com.wraitho.api.characters.CharactersApi;
import com.wraitho.arch.feature.characters.CharactersStore;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = { ArchModule.class, ApiModule.class, StoresModule.class})
public interface ArchComponent {

    void inject(MainActivity mainActivity);

    // EXPOSING GENERAL STUFFS
    Scheduler scheduler();
    Picasso picasso();

    // EXPOSING API-CLIENTS
    CharactersApi charactersApi();

    // EXPOSING STORES
    CharactersStore charactersStrore();

}
