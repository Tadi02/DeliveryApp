package hu.tomkom.deliveryapp.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import hu.tomkom.deliveryapp.BuildConfig;
import hu.tomkom.deliveryapp.mock.MockDeliveryApi;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryPresenter;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryScreen;
import hu.tomkom.deliveryapp.util.DeliveryTestHelper;
import hu.tomkom.deliveryapp.util.RobolectricDaggerTestRunner;

import static hu.tomkom.deliveryapp.TestHelper.setTestInjector;
import static junit.framework.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DeliveryTest {

    private DeliveryPresenter deliveryPresenter;
    private DeliveryScreen deliveryScreen;

    ArgumentCaptor<List> listCaptor;
    ArgumentCaptor<Date> dateCaptor;
    ArgumentCaptor<String> stringCaptor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        deliveryScreen = mock(DeliveryScreen.class);
        deliveryPresenter = new DeliveryPresenter();
        deliveryPresenter.attachScreen(deliveryScreen);
        listCaptor = ArgumentCaptor.forClass(List.class);
        dateCaptor = ArgumentCaptor.forClass(Date.class);
        stringCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void getDeliveries(){
        Date date = new Date();

        deliveryPresenter.setDate(date);

        verify(deliveryScreen, atLeastOnce()).showDate(dateCaptor.capture());
        assertEquals(date, dateCaptor.getValue());

        verify(deliveryScreen, atLeastOnce()).showDeliveries(listCaptor.capture());
        assertNotNull(listCaptor.getValue());
        assertTrue(listCaptor.getValue().size() > 0);
    }

    @Test
    public void markDeliveryDone(){
        String notDoneId = MockDeliveryApi.getNotDoneDeliveryId();

        deliveryPresenter.markDeliveryCompleted(notDoneId);

        verify(deliveryScreen).showDeliveries(listCaptor.capture());
        assertTrue(DeliveryTestHelper.checkIfDeliveryMarkedDone(listCaptor.getValue(), notDoneId));
    }

    @Test
    public void listItemNavigation(){
        String id = "ASD";

        deliveryPresenter.listItemClicked(id);

        verify(deliveryScreen).navigateToDetails(stringCaptor.capture());
        assertEquals(id, stringCaptor.getValue());
    }

    @After
    public void tearDown() {
        deliveryPresenter.detachScreen();
    }

}
