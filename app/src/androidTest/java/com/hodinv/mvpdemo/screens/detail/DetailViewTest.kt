package com.hodinv.mvpdemo.screens.detail

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hodinv.mvpdemo.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailViewTest {
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
    fun testInitialStateNoErrors() {
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(not(isDisplayed())))
        onView(ViewMatchers.withId(R.id.errorName)).check(matches(not(isDisplayed())))
        onView(ViewMatchers.withId(R.id.errorPhone)).check(matches(not(isDisplayed())))
        onView(ViewMatchers.withId(R.id.errorGrade)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testClickDone() {
        onView(ViewMatchers.withId(R.id.commit)).perform(click())
        Mockito.verify(presenter).save()
    }

    @Test
    fun testEnterName() {
        val text = "Text"
        onView(ViewMatchers.withId(R.id.name)).perform(typeText(text))
        Espresso.closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.commit)).perform(click())
        Mockito.verify(presenter).setName(text)
    }

    @Test
    fun testEnterEmail() {
        val text = "Text"
        onView(ViewMatchers.withId(R.id.email)).perform(typeText(text))
        Espresso.closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.commit)).perform(click())
        Mockito.verify(presenter).setEmail(text)
    }

    @Test
    fun testEnterPhone() {
        val text = "Text"
        onView(ViewMatchers.withId(R.id.phone)).perform(typeText(text))
        Espresso.closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.commit)).perform(click())
        Mockito.verify(presenter).setPhone(text)
    }

    @Test
    fun testEnterGrade() {
        onView(ViewMatchers.withId(R.id.gradeA)).perform(click())
        Mockito.verify(presenter).setGrade('A')
        onView(ViewMatchers.withId(R.id.gradeB)).perform(click())
        Mockito.verify(presenter).setGrade('B')
        onView(ViewMatchers.withId(R.id.gradeC)).perform(click())
        Mockito.verify(presenter).setGrade('C')
        onView(ViewMatchers.withId(R.id.gradeD)).perform(click())
        Mockito.verify(presenter).setGrade('D')
        onView(ViewMatchers.withId(R.id.gradeE)).perform(click())
        Mockito.verify(presenter).setGrade('E')
    }

    @Test
    fun testGradeClearPrevGrade() {
        onView(ViewMatchers.withId(R.id.gradeA)).perform(click())
        onView(ViewMatchers.withId(R.id.gradeB)).perform(click())
        onView(ViewMatchers.withId(R.id.gradeA)).check(matches(isNotChecked()))
        onView(ViewMatchers.withId(R.id.gradeB)).check(matches(isChecked()))
    }

    @Test
    fun testShowNoName() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Name, DetailContract.View.ErrorType.Empty)
        }
        onView(ViewMatchers.withId(R.id.errorName)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorName)).check(matches(withText(R.string.error_required_name)))
    }

    @Test
    fun testNoErrorForName() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Name, DetailContract.View.ErrorType.Empty)
            mActivityRule.activity.clearError(DetailContract.View.Fields.Name)
        }
        onView(ViewMatchers.withId(R.id.errorName)).check(matches(not(isDisplayed())))
    }


    @Test
    fun testShowNoEmail() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Email, DetailContract.View.ErrorType.Empty)
        }
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(withText(R.string.error_required_email)))
    }

    @Test
    fun testShowWrongEmail() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Email, DetailContract.View.ErrorType.Invalid)
        }
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(withText(R.string.error_wrong_email)))

    }

    @Test
    fun testNoErrorForEmail() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Email, DetailContract.View.ErrorType.Empty)
            mActivityRule.activity.clearError(DetailContract.View.Fields.Email)
        }
        onView(ViewMatchers.withId(R.id.errorEmail)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testShowNoPhone() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Phone, DetailContract.View.ErrorType.Empty)
        }
        onView(ViewMatchers.withId(R.id.errorPhone)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorPhone)).check(matches(withText(R.string.error_required_phone)))
    }

    @Test
    fun testNoErrorForPhone() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Phone, DetailContract.View.ErrorType.Empty)
            mActivityRule.activity.clearError(DetailContract.View.Fields.Phone)
        }
        onView(ViewMatchers.withId(R.id.errorPhone)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testShowNoGrade() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Grade, DetailContract.View.ErrorType.Empty)
        }
        onView(ViewMatchers.withId(R.id.errorGrade)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorGrade)).check(matches(withText(R.string.error_required_grade)))

    }

    @Test
    fun testNoErrorForGrade() {
        UiThreadStatement.runOnUiThread {
            mActivityRule.activity.setError(DetailContract.View.Fields.Grade, DetailContract.View.ErrorType.Empty)
            mActivityRule.activity.clearError(DetailContract.View.Fields.Grade)
        }
        onView(ViewMatchers.withId(R.id.errorGrade)).check(matches(not(isDisplayed())))
    }


}