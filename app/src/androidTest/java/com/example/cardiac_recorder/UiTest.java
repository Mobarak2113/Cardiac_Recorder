package com.example.cardiac_recorder;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI testing for app
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddRecord(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.add_button1)).perform(click());
        Espresso.onView(withText("Add Cardiac Record")).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.date)).perform(ViewActions.typeText("29-06-2022"));
        Espresso.onView(withId(R.id.time)).perform(ViewActions.typeText("10:10am"));
        Espresso.onView(withId(R.id.systolic)).perform(ViewActions.typeText("100"));
        Espresso.onView(withId(R.id.diastolic)).perform(ViewActions.typeText("70"));
        Espresso.onView(withId(R.id.pulse)).perform(ViewActions.typeText("66"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.comment)).perform(ViewActions.typeText("good"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.add_button2)).perform(click());
        Espresso.onView(withText("Cardiac_Recorder")).check(matches(isDisplayed()));

    }
}
