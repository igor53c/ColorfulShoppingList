package com.ipcoding.colorfulshoppinglist.core.domain.resources

interface ResourceProvider {

    fun getString(resourceIdentifier: Int): String
}