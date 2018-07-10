package com.hodinv.mvpdemo.screens.users


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.User
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UsersViewTest {

    val presenter = Mockito.mock(UsersContract.Presenter::class.java)
    val list1 = listOf(
            User(1, "User1", "email1@mail.com", "11111", 'A'),
            User(2, "User2", "email2@mail.com", "22222", 'B'),
            User(3, "User3", "email3@mail.com", "33333", 'C')
    )
    val list2 = listOf(
            User(4, "User4", "email4@mail.com", "44444", 'A'),
            User(3, "User3", "email3@mail.com", "33333", 'C')
    )
    val list3 = listOf(
            User(1, "User1", "email1@mail.com", "11111", 'A')
    )


    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<UsersActivity>(UsersActivity::class.java) {


        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            activity.presenter = presenter
        }
    }

    @Test
    fun checkNewOnFab() {
        onView(withId(R.id.addNewUser)).perform(click())
        Mockito.verify(presenter)?.addNewUser()
    }


    @Test
    fun testUsersAreVisible() {
        mActivityRule.activity.setUsers(list1)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        for (pos in 0..list1.size - 1) {
            onView(withText(list1[pos].name)).check(matches(isDisplayed()));
        }
    }

    @Test
    fun testUsersChanged() {
        mActivityRule.activity.setUsers(list1)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        mActivityRule.activity.setUsers(list2)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        for (pos in 0..list2.size - 1) {
            onView(withText(list2[pos].name)).check(matches(isDisplayed()));
        }
        mActivityRule.activity.setUsers(list3)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        for (pos in 0..list2.size - 1) {
            onView(withText(list2[pos].name)).check(doesNotExist());
        }
    }

    @Test
    fun testUsersClickGoesToDetail() {
        mActivityRule.activity.setUsers(list1)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        onView(allOf(withId(R.id.name), withText(list1[0].name))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.name), withText(list1[0].name))).perform(click())
        Mockito.verify(presenter)?.openUserDetail(list1[0])
    }

    @Test
    fun testSwipeRemovesUser() {
        mActivityRule.activity.setUsers(list1)
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        Thread.sleep(5000)
        onView(withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<UserViewHolder>(0, swipeLeft()));
        Mockito.verify(presenter)?.deleteUser(list1[0])
    }

}
