package com.familyservicestoronto.shortcut;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class WhatsappTutorialActivityTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityTestRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void whatsappTutorialActivityTest() {
        ViewInteraction imageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2),
                        0),
                        isDisplayed()));
        imageView.perform(click());

        ViewInteraction imageView2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0),
                        0),
                        isDisplayed()));
        imageView2.perform(click());

        ViewInteraction imageView3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2),
                        0),
                        isDisplayed()));
        imageView3.perform(click());

        ViewInteraction button = onView(
                allOf(withText("Add New Contact/Group"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Add New Contact/Group"),
                        withParent(withParent(withId(R.id.tutorialConstraintLayout))),
                        isDisplayed()));
        textView.check(matches(withText("Add New Contact/Group")));

        ViewInteraction button2 = onView(
                allOf(withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutorialConstraintLayout),
                                        0),
                                2),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withText("Upload Photos"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                2),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Upload Photos"),
                        withParent(withParent(withId(R.id.tutorialConstraintLayout))),
                        isDisplayed()));
        textView2.check(matches(withText("Upload Photos")));

        ViewInteraction button4 = onView(
                allOf(withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutorialConstraintLayout),
                                        0),
                                2),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withText("Edit Profile"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                3),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("Edit Profile"),
                        withParent(withParent(withId(R.id.tutorialConstraintLayout))),
                        isDisplayed()));
        textView3.check(matches(withText("Edit Profile")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
