package com.devon.firstapplication;


import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
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
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void menu() {
        onView(withId(R.id.hello_world))
                .check(matches(withText(R.string.hello)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.menu1))
                .perform(click());

        onView(withId(R.id.hello_world))
                .check(matches(withText(R.string.menu1)));
    }

    @Test
    public void enterAllErrorsInAllForm(){

        //Leave Name field blank
        onView(withId(R.id.nameEditText)).check(matches(withText("")));

        //Leave email blank
        onView(withId(R.id.emailEditText)).check(matches(withText("")));

        //Leave User name blank
        onView(withId(R.id.userEditText)).check(matches(withText("")));

        //Leave password blank
        onView(withId(R.id.passEditText)).check(matches(withText("")));

        //Leave job blank
        onView(withId(R.id.job)).check(matches(withText("")));

        //Verify login/Create button labeled correctly
        onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));

        onView(withId(R.id.loginBtn)).perform(click());





    }


}
//    @Test
//
//    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
//        onView(withId(datePickerLaunchViewId)).perform(click());
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
//        onView(withId(android.R.id.button1)).perform(click());
//    }
//    setDate(R.id.datePickerDialogButton, 1994, 1, 1);
