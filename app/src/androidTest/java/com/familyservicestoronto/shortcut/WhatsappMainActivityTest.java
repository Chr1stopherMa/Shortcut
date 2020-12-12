package com.familyservicestoronto.shortcut;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
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
public class WhatsappMainActivityTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityTestRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void whatsappMainActivityTest() {
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

        ViewInteraction textView = onView(
                allOf(withText("WhatsApp"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("WhatsApp")));

        ViewInteraction imageView3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2),
                        0),
                        isDisplayed()));
        imageView3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("WhatsApp"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        textView2.check(matches(withText("WhatsApp")));

        ViewInteraction button = onView(
                allOf(withText("ADD NEW CONTACT/GROUP"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withText("UPLOAD PHOTOS"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withText("EDIT PROFILE"),
                        withParent(allOf(withId(R.id.tutorialLinearLayout),
                                withParent(withId(R.id.tutorialConstraint)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withText("BACK"),
                        withParent(withParent(withId(R.id.tutorialLinearLayout))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withText("GO TO WHATSAPP"),
                        withParent(withParent(withId(R.id.tutorialLinearLayout))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));
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
