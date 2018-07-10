package com.hodinv.mvpdemo.screens.users

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.screens.detail.DetailActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest
class UsersRouterTest {

    val user = User(1, "User1", "email1@mail.com", "11111", 'A')

    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<UsersActivity>(UsersActivity::class.java) {
        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            activity.presenter = null
        }
    }

    @Test
    fun checkGoDetail() {
        Intents.init();
        mActivityRule.activity.openDetail(user)
        Intents.intended(allOf(
                hasComponent(DetailActivity::class.java.name),
                hasExtra(DetailActivity.EXTRA_USER_ID, user.id)))
        Intents.release()
    }

    @Test
    fun checkGoNewUser() {
        Intents.init();
        mActivityRule.activity.openNewUser()
        Intents.intended(allOf(
                hasComponent(DetailActivity::class.java.name),
                hasExtra(DetailActivity.EXTRA_USER_ID, DetailActivity.NEW_USER)))
        Intents.release()
    }

}