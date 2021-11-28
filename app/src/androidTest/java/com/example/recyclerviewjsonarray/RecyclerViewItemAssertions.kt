package com.example.recyclerviewjsonarray

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher

/* Assertion class to assert check the recylerview adapter for expected counts*/
class RecyclerViewItemAssertions(matcher: Matcher<Int>) :ViewAssertion {
    private var matcher: Matcher<Int>? = null

    fun withItemCount(expectedCount: Int) {
        return withItemCount(`is`(expectedCount))
    }

    fun withItemCount(matcher: Matcher<Int>) {
        return RecyclerViewItemAssertions(matcher)
    }

    private fun RecyclerViewItemAssertions(matcher: Matcher<Int>) {
        this.matcher = matcher
    }
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }
    }


