package com.raj.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
		auth.jdbcAuthentication()
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
		   		+ "from user_accounts where username=?")
		   .authoritiesByUsernameQuery("select username, role "
		   		+ "from user_accounts where username=?")
		   .dataSource(datasource)
		   .passwordEncoder(bCryptEncoder);//this is decoding the password in query retrieval
		
		
	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/projects/new").hasRole("ADMIN")
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasRole("ADMIN")
			.antMatchers("/employees/save").hasRole("ADMIN")
//			.antMatchers("/employees/new").hasAuthority("ADMIN")
//			.antMatchers("/employees/save").hasAuthority("ADMIN")
//			.antMatchers("/h2_console/**").permitAll()
//			.antMatchers("/").authenticated().and().formLogin();
			.antMatchers("/","/**").permitAll()
			.and() 
			.formLogin(); 
		http.csrf().disable();
		
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
		//above are just to console to work but using in this prod app can be hack
		
	}
}
