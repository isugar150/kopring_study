package com.example.study1.component.security

import com.example.study1.component.ExceptionAdvice
import com.example.study1.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    val logger = Logger.getLogger(UserDetailsServiceImpl::class.java.name)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        logger.info("--------------------------- UserDetailsServiceImpl ---------------------------")
        logger.info("username = $username")

        return usersRepository.findByUserId(username)?.let { obj -> obj.toEntity() }
            ?: run { throw UsernameNotFoundException("등록되지 않은 사용자입니다") }
    }
}