package com.example.recyclerviewjsonarray

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`

/* Assertion class to assert check the recylerview adapter for expected counts*/
class RecyclerViewItemAssertions :ViewAssertion {
    private var expectedCount = 0

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        val recyclerView = view as RecyclerView
        val adapter: RecyclerView.Adapter<*> = recyclerView.getAdapter() as RecyclerView.Adapter<*>
        assertThat(adapter.itemCount, `is`(expectedCount))
    }
    }


