package com.example.minerush.api.serverresponse

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val message: String, val name: String, val role:String, val status:Int)