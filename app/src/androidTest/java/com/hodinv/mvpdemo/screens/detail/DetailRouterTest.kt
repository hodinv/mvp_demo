package com.hodinv.mvpdemo.screens.detail

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailRouterTest {
    val presenter = Mockito.mock(DetailContract.Presenter::class.java)

    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {

        override fun getActivityIntent(): Intent {
            return DetailActivity.newIntent(InstrumentationRegistry.getInstrumentation().targetContext)
        }

        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            activity.presenter = presenter
        }
    }


    @Test
    fun testGoBack() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.goBack()
        }
        Assert.assertEquals(true, mActivityRule.activity.isFinishing)
    }
}
