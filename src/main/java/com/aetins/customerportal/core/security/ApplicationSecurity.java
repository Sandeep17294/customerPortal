
package com.aetins.customerportal.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.aetins.customerportal.core.enums.PRIVILEGES;
import com.aetins.customerportal.core.enums.ROLES;
import com.aetins.customerportal.core.handlers.AuthenticationSecuritySuccessHandler;

/**
 * Spring security filter
 * 
 * @author avinash
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSecuritySuccessHandler successHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler loginFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/resources/**", "/primefaces_resource/**",
				"/*.css", "/*.js", "/*.scss", "/*.jpeg", "/*.eot", "/*.svg", "/*.ttf", "/*.woff").permitAll();

		http.authorizeRequests()
				.antMatchers("/login_tabbed.app", "/registration/**","/pswd/**","/sms/**","/automation/**", "/Aunthentication/**", "/WEN-INF/views/**")
				.permitAll().
				antMatchers("/admin/**").hasAnyAuthority(PRIVILEGES.USER_STATUS_CHANGE_PRIVILEGE.name(),PRIVILEGES.FEEDBACK_CLOSE_PRIVILEGE.name(), PRIVILEGES.PORTAL_MANAGEMENT_PRIVILEGE.name())
				.antMatchers("/user/**").hasAnyAuthority(PRIVILEGES.TRANSACTION_PRIVILEGE.name(),PRIVILEGES.CHANGE_SECURITY_QUES_PRIVILEGE.name(), PRIVILEGES.E_STATEMENT_PRIVILEGE.name())
				.antMatchers("/businessuser/**").hasAuthority(PRIVILEGES.FEEDBACK_REPLY_PRIVILEGE.name())
				.antMatchers("/secure/**").hasAnyAuthority(PRIVILEGES.USER_STATUS_CHANGE_PRIVILEGE.name(),
						PRIVILEGES.FEEDBACK_CLOSE_PRIVILEGE.name(), PRIVILEGES.PORTAL_MANAGEMENT_PRIVILEGE.name(),
						PRIVILEGES.TRANSACTION_PRIVILEGE.name(), PRIVILEGES.CHANGE_SECURITY_QUES_PRIVILEGE.name(),
						PRIVILEGES.E_STATEMENT_PRIVILEGE.name(), PRIVILEGES.FEEDBACK_REPLY_PRIVILEGE.name())
				.anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password")
				.successHandler(successHandler).permitAll().failureHandler(loginFailureHandler).and()
				.sessionManagement().invalidSessionUrl("/login.jsp").maximumSessions(1)
				.sessionRegistry(sessionRegistry()).expiredUrl("/sessionexpried").and().sessionFixation().none().and()
				.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler).invalidateHttpSession(false)
				.deleteCookies("JSESSIONID","harmony_expandeditems").permitAll();

		http.csrf().disable();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**", "/resources/**", "/primefaces_resource/**", "/*.css",
				"/*.js", "/*.scss", "/*.jpeg", "/*.eot", "/*.svg", "/*.ttf", "/*.woff");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	/*
	 * @Bean public SecurityWebFilterChain
	 * springSecurityFilterChain(ServerHttpSecurity http) { http // ...
	 * .redirectToHttps() .httpsRedirectWhen(e ->
	 * e.getRequest().getHeaders().containsKey("X-Forwarded-Proto")); return
	 * http.build(); }
	 */

}
