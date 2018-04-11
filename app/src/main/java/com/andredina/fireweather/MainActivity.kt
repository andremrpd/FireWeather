package com.andredina.fireweather

import android.Manifest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.andredina.fireweather.ui.base.BaseActivity
import com.andredina.fireweather.ui.weather.WeatherFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var rxPermissions: RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureDagger()

        rxPermissions  = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe{ granted ->
                    if (granted)  showFragment(savedInstanceState)
                    else showErrorMsg("No permission")
                }

    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun configureDagger() {
        AndroidInjection.inject(this)
    }

    private fun showFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {

            val fragment = WeatherFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, null)
                    .commit()
        }
    }

    override fun showLoading() {
        mainLayout.showProgressBar()
    }

    override fun dismissLoading() {
        mainLayout.dismissProgressBar()
    }

    override fun showErrorMsg(msg: String) {
        dismissLoading()
        Snackbar.make(mainLayout, msg, Snackbar.LENGTH_LONG).show()
    }

}
