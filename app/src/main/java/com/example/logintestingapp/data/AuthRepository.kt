package com.example.logintestingapp.data
interface AuthRepository {
    fun login(username: String, password: String): Boolean
}

class FakeAuthRepository : AuthRepository {
    override fun login(username: String, password: String): Boolean {
        return username == "admin" && password == "123456"
    }
}
