package com.timsterj.imgur.fragments;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.timsterj.imgur.ActivityUtils;
import com.timsterj.imgur.R;
import com.timsterj.imgur.activities.HomeActivity;
import com.timsterj.imgur.rules.FragmentTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

@RunWith(AndroidJUnit4ClassRunner.class)
public class HomeFragmentTest {

    int countItem = 0;

    @Rule
    public FragmentTestRule fragmentTestRule = new FragmentTestRule<>(HomeActivity.class, new HomeFragment());

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(5000);
        RecyclerView recyclerView = fragmentTestRule.getFragment().getView().findViewById(R.id.rv_galleries);

        countItem = recyclerView.getAdapter().getItemCount();

    }

    @Test
    public void launchFragment_IsDisplayed() {
        onView(ViewMatchers.withId(R.id.ftb_container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


    @Test
    public void scrollRecyclerView_OpenInfo() throws InterruptedException {

        if (countItem > 0) {
            for (int i = 0; i < countItem; i++) {

                onView(ViewMatchers.withId(R.id.rv_galleries))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(i,
                                ViewActions.click()
                        ));

                Espresso.pressBack();
            }
        }


    }

    @Test
    public void scrollRecyclerView_checkProgressBar() throws InterruptedException {


        onView(ViewMatchers.withId(R.id.rv_galleries))
                .perform(RecyclerViewActions.scrollToPosition(countItem - 1));

        onView(ViewMatchers.withId(R.id.progress_bar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }



}
