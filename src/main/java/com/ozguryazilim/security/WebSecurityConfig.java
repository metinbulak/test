package com.ozguryazilim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.ozguryazilim.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private MyUserDetailService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 
		 http.csrf().disable();
	        http
	            .authorizeRequests()
	            
	            .antMatchers("/login").permitAll()
	            .antMatchers("/register").permitAll()
	            .antMatchers( "/js/**", "/css/**").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin().loginPage("/login")
	        
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	    }
	 
	 
	 
	    @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// TODO Auto-generated method stub
			super.configure(auth);
			
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
			
			
		}
	 
	 
	 
	    @Bean
		public PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}
	    
	    
	    
	    
	  
}
