package com.raj.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
		auth.jdbcAuthentication().dataSource(datasource)
//		    .withDefaultSchema()
//			.withUser("rajkhare1")
//				.password("efforts007")
//				.roles("USER")
//			.and()
//			.withUser("richa")
//				.password("efforts")
//				.roles("USER")
//			.and()
//			.withUser("admin")
//			 	.password("adming")
//			   	.roles("ADMIN");
		
		   .usersByUsernameQuery("select username,password, enabled "
		   		+ "from users where username=?")
		   .authoritiesByUsernameQuery("select username, authority "
		   		+ "from authorities where username=?");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/projects/new").hasAnyRole("ADMIN")
			.antMatchers("/employees/new").hasAnyRole("ADMIN")
			.antMatchers("/h2_console/**").permitAll()
			.antMatchers("/").authenticated().and().formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
}
