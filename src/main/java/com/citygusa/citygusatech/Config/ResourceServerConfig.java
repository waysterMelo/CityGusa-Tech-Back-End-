package com.citygusa.citygusatech.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    JwtTokenStore jwtTokenStore;

    @Autowired
    private Environment environment;

    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**", "/users/register",
            "/roles/inserir", "/datas"};
    private static final String[] OPERATOR_OR_ADMIN = {"/forno/**", "/analise/**", "/gusa/**", "/carvao/**", "/users/**"};
    private static final String[] ADMIN = {"/roles/**}"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      //Define o armazenamento de tokens JWT que será usado pelo servidor de recursos.
       resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
        * O código desabilita uma proteção de segurança específica no ambiente
        *  de teste para facilitar o uso do console H2.
        * */
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        /*
        * O código estabelece diferentes níveis de acesso:
        URLs públicas podem ser acessadas por qualquer pessoa.
        URLs de consulta (GET) para recursos de operador ou administrador também podem ser acessadas sem autenticação.
        URLs específicas de administrador exigem o papel "ADMIN".
        Todas as outras URLs exigem que o usuário esteja autenticado.
        *
        * */
        http.authorizeRequests().antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR","ADMIN")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, PUBLIC).permitAll()
                .anyRequest().authenticated();
    }



}
