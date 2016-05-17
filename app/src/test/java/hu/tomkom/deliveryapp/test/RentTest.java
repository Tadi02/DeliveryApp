package hu.tomkom.deliveryapp.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.tomkom.deliveryapp.BuildConfig;
import hu.tomkom.deliveryapp.mock.MockRentApi;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;
import hu.tomkom.deliveryapp.ui.rent.RentPresenter;
import hu.tomkom.deliveryapp.ui.rent.RentScreen;
import hu.tomkom.deliveryapp.util.RobolectricDaggerTestRunner;

import static hu.tomkom.deliveryapp.TestHelper.setTestInjector;
import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RentTest {

    private RentPresenter rentPresenter;
    private RentScreen rentScreen;

    ArgumentCaptor<Rent> rentCaptor;
    ArgumentCaptor<List> listCaptor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        rentScreen = mock(RentScreen.class);
        rentPresenter = new RentPresenter();
        rentPresenter.attachScreen(rentScreen);
        rentCaptor =  ArgumentCaptor.forClass(Rent.class);
        listCaptor = ArgumentCaptor.forClass(List.class);
    }

    @Test
    public void getRent(){
        rentPresenter.setRentId(MockRentApi.getValidRentId());
        rentPresenter.fetchData();

        verify(rentScreen).showRentData(rentCaptor.capture());
        assertNotNull(rentCaptor.getValue());

        verify(rentScreen).showComments(listCaptor.capture());
        assertNotNull(listCaptor.getValue());
    }

    @After
    public void tearDown() {
        rentPresenter.detachScreen();
    }

}
