package com.timsterj.imgur.fragments;

import android.provider.ContactsContract;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.timsterj.imgur.R;
import com.timsterj.imgur.activities.HomeActivity;
import com.timsterj.imgur.model.Gallery;
import com.timsterj.imgur.model.Image;
import com.timsterj.imgur.rules.FragmentTestRule;
import com.timsterj.imgur.viewmodel.GalleryViewModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4ClassRunner.class)
public class GalleryInfoFragmentTest {

    int countItemComments;

    @Rule
    public FragmentTestRule fragmentTestRule = new FragmentTestRule<>(HomeActivity.class, new HomeFragment());

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(5000);

        onView(ViewMatchers.withId(R.id.rv_galleries))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        ViewActions.click()
                ));

    }

    @Test
    public void launchFragment_IsDisplayed() {
        onView(ViewMatchers.withId(R.id.ftb_container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Ignore
    private GalleryViewModel getViewModel() {
        return new ViewModelProvider(fragmentTestRule.getActivity()).get(GalleryViewModel.class);
    }


}
