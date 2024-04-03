package com.example.study1.dto.users

data class KakaoUserInfo(val attributes: Map<String?, Any>) {
    init {
        Companion.socialId = attributes["id"].toString()
        account = attributes["kakao_account"] as Map<String, Any>?
        profile = account!!["profile"] as Map<String, Any>?
    }

    val socialId: String
        get() = Companion.socialId

    val name: String
        get() = profile!!["nickname"].toString()

    companion object {
        var socialId: String = ""
        var account: Map<String, Any>? = null
        var profile: Map<String, Any>? = null
    }
}