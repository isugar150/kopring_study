package com.example.study1.component.security

import com.example.study1.dto.users.KakaoUserInfo
import com.example.study1.dto.users.MemberRole
import com.example.study1.dto.users.PrincipalDetail
import com.example.study1.dto.users.UsersDto
import com.example.study1.repository.UsersRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
@Transactional
class OAuth2UserService : DefaultOAuth2UserService() {
    private val usersRepository: UsersRepository? = null
    val logger = Logger.getLogger(OAuth2UserService::class.java.name)

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        logger.info("--------------------------- OAuth2UserService ---------------------------")

        val oAuth2User = super.loadUser(userRequest)
        val attributes = oAuth2User.attributes

        logger.info("OAuth2USer = $oAuth2User")
        logger.info("attributes = $attributes")

        // nameAttributeKey
        val userNameAttributeName = userRequest.clientRegistration
            .providerDetails
            .userInfoEndpoint
            .userNameAttributeName
        logger.info("nameAttributeKey = $userNameAttributeName")

        val kakaoUserInfo: KakaoUserInfo = KakaoUserInfo(attributes)
        val socialId: String = kakaoUserInfo.socialId
        val name: String = kakaoUserInfo.name

        // 소셜 ID 로 사용자를 조회, 없으면 socialId 와 이름으로 사용자 생성
//        val bySocialId: UsersDto = usersRepository.findByUserId(socialId)
//        val member: UsersDto = bySocialId.orElseGet { saveSocialMember(socialId, name) }

//        return PrincipalDetail(
//            member, Collections.singleton(SimpleGrantedAuthority(member.getRole().getValue())),
//            attributes
//        )
        return PrincipalDetail(null, null, null)
    }

    // 소셜 ID 로 가입된 사용자가 없으면 새로운 사용자를 만들어 저장한다
    fun saveSocialMember(socialId: String?, name: String?): Member {
        logger.info("--------------------------- saveSocialMember ---------------------------")
//        val newMember: UsersDto = UsersDto.builder().socialId(socialId).name(name).role(MemberRole.USER).build()
//        return usersRepository.save(newMember)
        return null
    }
}