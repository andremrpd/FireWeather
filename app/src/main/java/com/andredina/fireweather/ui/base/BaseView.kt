package com.andredina.fireweather.ui.base

interface BaseView {

    fun showLoading()
    fun dismissLoading()
    fun showErrorMsg(msg: String)

}