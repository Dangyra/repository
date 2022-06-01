package lt.ku.library.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.ku.library.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		/*
		auth
			.userDetailsService(this.userService)
			.passwordEncoder(bc);
		*/
		
		auth
			.inMemoryAuthentication()
				.withUser("dangyra").password(bc.encode("biblioteka")).roles("admin", "user");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{


	    String[] staticResources  =  {
	        "/image/**",
	    };
		
		http
			.authorizeRequests()
				.antMatchers(staticResources).permitAll()
				.antMatchers("/book/new").hasAnyRole("admin")
				.antMatchers("/api/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/client/").permitAll()
				.antMatchers("/client/new").permitAll()
				.antMatchers("/book/").permitAll()
				.antMatchers("/order/").permitAll()
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
		
		.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login-error")
		.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
		/*
		.and()
			.csrf()
				.disable();
		*/
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
