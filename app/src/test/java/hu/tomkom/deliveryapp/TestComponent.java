package hu.tomkom.deliveryapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.tomkom.deliveryapp.interactor.InteractorModule;
import hu.tomkom.deliveryapp.mock.MockNetworkModule;

@Singleton
@Component(modules = {TestModule.class, InteractorModule.class, MockNetworkModule.class})
public interface TestComponent extends DeliveryApplicationComponent {
}
