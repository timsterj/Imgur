package com.timsterj.imgur;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import com.timsterj.imgur.R;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ActivityUtils {


    private ActivityUtils(){}

    public static void openFragment(AppCompatActivity activity, Fragment newFragment) {

        activity
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ftb_container, newFragment)
                .commitAllowingStateLoss();

    }


}
