package com.abrahambookstore.bookstore.controller

import com.abrahambookstore.bookstore.domain.User
import com.abrahambookstore.bookstore.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    // get all users
    @GetMapping("")
    fun getAllUsers():List<User> = userRepository.findAll()

    @PostMapping("create")
    fun createUser(@RequestBody user:User):ResponseEntity<User> {
        val savedUser = userRepository.save(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId:Int):ResponseEntity<User>{
        val user = userRepository.findById(userId).orElse(null)
        return if(user != null)
        {
            ResponseEntity(user, HttpStatus.OK)
        }
        else{
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") userId:Int, @RequestBody updatedUser:User):ResponseEntity<User>{
        val existingUser = userRepository.findById(userId).orElse(null)
        if(existingUser == null)
        {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val updatedUser = existingUser.copy(name = updatedUser.name, email = updatedUser.email)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId:Int):ResponseEntity<User>{
        var userToDelete = userRepository.existsById(userId)
        if(!userToDelete){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        userRepository.deleteById(userId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }
}