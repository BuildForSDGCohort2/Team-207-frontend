package com.alice.afroapp;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class EditActivityTest {
    @Rule
    ActivityTestRule<EditActivity> mActivityTestRule = new ActivityTestRule<>
            (EditActivity.class);
    private String fullname = "Alice N Musukwa";
    private String proficiency ="Android/java";
    private String location = "Zomba";
    private String email = "am@gmail.com";

    @Before
    public void setUp() throws Exception {
    }
    @Test
     public void testCaseScenarios(){
        //input text in the textbox
        Espresso.onView(withId(R.id.editProf)).perform(typeText(fullname));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //input text in the textbox
        Espresso.onView(withId(R.id.editProficiency)).perform(typeText(proficiency));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //input text
        Espresso.onView(withId(R.id.editLoc)).perform(typeText(location));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //input text
        Espresso.onView(withId(R.id.editt)).perform(typeText(email));
        //close keyboard
        Espresso.closeSoftKeyboard();



    }

    @After
    public void tearDown() throws Exception {
    }
}