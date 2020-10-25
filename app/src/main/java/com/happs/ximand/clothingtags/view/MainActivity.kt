package com.happs.ximand.clothingtags.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            closeApplication()
        } else {
            super.onBackPressed()
        }
    }

    private fun closeApplication() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            finishAffinity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentNavigation.initialize(supportFragmentManager)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )
        FragmentNavigation.getInstance()
            .navigateToNewFragment(AllClothingTagsFragment.newInstance())

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

}