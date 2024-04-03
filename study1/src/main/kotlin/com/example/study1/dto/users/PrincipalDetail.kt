package com.example.study1.dto.users

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User


data class PrincipalDetail (
    var user: UsersDto,
    var authorities: Collection<GrantedAuthority?>,

    var attributes: Map<String, Any>?
) : UserDetails, OAuth2User {

    val memberInfo: Map<String, Any>
        // info 에 들어가는 것들이 토큰에 들어가는 데이터
        get() {
            val info: MutableMap<String, Any> = HashMap()
            info["name"] = user.name!!
            info["email"] = user.email!!
            info["role"] = user.authorities
            return info
        }

    override fun getName(): String {
        return user.userId!!
    }

    override fun getAttributes(): Map<String, Any> {
        return attributes!!
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.name!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}