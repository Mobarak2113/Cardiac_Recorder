package com.example.cardiac_recorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddRecord(){
        onView(withId(R.id.add_button1)).perform(click()); //Click add button to add a city to the list
        onView(withText("Add Cardiac Record")).check(matches(isDisplayed()));

        onView(withId(R.id.date)).perform(ViewActions.typeText("29-06-2022")); //Type a city name
        onView(withId(R.id.time)).perform(ViewActions.typeText("10:10am")); //Type a city name
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("100")); //Type a city name
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("70")); //Type a city name
        onView(withId(R.id.pulse)).perform(ViewActions.typeText("66")); //Type a city name
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(ViewActions.typeText("good")); //Type a city name
        Espresso.pressBack();

        onView(withId(R.id.add_button2)).perform(click());//Confirm the city name and add to the list
        onView(withText("Cardiac_Recorder")).check(matches(isDisplayed())); //Check the name on the screen

    }
}
