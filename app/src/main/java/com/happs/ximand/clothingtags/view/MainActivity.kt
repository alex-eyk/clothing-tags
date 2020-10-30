package com.happs.ximand.clothingtags.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
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
        requestPermissions()
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            1
        )
    }

    //TODO
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            @Suppress("ControlFlowWithEmptyBody")
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
            } else {
                Snackbar.make(
                    findViewById(R.id.container),
                    "Нет разрешения на доступ к памяти. Из-за этого вы не сможете загружать изображения одежды. Измените это в настройках",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }

    }
}