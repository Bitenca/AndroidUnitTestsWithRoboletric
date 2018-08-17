package com.example.feliperezende.testeteste

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.TextView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import org.robolectric.Shadows
import android.widget.Button
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.*


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest {

    lateinit var activity: MainActivity
    lateinit var textView: View

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(MainActivity::class.java)
        textView = activity.findViewById<TextView>(R.id.hello_textview)
    }

    @Test
    fun shouldHaveDefaultMarginBot() {

        var bottomMargin = (textView.layoutParams as ConstraintLayout.LayoutParams).bottomMargin
        assertEquals(5, bottomMargin)
    }

    @Test
    fun shouldHaveDefaultMarginRight() {
        var rightMargin = (textView.layoutParams as ConstraintLayout.LayoutParams).rightMargin
        assertEquals(10, rightMargin)
    }

    @Test
    fun shouldHaveDefaultMarginLeft() {
        var leftMargin = (textView.layoutParams as ConstraintLayout.LayoutParams).leftMargin
        assertEquals(10, leftMargin)
    }

    @Test
    fun shouldHaveDefaultMarginTop() {
        var topMargin = (textView.layoutParams as ConstraintLayout.LayoutParams).topMargin
        assertEquals(5, topMargin)
    }

    @Test
    @Throws(Exception::class)
    fun checkActivityNotNull() {
        assertNotNull(activity)
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveCorrectAppName() {
        val hello = activity.resources.getString(R.string.app_name)
        assertThat(hello, equalTo("Testeteste"))
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartNewActivity() {
        val button = activity.findViewById(R.id.startNextActivity) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity).peekNextStartedActivity()
        assertEquals(SecondActivity::class.java.canonicalName, intent.component!!.className)
    }

    @Test
    @Throws(Exception::class)
    fun testButtonClickShouldShowToast() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        val view = activity.findViewById(R.id.showToast) as Button
        assertNotNull(view)
        view.performClick()
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Lala"))
    }

}