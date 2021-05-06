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
		
		auth.inMemoryAuthentication().withUser("user").password(bcrypt.encode("password")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("a").password(bcrypt.encode("b")).roles("EDITOR");
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
	
	
	
	
	//	@Bean
	//	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
	//		return new MySimpleUrlAuthenticationSuccessHandler();
	//	}
	//	
}
