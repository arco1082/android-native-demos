package com.moonshot.nativedemo.data.source;

import com.moonshot.nativedemo.di.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by armando_contreras on 4/20/17.
 */

@Singleton
@Component(modules = {DemoRepositoryModule.class, ApplicationModule.class})
public interface DemoRepositoryComponent {

    DemoRepository getDemoRepository();
}

