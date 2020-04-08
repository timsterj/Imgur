package com.timsterj.imgur.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.timsterj.imgur.data.network.api.ImgurApi;
import com.timsterj.imgur.data.network.dto.CommentDTO;
import com.timsterj.imgur.data.pagination.GalleryDataSourceFactory;
import com.timsterj.imgur.data.pagination.GallerySource;
import com.timsterj.imgur.model.Comment;
import com.timsterj.imgur.rules.RxImmediateSchedulerRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assert_;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GalleryViewModelTest {

    @ClassRule
    public static RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();
    @Rule
    public TestRule archTestingRule = new InstantTaskExecutorRule();

    @Mock
    ImgurApi imgurApi;
    @Mock
    GalleryDataSourceFactory galleryDataSourceFactory;
    @Mock
    GallerySource gallerySource;
    @Mock
    Observer<List<Comment>> observerComments;

    private GalleryViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new GalleryViewModel(true);

        viewModel.imgurApi = imgurApi;
        viewModel.factory = galleryDataSourceFactory;

        when(galleryDataSourceFactory.create()).thenReturn(gallerySource);
        when(gallerySource.getDataState()).thenAnswer(Answers.RETURNS_MOCKS);

    }

    @Test
    public void getListComments_notNull() {
        MutableLiveData<List<Comment>> liveData = viewModel.getListComments();

        assert_().that(liveData).isNotNull();

    }

    @Test
    public void initPagination_ListPagedLiveData_notNull() {

        viewModel.initPagination();

        assert_().that(viewModel.getPagedListLiveData()).isNotNull();

    }

    @Test
    public void initPagination_DataState_notNull() {

        viewModel.initPagination();

        assert_().that(viewModel.getDataState()).isNotNull();

    }

    @Test
    public void getComments_GalleryId_Normal_GetComments() {

        ArrayList<Comment> testData = new ArrayList<>();
        testData.add(new Comment());
        testData.add(new Comment());
        testData.add(new Comment());
        testData.add(new Comment());

        CommentDTO testComment = new CommentDTO();
        testComment.setData(testData);

        Observable<CommentDTO> testObservable = Observable.just(testComment);

        doReturn(testObservable).when(imgurApi).getComments(anyString(), anyString());

        viewModel.getComments("test");

        assert_().that(viewModel.getListComments().getValue()).isNotNull();

    }

    @Test(expected = IllegalArgumentException.class)
    public void getComments_GalleryId_Null_ThrowException() {
        viewModel.getComments(null);
    }

    @Test
    public void getComments_NullResponse_RepeatRequest(){
        ArrayList<Comment> testData = null;

        CommentDTO testComment = new CommentDTO();
        testComment.setData(testData);

        Observable<CommentDTO> testObservable = Observable.just(testComment);

        doReturn(testObservable).when(imgurApi).getComments(anyString(), anyString());

        viewModel.getComments("test");

        assert_().that(viewModel.getListComments().getValue()).isNull();

    }




}