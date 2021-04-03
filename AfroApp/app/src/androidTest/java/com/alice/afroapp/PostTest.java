package com.alice.afroapp;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class PostTest {
    @Rule
   public ActivityTestRule<Post> mActivityTestRule =
            new ActivityTestRule<>(Post.class);
    String postQuestion = "what is a class";

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void testScenario(){
        //input text in the textbox
        Espresso.onView(withId(R.id.editQuestion)).perform(typeText(postQuestion));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //click send button
        Espresso.onView(withId(R.id.postButton)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }
}