package com.bnpparibas.irb.dsira.prospects.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class CorsGlobalConfiguration implements WebFluxConfigurer {

	private static final String ACTUATOR_ENDPOINT_PATTERN = "/actuator/*";

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
						.authorizeExchange()
						.anyExchange().authenticated()
						.and()
						.oauth2ResourceServer()
						.jwt();
		return http.build();
	}


	@Bean
		@Order(Ordered.HIGHEST_PRECEDENCE)
		CorsWebFilter corsWebFilter() {
			CorsConfiguration corsConfig = new CorsConfiguration();
			corsConfig.setAllowedOrigins(Arrays.asList("*"));
			corsConfig.setMaxAge(8000L);
			corsConfig.addAllowedMethod("PUT");
			corsConfig.addAllowedMethod("POST");
			corsConfig.addAllowedMethod("DELETE");
			corsConfig.addAllowedMethod("HEAD");
			corsConfig.addAllowedMethod("GET");
			corsConfig.addAllowedHeader("*");

			UrlBasedCorsConfigurationSource source =
							new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);

			return new CorsWebFilter(source);
		}
	}
