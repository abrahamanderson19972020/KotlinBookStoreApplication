package com.abrahambookstore.bookstore.repository

import com.abrahambookstore.bookstore.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface UserRepository:JpaRepository<User, Int> {
}