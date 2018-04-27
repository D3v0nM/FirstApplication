package com.devon.firstapplication;


import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
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


        //All lines should produce corresponding blank error
        onView(withId(R.id.nameEditText)).check(matches(hasErrorText("Name is required")));
        //Leave email blank
        onView(withId(R.id.emailEditText)).check(matches(hasErrorText("Email is required")));

        //Leave User name blank
        onView(withId(R.id.userEditText)).check(matches(hasErrorText("A username is required")));

        //Leave password blank
        onView(withId(R.id.passEditText)).
                check(matches(hasErrorText("Password is required and must be at least 6 characters")));


        // verify error count logic for second activity
        onView(withId(R.id.loginBtn)).check(matches(withText(R.string.login)));
        onView(withId(R.id.loginBtn)).perform(click());

    }

    @Test
    public void under18(){
       setDate(R.id.birthday, 2000, 4, 22);
       onView(withId(R.id.loginBtn)).check(doesNotExist());

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
                perform(typeText("Would you like to play a game???\n"  + "HOw about 'Geo-thermal nuclear war'? \n " +  "No????"));





        //set birthday over 18
        setDate(R.id.birthday, 1994, 11, 11);


        Espresso.closeSoftKeyboard();

        // test screen rotation
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.nameEditText)).check(matches(withText("Devon Coolness")));
        onView(withId(R.id.emailEditText)).check(matches(withText("can@not.com")));
        onView(withId(R.id.userEditText)).check(matches(withText("El Presidente")));
        onView(withId(R.id.jobEditText)).check(matches(withText("he Chiefr")));
        onView(withId(R.id.profileEditText)).check(matches(withText("Would you like to play a game??? \n"+
                "HOw about 'Geo-thermal nuclear war'?\n No????")));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.nameEditText)).check(matches(withText("Devon Coolness")));
        onView(withId(R.id.emailEditText)).check(matches(withText("can@not.com")));
        onView(withId(R.id.userEditText)).check(matches(withText("El Presidente")));
        onView(withId(R.id.jobEditText)).check(matches(withText("he Chief")));
        onView(withId(R.id.profileEditText)).check(matches(withText("Would you like to play a game???\n"  +
                "HOw about 'Geo-thermal nuclear war'? \n " +  "No????")));

        onView(withId(R.id.loginBtn)).perform(ViewActions.scrollTo());



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


