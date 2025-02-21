package com.abrahambookstore.bookstore.domain

import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    val name:String,
    val email:String
)