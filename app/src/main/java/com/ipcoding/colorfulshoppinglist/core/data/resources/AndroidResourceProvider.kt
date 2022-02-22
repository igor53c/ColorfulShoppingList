package com.ipcoding.colorfulshoppinglist.core.data.resources

import android.content.Context
import androidx.annotation.StringRes
import com.ipcoding.colorfulshoppinglist.core.domain.resources.ResourceProvider

class AndroidResourceProvider(
    private val context: Context
) : ResourceProvider {

    override fun getString(@StringRes resourceIdentifier: Int): String {
        return context.resources.getString(resourceIdentifier)
    }
}