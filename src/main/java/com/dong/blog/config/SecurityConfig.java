package com.dong.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dong.blog.config.auth.PrincipalDetailService;
import com.dong.blog.config.oauth.principalOauthUserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private principalOauthUserService principalOauthUserService;
	
	@Autowired
	private PrincipalDetailService principalDetailService;

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


	@Bean
	public BCryptPasswordEncoder encodePWD() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("==============SecurityConfig::configure");
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable() //csrf 토큰 비활성화(테스트할 때 걸어두는게좋음)
				.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/test/**","/check/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/")
			.and()
				.oauth2Login()
				.loginPage("/auth/loginForm")
				.userInfoEndpoint()
				.userService(principalOauthUserService);
				
				
				
 


	}
	
}
