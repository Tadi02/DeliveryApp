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
import hu.tomkom.deliveryapp.ui.main.MainPresenter;
import hu.tomkom.deliveryapp.ui.main.MainScreen;
import hu.tomkom.deliveryapp.util.DeliveryTestHelper;
import hu.tomkom.deliveryapp.util.RobolectricDaggerTestRunner;

import static hu.tomkom.deliveryapp.TestHelper.setTestInjector;
import static junit.framework.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainTest {

    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    ArgumentCaptor<List> listCaptor;
    ArgumentCaptor<Date> dateCaptor;
    ArgumentCaptor<String> stringCaptor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);
        listCaptor = ArgumentCaptor.forClass(List.class);
        dateCaptor = ArgumentCaptor.forClass(Date.class);
        stringCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void getData(){
        mainPresenter.fetchData();

        verify(mainScreen).showTodaysDeliveries(listCaptor.capture());
        assertNotNull(listCaptor.getValue());

        verify(mainScreen).setDeliveryNumbers(anyInt(), anyInt());
    }

    @Test
    public void markDeliveryDone(){
        String notDoneId = MockDeliveryApi.getNotDoneDeliveryId();

        mainPresenter.markDeliveryCompleted(notDoneId);

        verify(mainScreen).showTodaysDeliveries(listCaptor.capture());
        assertFalse(DeliveryTestHelper.checkIfDeliveryPresent(listCaptor.getValue(), notDoneId));
    }

    @Test
    public void listItemNavigation(){
        String id = "ASD";

        mainPresenter.listItemClicked(id);

        verify(mainScreen).navigateToDetails(stringCaptor.capture());
        assertEquals(id, stringCaptor.getValue());
    }


    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }


}
