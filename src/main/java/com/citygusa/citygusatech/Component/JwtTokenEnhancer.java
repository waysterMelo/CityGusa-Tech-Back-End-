package com.citygusa.citygusatech.Component;

import com.citygusa.citygusatech.Entity.Users;
import com.citygusa.citygusatech.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {


    @Autowired
    private UserRepository userRepository;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Users users = userRepository.findByEmail(authentication.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("userFirstName", users.getUsername());
        map.put("userId", users.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);
        return accessToken;
    }
}
