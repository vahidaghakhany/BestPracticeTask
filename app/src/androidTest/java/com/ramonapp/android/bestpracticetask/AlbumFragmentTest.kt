package com.ramonapp.android.bestpracticetask

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramonapp.android.bestpracticetask.presentation.MainActivity
import com.ramonapp.android.bestpracticetask.presentation.albums.AlbumAdapter
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumFragmentTest {


    @Before
    fun setup(){
        launch(MainActivity::class.java)
    }

    @Test
    fun test_get_data_from_network_and_show_on_recyclerView() {

        onView(withId(R.id.album_progress)).check(matches(isDisplayed()))
        onView(withId(R.id.album_recycler)).check(matches(isDisplayed()))
        SystemClock.sleep(5000)
        onView(withId(R.id.album_progress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.album_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.ViewHolder>(
                99,
                click()
            )
        )
    }

}