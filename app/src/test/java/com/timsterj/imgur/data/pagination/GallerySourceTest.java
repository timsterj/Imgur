package com.timsterj.imgur.data.pagination;

import android.telephony.PhoneNumberUtils;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.paging.PageKeyedDataSource;

import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.data.network.api.ImgurApi;
import com.timsterj.imgur.data.network.dto.CommentDTO;
import com.timsterj.imgur.data.network.dto.GalleryDTO;
import com.timsterj.imgur.model.Comment;
import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.rules.RxImmediateSchedulerRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static com.google.common.truth.Truth.assert_;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GallerySourceTest {

    private GallerySource gallerySource;

    @ClassRule
    public static RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public TestRule archTestingRule = new InstantTaskExecutorRule();

    @Mock
    ImgurApi imgurApi;
    @Mock
    Observer<String> observer;
    @Mock
    PageKeyedDataSource.LoadInitialParams<Integer> params;
    @Mock
    PageKeyedDataSource.LoadInitialCallback<Integer, Gallery> callback;

    @Before
    public void setUp() {
        gallerySource = new GallerySource(true);
        gallerySource.imgurApi = imgurApi;

    }

    @Test
    public void loadInitial_Normal_Initial() {
        ArrayList<Gallery> testData = new ArrayList<>();
        testData.add(new Gallery());
        testData.add(new Gallery());
        testData.add(new Gallery());
        testData.add(new Gallery());

        GalleryDTO testGallery = new GalleryDTO();
        testGallery.setData(testData);

        Observable<GalleryDTO> testObservable = Observable.just(testGallery);
        doReturn(testObservable).when(imgurApi).getGalleries(anyString(), anyString(), anyString());

        gallerySource.getDataState().observeForever(observer);

        gallerySource.loadInitial(params, callback);

        verify(observer).onChanged(Contracts.NetworkState.LOADED);

        verify(callback).onResult(ArgumentMatchers.anyList(), ArgumentMatchers.isNull(), anyInt());

    }

}