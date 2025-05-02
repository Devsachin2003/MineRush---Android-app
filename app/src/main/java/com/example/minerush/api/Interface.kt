package com.example.minerush.api

import com.android.lawbot.DataClasses.ChatRequest
import com.android.lawbot.DataClasses.ChatResponse
import com.example.minerush.DataClass.AdminProfileResponse
import com.example.minerush.DataClass.ExploreInternData
import com.example.minerush.DataClass.FaqsResponse
import com.example.minerush.DataClass.GlossaryResponse
import com.example.minerush.DataClass.InternApplicantResponse
import com.example.minerush.DataClass.InternshipResponse
import com.example.minerush.DataClass.ManageData
import com.example.minerush.DataClass.ManageUsersResponse
import com.example.minerush.DataClass.RecentUsersResponse
import com.example.minerush.DataClass.RulesResponse
import com.example.minerush.DataClass.UserMyProfileData
import com.example.minerush.User.user_ui.user_profile.UserMyProfileFragment
import com.example.minerush.api.serverresponse.ApiResponse
import com.example.minerush.api.serverresponse.CommonResponse
import com.example.minerush.api.serverresponse.LoginResponse
import com.example.minerush.api.serverresponse.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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
    @GET("Myprofile")
    fun getUserProfile(@Query("email") email: String): Call<UserMyProfileData>

    @GET("{id}")
    fun getRule(@Path("id") id: String): Call<RulesResponse>

    @GET("Explore_internships")
    fun getInternships(): Call<InternshipResponse>

    @GET("/FAQs")
    fun getFaqs(): Call<FaqsResponse>

    @GET("Displayglossary")
    fun getGlossary(): Call<GlossaryResponse>

    @Multipart
    @POST("/submit_application")
    fun submitApplication(
        @Part("name") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("dob") dob: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("degree_branch") branch: RequestBody,
        @Part("year_of_study") year: RequestBody,
        @Part("company_name") company: RequestBody,
        @Part("skills") skills: RequestBody,
        @Part resume: MultipartBody.Part
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("/Add_faq")
    fun addFaq(
        @Field("section") section: String,
        @Field("question") question: String,
        @Field("answer") answer: String
    ): Call<Map<String, Any>>

    @FormUrlEncoded
    @POST("/Add_Glossary")
    fun addGlossary(
        @Header("Authorization") authorization: String,
        @Field("term") term: String,
        @Field("definition") definition: String
    ): Call<Map<String, Any>>

    @FormUrlEncoded
    @POST("/Add_intern_from_adminside")
    fun addInternship(
        @Header("Authorization") email: String,
        @Field("title") title: String,
        @Field("company") company: String,
        @Field("location") location: String,
        @Field("duration") duration: String,
        @Field("description") description: String,
        @Field("requirements") requirements: String,
        @Field("stipend") stipend: String,
        @Field("application_process") applicationProcess: String,
        @Field("learning_outcomes") learningOutcomes: String,
        @Field("contact_info") contactInfo: String,
        @Field("mode") mode: String
    ): Call<Map<String, Any>>

    @GET("Adminmyprofile")
    fun getAdminProfile(@Query("email") email: String): Call<AdminProfileResponse>


    @GET("/View_recent_registered_user") // Ensure this matches your Flask route
    fun getRecentUsers(): Call<RecentUsersResponse>

    @GET("/View_recent_intern_applicant")
    fun getInternApplicantDetails(): Call<InternApplicantResponse>

    @GET("/Manage_users")
    fun getUsers(): Call<ManageUsersResponse>

    @POST("delete_user/{email}")
    fun deleteUser(@Path("email") email: String): Call<CommonResponse>

    @GET("Admineditprofile")
    fun getAdminDetails(
        @Query("email") email: String
    ): Call<AdminProfileResponse>

    @Multipart
    @POST("Admineditprofile")
    fun updateAdminProfile(
        @Part("email") email: RequestBody,
        @Part("name") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part picture: MultipartBody.Part? = null
    ): Call<AdminProfileResponse>



}
