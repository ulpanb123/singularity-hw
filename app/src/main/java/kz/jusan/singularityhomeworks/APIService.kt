package kz.jusan.singularityhomeworks

import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("users")
    suspend fun getUsers(): List<User>
}