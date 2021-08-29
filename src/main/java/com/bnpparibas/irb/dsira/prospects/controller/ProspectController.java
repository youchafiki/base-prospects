package com.bnpparibas.irb.dsira.prospects.controller;

import com.bnpparibas.irb.dsira.prospects.model.Greeting;
import com.bnpparibas.irb.dsira.prospects.model.Prospect;
import com.bnpparibas.irb.dsira.prospects.repository.ProspectRepository;
import jdk.jfr.internal.Logger;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/prospect")
@RequiredArgsConstructor
@Log4j2
public class ProspectController {

	private final ProspectRepository prospectRepository;


	@GetMapping
	public Flux<Prospect> getAll() {
		log.info("list all prospect");
		return prospectRepository.findAll();
	}

	@GetMapping(value="/greeting")
	public Mono<Greeting> greeting() {
		log.info("Greeting hello world");
		Mono<Greeting> greeting = WebClient.create("http://greeting:8086").get().retrieve().bodyToMono(Greeting.class);
		return greeting;
	}

	@PostMapping
	public Mono<Prospect> createMember(@RequestBody Prospect prospect) {

		log.info("Prospect to insert : ", prospect);
		return prospectRepository.save(prospect);

	}



}
