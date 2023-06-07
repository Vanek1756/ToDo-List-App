package com.example.todolistapp.app.ui.extensions

import android.content.Context
import android.content.res.Resources

@Suppress("unused")
fun Context.toPx(size: Int): Int = (size * Resources.getSystem().displayMetrics.density).toInt()

@Suppress("unused")
fun Context.toPx(size: Float): Float = size * Resources.getSystem().displayMetrics.density