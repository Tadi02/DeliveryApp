package hu.tomkom.deliveryapp.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.ui.main.MainPresenter;

@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }
}
