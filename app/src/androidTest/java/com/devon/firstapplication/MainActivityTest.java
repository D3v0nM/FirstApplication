package com.devon.firstapplication;


import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);



    @Test
    public void enterAllBlankInAllForm() {


        //Leave Name field blank
        onView(withId(R.id.nameEditText)).check(matches(withText("")));

        //Leave email blank
        onView(withId(R.id.emailEditText)).check(matches(withText("")));

        //Leave User name blank
        onView(withId(R.id.userEditText)).check(matches(withText("")));

        //Leave password blank
        onView(withId(R.id.passEditText)).check(matches(withText("")));

        //Leave job blank
        onView(withId(R.id.jobEditText)).check(matches(withText("")));

        Espresso.closeSoftKeyboard();

        //Verify login/Create button labeled correctly
        onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));
        onView(withId(R.id.loginBtn)).perform(click());

        //All lines should produce corresponding blank error
        onView(withId(R.id.nameEditText)).check(matches(hasErrorText("Name is required")));
        //Leave email blank
        onView(withId(R.id.emailEditText)).check(matches(hasErrorText("Not an Email Address")));

        //Leave User name blank
        onView(withId(R.id.userEditText)).check(matches(hasErrorText("Username is required")));

        //Leave password blank
        onView(withId(R.id.passEditText)).
                check(matches(hasErrorText("Password is required and must be at least 6 characters")));

        onView(withId(R.id.loginBtn)).perform(ViewActions.scrollTo(), click());


    }

    @Test
    public void under18(){
        //click button
        onView(withId(R.id.birthday))
                .perform(click());
        //enter date picker fragment and set date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 4, 22));
        onView(withId(android.R.id.button1)).perform(click());
        //verify button GONE
        onView(withEffectiveVisibility(ViewMatchers.Visibility.GONE));

    }


    @Test
    public void enterAllCorrectFormTest() {

        //Enter a full name
        onView(withId(R.id.nameEditText)).perform(typeText("Devon Coolness"));

        //enter a complete email
        onView(withId(R.id.emailEditText)).perform(typeText("can@not.com"));

        // enter a user name
        onView(withId(R.id.userEditText)).perform(typeText("El Presidente"));

        // enter a fully met password
        onView(withId(R.id.passEditText)).perform(typeText("123456"));

        //enter a job
        onView(withId(R.id.jobEditText)).perform(typeText("The Chief"));

        //Enter a profile description
        onView(withId(R.id.profileEditText)).
                perform(typeText("Would you like to play a game??? How about 'Geo-thermal nuclear war'? No????"));

        Espresso.closeSoftKeyboard();



        // test screen rotation
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.nameEditText)).check(matches(withText("Devon Coolness")));
        onView(withId(R.id.emailEditText)).check(matches(withText("can@not.com")));
        onView(withId(R.id.userEditText)).check(matches(withText("El Presidente")));
        onView(withId(R.id.jobEditText)).check(matches(withText("The Chief")));
        onView(withId(R.id.profileEditText)).check(matches(withText("Would you like to play a game??? How about 'Geo-thermal nuclear war'? No????")));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.nameEditText)).check(matches(withText("Devon Coolness")));
        onView(withId(R.id.emailEditText)).check(matches(withText("can@not.com")));
        onView(withId(R.id.userEditText)).check(matches(withText("El Presidente")));
        onView(withId(R.id.jobEditText)).check(matches(withText("The Chief")));
        onView(withId(R.id.profileEditText)).check(matches(withText("Would you like to play a game??? How about 'Geo-thermal nuclear war'? No????")));
        //activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        //set birthday over 18
        //click button
        onView(withId(R.id.birthday)).perform(scrollTo(),click());

        //enter date picker fragment and set date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(1999, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());
        //verify button GONE
        onView(withEffectiveVisibility(ViewMatchers.Visibility.GONE));
        Espresso.closeSoftKeyboard();


        //Verify login/Create button labeled correctly
        onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));
        onView(withId(R.id.loginBtn)).perform(click());
    }




//    @Test
//    public void goToSecondActivityTest(View view){
//
//            int errors = 0;
//            if (nameEditText.getText().toString().length() == 0) {
//                nameEditText.setError("Name is required");
//
//                errors += 1;
//            }
//
//            if (emailEditText.getText().toString().length() == 0) {
//                emailEditText.setError("Email is required");
//                errors += 1;
//            }
//
//            if (!checkEmail(emailEditText.getText().toString())) {
//                emailEditText.setError("Not an Email Address");
//                errors += 1;
//            }
//
//            if (userEditText.getText().toString().length() == 0) {
//                userEditText.setError("Username is required");
//                errors += 1;
//            }
//
//            if (passEditText.getText().toString().length() == 0 || passEditText.getText().toString().length() < 6) {
//                passEditText.setError("Password is required and must be at least 6 characters");
//            }
//
//            if (errors < 1) {
//
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra(Constraints.KEY_NAME, nameEditText.getText().toString());
//                intent.putExtra(Constraints.KEY_EMAIL, emailEditText.getText().toString());
//                intent.putExtra(Constraints.KEY_USER, userEditText.getText().toString());
//                intent.putExtra(Constraints.KEY_AGE, Age.toString());
//                intent.putExtra(Constraints.KEY_PASS, passEditText.getText().toString());
//                intent.putExtra(Constraints.KEY_JOB, jobEditText.getText().toString());
//                intent.putExtra(Constraints.KEY_PROFILE, profileEditText.getText().toString());
//                startActivity(intent);
//            } else
//                Tools.toastMessage(this, "Errors found. Fix errors and try again");
//        }
//        onView(withId(R.id.loginBtn)).perform(click());
//    }


    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {

        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).
                perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
       onView(withId(android.R.id.button1)).perform(click());

    }
}


//    }


