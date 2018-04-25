package com.devon.firstapplication;


import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class AgePickerTest {
    @Rule
    public android.support.test.rule.ActivityTestRule<MainActivity>
            ActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void Age (){

        onView(withId(R.id.birthday)).check(matches(withText(R.string.enter_dob)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.id.birthday)).perform(click());
    }
}
