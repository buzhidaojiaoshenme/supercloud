package com.example.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//	@Autowired
//	@Qualifier("redisTokenStore")
//	private TokenStore tokenStore;

//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources
//				.tokenStore(tokenStore);
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/sayHello").hasAuthority("hello")
				.anyRequest()
				.authenticated()
				.and()
				.requestMatchers()
				.antMatchers("/user/**");
	}
}