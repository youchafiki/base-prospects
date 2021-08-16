package com.bnpparibas.irb.dsira.prospects.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		System.out.println("###### JWT : "+jwt.toString());
		Collection<String> authorities = (Collection<String>) jwt.getClaims().getOrDefault("authorities",
		                                                                                   Collections.emptyList());
		Collection<String> scopes = Arrays.stream(((String) jwt.getClaims().getOrDefault("scope", "")).split(" "))
		                                  .map(String::trim).collect(Collectors.toList());
		//authorities.addAll(scopes);
		return authorities.stream().map(Object::toString).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
