package com.familyservicestoronto.shortcut;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GmailActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void gmailActivityTest1() {
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
                                2),
                        1),
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
                allOf(withText("Add an Account"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                1),
                        isDisplayed()));
        button.perform(click());

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
                allOf(withText("Open Email"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                2),
                        isDisplayed()));
        button3.perform(click());

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
                allOf(withText("Compose Email"),
                        childAtPosition(
                                allOf(withId(R.id.tutorialLinearLayout),
                                        childAtPosition(
                                                withId(R.id.tutorialConstraint),
                                                0)),
                                3),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction button6 = onView(
                allOf(withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutorialConstraintLayout),
                                        0),
                                2),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction button7 = onView(
                allOf(withText("Go to Gmail"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutorialLinearLayout),
                                        4),
                                1),
                        isDisplayed()));
        button7.perform(click());
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
