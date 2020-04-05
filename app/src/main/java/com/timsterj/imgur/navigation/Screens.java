package com.timsterj.imgur.navigation;

import androidx.fragment.app.Fragment;

import com.timsterj.imgur.fragments.HomeFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static final class HomeScreen extends SupportAppScreen {

        String extraName;

        public HomeScreen(String extraName) {
            this.extraName = extraName;
        }

        @Override
        public Fragment getFragment() {
            return HomeFragment.getFragment(extraName);
        }
    }

}
