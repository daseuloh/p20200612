package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration   //환경설정 파일
@EnableWebSecurity //Security사용
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//새롭게 만든 UserDetailsService를 사용함
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder BCE() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//새로 만든 service객체 추가 + 암호화 방법 설정
		auth.userDetailsService(userDetailsService).passwordEncoder( BCE() );
	}
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		//security가 적용되지 않는 url
		web.ignoring().antMatchers("/css/**", "/script/**", 
				"/image/**", "/fonts/**", "/lib/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
	        .antMatchers("/security/admin", "/security/admin/*").hasAuthority("ADMIN")
	        .antMatchers("/security/manager", "/security/manager/*").hasAnyAuthority("ADMIN", "MANAGER")
	        .anyRequest().permitAll()
	        .and()
            
        .formLogin()
        	.loginPage("/security/login")  //로그인 페이지 url
        	.loginProcessingUrl("/security/loginProcess") //<form action="?" //<form action="?" 이렇게 보낼테니까 처리해!
        	.permitAll()							//누구나 접근
        	.defaultSuccessUrl("/security/home") 	//성공시 이동할 페이지 
        	.and()
        .logout()
        	.logoutUrl("/security/logout")
        	.logoutSuccessUrl("/security/home")
        	.invalidateHttpSession(true)
        	.clearAuthentication(true)
        	.permitAll()
        	.and()
        .exceptionHandling()
        	.accessDeniedPage("/security/page403");
		
		//아래 코드 작성 시, 보안에 취약함
		http.csrf().disable(); //csrf()를 사용하지 않을 경우
	}


	
	
	

}
