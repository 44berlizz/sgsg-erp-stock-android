package com.slogup.sgsgerpstock.scene


import android.app.ProgressDialog
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by jessehj on 22/03/2019.
 */

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onDestroy() {
        stopProgressDialog()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun configToolbar(toolbar: Toolbar, homeButtonEnabled: Boolean = true, titleEnabled: Boolean = false) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(homeButtonEnabled)
            setDisplayHomeAsUpEnabled(homeButtonEnabled)
            setDisplayShowTitleEnabled(titleEnabled)
        }
    }

    fun showProgressDialog() {
        if (!::progressDialog.isInitialized) {
            progressDialog = indeterminateProgressDialog("Fetching the data...") {
                setCancelable(false)
            }
        }
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    fun stopProgressDialog() {
        if (::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }
}