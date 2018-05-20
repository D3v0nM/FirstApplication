package com.devon.firstapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(JUnit4.class)
public class SecondActivityTest {
    //Enter a full name
    String name = "Devon Coolness";
    String email = "can@not.com";
    String user = "El Presidente";
    String pass = "123456";
    String job = "The Chief";
    String desc = "Would you like to play a game??? How about 'Geo-thermal nuclear war'? No????";
    String age = "33";


    @Rule
    public ActivityTestRule<SecondActivity> activityTestRule =
            new ActivityTestRule<SecondActivity>(SecondActivity.class){

    @Override
    protected Intent getActivityIntent() {

        Intent intent = new Intent();

        intent.putExtra(Constraints.KEY_USER, name);
        intent.putExtra(Constraints.KEY_AGE, age);
        intent.putExtra(Constraints.KEY_JOB, job);
        intent.putExtra(Constraints.KEY_PROFILE, desc);

        return intent;
    }

};



        @Test
    public void enterAllTabsTest() {
          // activityTestRule.getActivityIntent();


        // test screen rotation
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.nameAndAge)).check(matches(withText(name + "\n" + age )));
         onView(withId(R.id.job)).check(matches(withText("Occupation:\n" + job)));
        onView(withId(R.id.profileText)).check(matches(withText("Details you need to know: \n" + desc)));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // Espresso.closeSoftKeyboard();
        onView(withId(R.id.nameAndAge)).check(matches(withText(name + "\n" + age)));
        onView(withId(R.id.job)).check(matches(withText("Occupation:\n" + job)));
        onView(withId(R.id.profileText)).check(matches(withText("Details you need to know: \n" + desc)));
            onView(withId(R.id.viewPager)).check(matches(isDisplayed()));

         //Check Matches Tab
        verifyMatches();


        //Swipe and verify Settings
//        swipeRight();
         moveThroughSettings();
       activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       Espresso.closeSoftKeyboard();
//       verifySettings();
      activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       Espresso.closeSoftKeyboard();

        //Verify logoff button labeled correctly
            onView(withId(R.id.viewPager)).perform(swipeRight());

            scrollTo();
            onView(withId(R.id.logoutBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.logoutBtn)).check(matches(withText(R.string.logout)));


       // onView(withId(R.id.logoutBtn)).perform(click());
        //turns out you can even remove the part of: ".check(matches(allOf( isEnabled(), isClickable())))"
            // and go straight into .perform( new ViewAction()
            onView(withId(R.id.logoutBtn)).check(matches(allOf( isEnabled(), isClickable()))).perform(
                    new ViewAction() {
                        @Override
                        public Matcher<View> getConstraints() {
                            return isEnabled(); // no constraints, they are checked above
                        }

                        @Override
                        public String getDescription() {
                            return "click plus button";
                        }

                        @Override
                        public void perform(UiController uiController, View view) {
                            view.performClick();
                        }
                    }
            );

        }





    public void verifyMatches(){
           //Swipe to matches tab
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //onView(withId(R.id.viewPager)).check(matches(isCompletelyDisplayed()));
//        onView(withId(R.id.viewPager)).perform(click());
       onView(withId(R.id.viewPager)).perform(swipeLeft());

        onView(withId(R.id.activity_second)).check(matches(isDisplayed()));

       onView(withId(R.id.recycler_view)).perform(scrollTo(),
               RecyclerViewActions.actionOnItemAtPosition(0, click()));

  //      activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  //      Espresso.closeSoftKeyboard();
        scrollTo();
        onData(anything())
                .atPosition(0)
                .onChildView(allOf(withId(R.id.matches_name), withText(R.string.matches)));


        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Espresso.closeSoftKeyboard();
//        onView(withId(R.layout.matches_tab)).check(matches(withText(R.string.matches)));

 //
        //onData(anything()).inAdapterView(withContentDescription("desc")).atPosition(x).perform(click());
        scrollTo();
       // onData(anything()).inAdapterView(withId(R.id.recycler_view)).atPosition(3).check(matches(withText(R.string.matches)));
       //onData(anyOf()).inAdapterView((withId(R.id.recycler_view))).atPosition(3).perform(click());
      onData(withId(R.id.like_button)).atPosition(3).inAdapterView(withEffectiveVisibility(ViewMatchers.Visibility.GONE));

               //inAdapterView(withContentDescription("like")).perform(click()





    }

    public void moveThroughSettings(){


        onView(withId(R.id.viewPager)).perform(swipeLeft());

        onView(withId(R.id.viewPager)).perform(swipeRight());

            onView(withId(R.id.settings_tab)).check(matches(withText(R.string.settings)));

       // onView(withId(R.id.viewPager)).perform(swipeRight());


    }



//    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
//
//        onView(withId(datePickerLaunchViewId)).perform(click());
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).
//                perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
//        onView(withId(android.R.id.button1)).perform(click());
//
//    }


}
