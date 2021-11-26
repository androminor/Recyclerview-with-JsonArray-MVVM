package com.example.recyclerviewjsonarray

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.recyclerviewjsonarray.CustomMatchers.Companion.withItemCount
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NewsListRecyclerViewTest {
    //When the view gets attached to the recyclerview
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /* Recyclerview when all data is present*/
    @Test
    fun recyclerview_data_presence() {
             onView((withId(R.id.recyclerView)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    //Test the progressbar in the fragment
    @Test
    fun recyclerview_progressbar_exist() {
             onView(withId(R.id.progressBar))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
    //This is an error state failing test counting items in the recyclerview
    @Test
    fun recyclerview_fixed_number_counts() {
        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(14)))
    }

    //This test counts the items more then 2 & is passed
    @Test
    fun recyclerview_counting_items() {
        onView(withId(R.id.recyclerView)).check(RecyclerViewItemAssertions());
    }


}