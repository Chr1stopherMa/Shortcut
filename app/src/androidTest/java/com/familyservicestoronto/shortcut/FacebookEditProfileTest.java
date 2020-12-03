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
import org.hamcrest.core.IsInstanceOf;
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
public class FacebookEditProfileTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityTestRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void facebookEditProfileTest() {
        ViewInteraction imageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0),
                        0),
                        isDisplayed()));
        imageView.perform(click());

        ViewInteraction button = onView(
                allOf(withText("Edit Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutorialConstraint),
                                        0),
                                3),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Edit Profile"),
                        withParent(withParent(withId(R.id.tutorialConstraintLayout))),
                        isDisplayed()));
        textView.check(matches(withText("Edit Profile")));

        ViewInteraction textView2 = onView(
                allOf(withText("1. Tap the \"Profile\" icon at the top or bottom of your screen."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView2.check(matches(withText("1. Tap the \"Profile\" icon at the top or bottom of your screen.")));

        ViewInteraction imageView2 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("2. Tap:"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView3.check(matches(withText("2. Tap:")));

        ViewInteraction imageView3 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withText("3. Tap here to change your profile picture."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView4.check(matches(withText("3. Tap here to change your profile picture.")));

        ViewInteraction imageView4 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withText("4. Tap here to change your cover picture."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView5.check(matches(withText("4. Tap here to change your cover picture.")));

        ViewInteraction imageView5 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withText("5. Type your bio here."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView6.check(matches(withText("5. Type your bio here.")));

        ViewInteraction imageView6 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withText("BACK"),
                        withParent(withParent(withId(R.id.tutorialConstraintLayout))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
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
