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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class FacebookEditProfileTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityTestRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void facebookEditProfileTest() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.FacebookIcon), withContentDescription("Facebook"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.EditProfileButton), withText("Edit Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.fbEditProfileText1), withText("1. Tap the \"Profile\" icon at the top or bottom of your screen, and tap:"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("1. Tap the \"Profile\" icon at the top or bottom of your screen, and tap:")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.fbEditProfile1), withContentDescription("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.facebookEditProfileText), withText("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(withText("Edit Profile")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.fbEditProfile2), withContentDescription("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.fbEditProfile3), withContentDescription("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.fbEditProfile4), withContentDescription("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.fbEditProfileText2), withText("2. Tap here and choose"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView3.check(matches(withText("2. Tap here and choose")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.fbEditProfileText5), withText("to change your profile picture."),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView4.check(matches(withText("to change your profile picture.")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.fbEditProfileText3), withText("3. Tap here and choose"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(withText("3. Tap here and choose")));

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.fbEditProfile5), withContentDescription("Edit Profile"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.fbEditProfileText6), withText("to change your cover picture."),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView6.check(matches(withText("to change your cover picture.")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.fbEditProfileText4), withText("4. Type your bio here."),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView7.check(matches(withText("4. Type your bio here.")));

        ViewInteraction button = onView(
                allOf(withId(R.id.backToFacebookButton3), withText("BACK"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
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
