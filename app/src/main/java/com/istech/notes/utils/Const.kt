package com.istech.notes.utils

import android.content.Context
import android.widget.Toast

object Const {
    const val type = "Type"
    const val data = "data"
    const val edit = "edit"
    const val add = "add"

    fun toast(tag: String, context: Context) {
        Toast.makeText(context, tag, Toast.LENGTH_SHORT).show()
    }
}