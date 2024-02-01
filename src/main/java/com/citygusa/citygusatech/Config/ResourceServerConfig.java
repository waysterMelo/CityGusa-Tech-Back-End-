package com.citygusa.citygusatech.Config;

import com.citygusa.citygusatech.Component.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableReactiveMethodSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    JwtTokenStore jwtTokenStore;

    @Autowired
    private Environment environment;
    

//
//    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
//    private static final String[] OPERATOR_OR_ADMIN = {"/products/**", "/categories/**"};
//    private static final String[] ADMIN = {"/users/**"};
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(jwtTokenStore);}
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        //h2
//        if (Arrays.asList(environment.getActiveProfiles()).contains("test")){
//            http.headers().frameOptions().disable();
//        }
//
//        http.authorizeRequests()
//                .antMatchers(PUBLIC).permitAll()
//                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()
//                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR","ADMIN")
//                .antMatchers(ADMIN).hasRole("ADMIN")
//                .anyRequest().authenticated();
//    }


}
