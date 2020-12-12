package com.familyservicestoronto.shortcut;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class GoogleActivityTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityTestRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void googleActivityTest() {
        ViewInteraction imageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0),
                        1),
                        isDisplayed()));
        imageView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.tutorialLinearLayout),
                        withParent(allOf(withId(R.id.tutorialConstraint),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Google"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        textView.check(matches(withText("Google")));

        ViewInteraction button = onView(
                allOf(withText("PERFORM SEARCH"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withText("OPEN NEW TAB"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withText("BACK"),
                        withParent(withParent(withId(R.id.tutorialLinearLayout))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withText("GO TO GOOGLE"),
                        withParent(withParent(withId(R.id.tutorialLinearLayout))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));
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
