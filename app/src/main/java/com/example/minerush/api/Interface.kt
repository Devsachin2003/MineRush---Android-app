package com.example.minerush.api

import com.android.lawbot.DataClasses.ChatRequest
import com.android.lawbot.DataClasses.ChatResponse
import com.example.minerush.DataClass.RulesResponse
import com.example.minerush.User.user_ui.user_profile.UserMyProfileFragment
import com.example.minerush.api.serverresponse.LoginResponse
import com.example.minerush.api.serverresponse.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // ✅ Bot chat endpoint
    @Headers("Content-Type: application/json")
    @POST("/chat")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>

    // ✅ Login endpoint
    @FormUrlEncoded
    @POST("Login")
    fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    // ✅ Register endpoint
    @FormUrlEncoded
    @POST("Register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("username") username: String,
        @Field("organization") organization: String,
        @Field("password") password: String,
        @Field("confirmpassword") confirmpassword: String,
        @Field("role") role: String
    ): Call<SignUpResponse>

    // ✅ Get user profile (you can update return type if not correct)
    @GET("getUserProfile")
    suspend fun getUserProfile(): UserMyProfileFragment

    @GET("/{id}")
    fun getRule(@Path("id") id: String): Call<RulesResponse>
}
