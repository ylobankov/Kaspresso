package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object WifiActivityScreen : KScreen<WifiActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val checkWifiButton = KButton { withId(R.id.check_wifi_btn) }
    val wifiStatus = KTextView { withId(R.id.wifi_status) }
}
