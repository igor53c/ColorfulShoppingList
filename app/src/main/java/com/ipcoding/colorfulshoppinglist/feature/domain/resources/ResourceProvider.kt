package com.ipcoding.colorfulshoppinglist.feature.domain.resources

interface ResourceProvider {

    fun getString(resourceIdentifier: Int): String
}