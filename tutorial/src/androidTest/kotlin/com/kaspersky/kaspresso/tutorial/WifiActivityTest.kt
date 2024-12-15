package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiActivityScreen
import org.junit.Rule
import org.junit.Test

class WifiActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun wifiTest() {
        MainScreen {
            wifiActivityButton {
                isVisible()
                isClickable()
                containsText("INTERNET AVAILABILITY")
                click()
            }
        }

        WifiActivityScreen {
            checkWifiButton {
                isVisible()
                isClickable()
                containsText("CHECK WIFI STATUS")
            }
            wifiStatus.hasEmptyText()

            device.network.toggleWiFi(true)
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)

            checkWifiButton.click()
            wifiStatus.hasText(R.string.enabled_status)

            device.network.toggleWiFi(false)
            device.exploit.rotate()

            checkWifiButton.click()
            wifiStatus.hasText(R.string.disabled_status)
        }
    }
}
