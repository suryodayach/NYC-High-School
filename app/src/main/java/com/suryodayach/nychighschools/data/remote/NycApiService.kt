package com.suryodayach.nychighschools.data.remote

import com.suryodayach.nychighschools.data.model.HighSchool
import retrofit2.http.GET
import retrofit2.http.Query

interface NycApiService {

    @GET("s3k6-pzi2.json")
    suspend fun getHighSchools(): List<HighSchool>

    @GET("s3k6-pzi2.json")
    suspend fun getHighSchool(@Query("dbn") dbn: String): List<HighSchool>
}