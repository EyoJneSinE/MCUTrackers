package com.eniskaner.mcutrackers.util

import android.view.View

fun interface NavigateCallBack {
    fun navigate(view: View, title: String)
}