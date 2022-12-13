package com.kaspersky.kaspresso.kautomatorsample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.databinding.ActivityFlakyBinding

class FlakyActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_DELAY = 2_000L
        private const val SECOND_DELAY = 15_000L
    }

    private lateinit var binding: ActivityFlakyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flaky)

        binding = ActivityFlakyBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startAsync(FIRST_DELAY, SECOND_DELAY)
    }

    @Suppress("SameParameterValue")
    private fun startAsync(firstDelayMs: Long, secondDelayMs: Long) {
        Handler(mainLooper).apply {
            postDelayed(
                { binding.flakyButton1.text = getString(R.string.menu_item_2) },
                firstDelayMs
            )
        }
    }
}
