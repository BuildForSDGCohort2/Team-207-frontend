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

public class AddProfTest {
    @Rule
   public ActivityTestRule<AddProf> mActivityTestRule = new ActivityTestRule<>
            (AddProf.class);
   private String fullname = "Alison Musukwa";
   private String proficiency ="Android";
   private String location = "Zomba";
   private String email = "am@gmail.com";

    @Before
    public void setUp() throws Exception {
    }

    @Test

     public void testUserScenario(){
        //input text in the textbox
        Espresso.onView(withId(R.id.editName)).perform(typeText(fullname));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //input text in the textbox
        Espresso.onView(withId(R.id.editProf)).perform(typeText(proficiency));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //input text in the textbox
        Espresso.onView(withId(R.id.editLoc)).perform(typeText(location));
        //close keyboard
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editt)).perform(typeText(email));
        //close keyboard
        Espresso.closeSoftKeyboard();
        //click send button
        Espresso.onView(withId(R.id.fab)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }
}