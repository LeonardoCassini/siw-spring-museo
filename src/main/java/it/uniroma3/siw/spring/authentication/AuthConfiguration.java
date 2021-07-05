package it.uniroma3.siw.spring.authentication;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static it.uniroma3.siw.spring.model.Credenziali.ADMIN_ROLE;

@Configuration
public class AuthConfiguration extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			// definiamo chi può accedere
			.authorizeRequests()
			// chiunque può accedere alle seguenti pagine
			.antMatchers(HttpMethod.GET, "/","/home","/login","/artisti","/artista/{id}","/artista","/collezioni","/collezione","/collezioni/{id}","/opera","/opera/{id}","/anni","/anno/{annoRealizzazione}","/informazioni","/css/**","/immagini/**").permitAll()
			// chiunque può mandare richieste di post per il login e la registrazione
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			// solo gli utenti autenticati con il ruolo di admin possono accedere a risorse con path /admin/**
			.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
			.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
			// tutti gli utenti autenticati possono accedere alle pagine rimantenti 
			.anyRequest().authenticated()
			// autenticazione tramite il protocollo formlogin
			.and().formLogin()
			.loginPage("/login")
			// se il login ha successo si viene reindirizzati al path /homePage
			.defaultSuccessUrl("/default")
			// logout
			.and().logout()
			// logout attivato con una richiesta Get a "/logout"
			.logoutUrl("/logout")
			// se il logout ha successo si viene reindirizzati al path /homePage
			.logoutSuccessUrl("/home")
			.invalidateHttpSession(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.clearAuthentication(true).permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				// usa l'autowired datasource per accedere alle credenziali salvate
				.dataSource(this.datasource)
				// ottiene email e ruolo dell'utente
				.authoritiesByUsernameQuery("SELECT username, role FROM credenziali WHERE username=?")
				// ottiene email, password e un boolean flag per specificare se l'utente è attivo o meno
				.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
	}
	//Metodo per criptare de decriptare la password
	@Bean
	PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
}

