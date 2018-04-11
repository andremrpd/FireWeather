package com.andredina.fireweather.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log

abstract class BaseFragment: Fragment(), BaseView {

    private val TAG: String?
        get() = this::class.simpleName

    protected var baseActivity: BaseActivity? = null
        private set

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }
    }

    override fun onDetach(){
        dismissLoading()
        baseActivity = null
        super.onDetach()
    }

    fun onError(throwable: Throwable){
        Log.e(TAG, throwable.localizedMessage, throwable)
        showErrorMsg(throwable.localizedMessage)
    }

    override fun showErrorMsg(msg: String) {
        baseActivity?.showErrorMsg(msg)
    }

    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun dismissLoading() {
        baseActivity?.dismissLoading()
    }


}