package com.kaspersky.kaspresso.tutorial

import android.content.res.Configuration
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WifiActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun wifiTest() {
        before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.run {
            step("Open target screen") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        containsText("INTERNET AVAILABILITY")
                        click()
                    }
                }
            }
            step("Check WIFI status button") {
                WifiActivityScreen {
                    checkWifiButton {
                        isVisible()
                        isClickable()
                        containsText("CHECK WIFI STATUS")
                    }
                    wifiStatus.hasEmptyText()
                }
            }
            step("Check WIFI status is enabled") {
                WifiActivityScreen {
                    checkWifiButton.click()
                    wifiStatus.hasText(R.string.enabled_status)
                }
            }
            step("Disable WIFI and rotate device") {
                device.network.toggleWiFi(false)
                device.exploit.rotate()
                Assert.assertTrue(
                    device.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                )
            }
            step("Check WIFI status is disabled") {
                WifiActivityScreen {
                    checkWifiButton.click()
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
        }
    }
}
