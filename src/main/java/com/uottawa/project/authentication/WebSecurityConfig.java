package com.uottawa.project.authentication;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	@Value("#{new Boolean('${global.isDebug:false}')}")
	private boolean isDebug;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (isDebug) {
			http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
		} else {
			http.csrf().disable().authorizeRequests().antMatchers("/users").authenticated()
					.antMatchers("/create").authenticated()
					.antMatchers("/main_events/**").authenticated().antMatchers("/main_grants/**").authenticated()
					.antMatchers("/main_members/**").authenticated().antMatchers("/main_partners/**").authenticated()
					.antMatchers("/main_products/**").authenticated().antMatchers("/main_supervision/**")
					.authenticated().antMatchers("/relp_event_event/**").authenticated()
					.antMatchers("/relp_event_grant/**").authenticated().antMatchers("/relp_event_member/**")
					.authenticated().antMatchers("/relp_event_partner/**").authenticated()
					.antMatchers("/relp_event_product/**").authenticated().antMatchers("/relp_event_topic/**")
					.authenticated().antMatchers("/relp_grant_member/**").authenticated()
					.antMatchers("/relp_grant_member_investigator/**").authenticated()
					.antMatchers("/relp_grant_topic/**").authenticated().antMatchers("/relp_member_affiliation/**")
					.authenticated().antMatchers("/relp_member_current_promotion/**").authenticated()
					.antMatchers("/relp_member_future_promotion/**").authenticated()
					.antMatchers("/relp_member_indicator_format/**").authenticated()
					.antMatchers("/relp_member_partnership_scope1/**").authenticated()
					.antMatchers("/relp_member_partnership_scope2/**").authenticated()
					.antMatchers("/relp_member_partnership_scope3/**").authenticated()
					.antMatchers("/relp_member_partnership_scope_future/**").authenticated()
					.antMatchers("/relp_member_partnership_type1/**").authenticated()
					.antMatchers("/relp_member_partnership_type2/**").authenticated()
					.antMatchers("/relp_member_partnership_type3/**").authenticated()
					.antMatchers("/relp_member_partnership_type_future/**").authenticated()
					.antMatchers("/relp_member_strategic_direction/**").authenticated()
					.antMatchers("/relp_partner_member/**").authenticated().antMatchers("/relp_product_member/**")
					.authenticated().antMatchers("/relp_product_partner/**").authenticated()
					.antMatchers("/relp_product_target_stakeholder/**").authenticated()
					.antMatchers("/relp_product_topic/**").authenticated()
					.antMatchers("/relp_supervision_co_supervisor/**").authenticated()
					.antMatchers("/relp_supervision_principal_supervisor/**").authenticated()
					.antMatchers("/relp_supervision_thesis_advisory_committee/**").authenticated()
					.antMatchers("/types_affiliation/**").authenticated().antMatchers("/types_event/**").authenticated()
					.antMatchers("/types_faculty/**").authenticated().antMatchers("/types_frequency_indicator/**")
					.authenticated().antMatchers("/types_grant_source/**").authenticated()
					.antMatchers("/types_grant_status/**").authenticated().antMatchers("/types_indicator_format/**")
					.authenticated().antMatchers("/types_member_cat/**").authenticated()
					.antMatchers("/types_partnership_scope/**").authenticated()
					.antMatchers("/types_partnership_type/**").authenticated().antMatchers("/types_product/**")
					.authenticated().antMatchers("/types_promotion/**").authenticated()
					.antMatchers("/types_strategic_direction/**").authenticated()
					.antMatchers("/types_target_stakeholder/**").authenticated().antMatchers("/types_topic/**")
					.authenticated().antMatchers("/types_trainee_level/**").authenticated().anyRequest().permitAll()
					.and().exceptionHandling()
					.authenticationEntryPoint(
							(request, response, exception) -> response.setStatus(HttpStatus.UNAUTHORIZED.value()))
					.and().formLogin().usernameParameter("email")
					.successHandler((request, response, authentication) -> response.setStatus(HttpStatus.OK.value()))
					.failureHandler(
							(request, response, authentication) -> response.setStatus(HttpStatus.UNAUTHORIZED.value()))
					.permitAll().and().logout()
					.logoutSuccessHandler(
							(request, response, authentication) -> response.setStatus(HttpStatus.OK.value()))
					.permitAll();
		}
	}
}
