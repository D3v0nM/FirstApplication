package com.devon.firstapplication;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;




@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

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
        onView(withId(R.id.passEditText)).perform(typeText(("no")));

        //Leave job blank
        onView(withId(R.id.jobEditText)).check(matches(withText("")));

        Espresso.closeSoftKeyboard();

        //Verify login/Create button labeled correctly
       // onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));



        //maybe put error/toast message test here
    }



    @Test
    public void goToSecondActivityFail(){

    }

    @Test
    public void enterAllCorrectFormTest() {

        //Leave Name field blank
        onView(withId(R.id.nameEditText)).perform(typeText("Devon Coolness"));

        //Leave email blank
        onView(withId(R.id.emailEditText)).perform(typeText("can@not.com"));

        //Leave User name blank
        onView(withId(R.id.userEditText)).perform(typeText("El Presidente"));

        //Leave password blank
        onView(withId(R.id.passEditText)).check(matches(withText("123456")));

        //Leave job blank
        onView(withId(R.id.jobEditText)).check(matches(withText("The Cheif")));

        Espresso.closeSoftKeyboard();



        //Verify login/Create button labeled correctly
       // onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));
    }

    @Test
    public void portToLandRotate(){
        enterAllCorrectFormTest();

    }

    @Test
    public void goToSecondActivityTest(){

        onView(withId(R.id.loginBtn)).perform(click());
    }
}






//    @Test
//
//    public static void setDateOver18(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
//
//        onView(withId(datePickerLaunchViewId)).perform(click());
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
//        onView(withId(android.R.id.button1)).perform(click());
//
//       setDateOver18(R.id.birthday, 1994, 1, 1);
//
//    }
//    @Test
//
//    public  void setDateUnder18(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
//        onView(withId(datePickerLaunchViewId)).perform(click());
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
//        onView(withId(android.R.id.button1)).perform(click());
//
//       setDateUnder18(R.id.birthday, 2000, 4, 22);
//
//    }


