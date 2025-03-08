package com.achievesoft.sunday.repositories

import com.achievesoft.sunday.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailAndPassword(email: String, password: String): User?
}
