package com.timsterj.imgur.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.timsterj.imgur.App;
import com.timsterj.imgur.R;
import com.timsterj.imgur.base.BaseActivity;
import com.timsterj.imgur.base.OnBackPressed;
import com.timsterj.imgur.contracts.Contracts;
import com.timsterj.imgur.databinding.ActivityHomeBinding;
import com.timsterj.imgur.navigation.Screens;
import com.timsterj.imgur.viewmodel.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class HomeActivity extends BaseActivity<HomeViewModel> {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;

    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getINSTANCE().getHomeComponent().inject(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            init();
        }

    }

    @Override
    public void init() {
        router.replaceScreen(new Screens.HomeScreen(Contracts.NavigationConstant.HOME_FRAGMENT));
    }

    @Override
    public HomeViewModel getViewModel() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return viewModel;
    }

    private Navigator getNavigator(){
        if (navigator == null) {
            navigator = new SupportAppNavigator(this, getSupportFragmentManager(), R.id.ftb_container);
        }
        return navigator;
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(getNavigator());
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        App.getINSTANCE().clearHomeComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Fragment currentFragment = null;
        for (Fragment fragment : fragments) {
            if (fragment.isVisible()) {
                currentFragment = fragment;
            }
        }

        if (currentFragment != null) {
            ((OnBackPressed) currentFragment).onBackPressed();
        } else {
            super.onBackPressed();
        }

    }
}
