package com.gaur.mviexampleyt.data.repository

import com.gaur.mviexampleyt.data.api.APIService

class GetUserRepository (val apiService: APIService) {

    suspend fun getPosts() = apiService.getPosts()


}