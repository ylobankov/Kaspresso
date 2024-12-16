package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.AfterLoginActivityScreen
import org.junit.Rule
import org.junit.Test

class AfterLoginActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun titlePresentAfterLogin() {
        run {
            step("Open AfterLogin screen") {
                scenario(
                    LoginActivityScenario(
                        username = "123456",
                        password = "123456"
                    )
                )
            }
            step("Check title") {
                AfterLoginActivityScreen {
                    title {
                        isVisible()
                        hasText(R.string.screen_after_login)
                    }
                }
            }
        }
    }
}
