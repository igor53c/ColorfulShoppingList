package com.ipcoding.colorfulshoppinglist.core.data.remote

import com.ipcoding.colorfulshoppinglist.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpoonacularApi {

    /*@GET("teams/{id}")
    suspend fun getTeamInfo(
        @Header("X-Auth-Token") token: String = BuildConfig.API_KEY,
        @Path("id") id: String
    ): TeamDto

    @GET("competitions/{id}/teams")
    suspend fun getTeamList(
        @Header("X-Auth-Token") token: String = BuildConfig.API_KEY,
        @Path("id") id: String
    ): TeamListDto

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
    }*/
}