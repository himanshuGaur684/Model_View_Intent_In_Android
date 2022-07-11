package com.gaur.mviexampleyt.data.api

import com.gaur.mviexampleyt.data.model.FakeDTO
import retrofit2.http.GET

interface APIService {

    @GET("posts")
    suspend fun getPosts():List<FakeDTO>

}