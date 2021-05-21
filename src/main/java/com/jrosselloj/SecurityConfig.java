package com.jrosselloj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jrosselloj.service.UserService;

/**
 * Classe configuració seguretat usuaris
 * 
 * @author Jaume
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
		
		auth.inMemoryAuthentication().withUser("ADMIN_SIS").password(bcrypt.encode("1234")).roles("ADMIN_SIS");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/javax.faces.resource/**").permitAll()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated();
		
		// login
		http.formLogin().loginPage("/login.xhtml").permitAll().defaultSuccessUrl("/index.xhtml", true);
		//http.formLogin().loginPage("/login.xhtml").permitAll().successHandler(myAuthenticationSuccessHandler());
		
		// logout
		http.logout().logoutSuccessUrl("/login.xhtml");
		
		http.csrf().disable();
		
	}
	
	
}
