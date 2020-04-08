package com.timsterj.imgur.rules;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;

import com.timsterj.imgur.ActivityUtils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class OpenFragmentRule<A extends AppCompatActivity> implements TestRule {

    private final ActivityTestRule<A> activityTestRule;
    private final Fragment fragment;

    public OpenFragmentRule(ActivityTestRule<A> aActivityTestRule, Fragment fragment) {
        this.activityTestRule = aActivityTestRule;
        this.fragment = fragment;

    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                ActivityUtils.openFragment(activityTestRule.getActivity(), fragment);
                base.evaluate();
            }
        };
    }
}

