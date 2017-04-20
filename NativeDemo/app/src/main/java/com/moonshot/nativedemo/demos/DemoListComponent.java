package com.moonshot.nativedemo.demos;

import com.moonshot.nativedemo.data.source.DemoRepositoryComponent;
import com.moonshot.nativedemo.di.FragmentScoped;

import dagger.Component;

/**
 * Created by armando_contreras on 4/20/17.
 */

@FragmentScoped
@Component(dependencies = DemoRepositoryComponent.class, modules = DemoPresenterModule.class)
public interface DemoListComponent {

    void inject(DemoListActivity activity);

}
