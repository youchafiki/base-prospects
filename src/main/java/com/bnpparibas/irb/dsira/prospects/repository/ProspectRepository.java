package com.bnpparibas.irb.dsira.prospects.repository;

import com.bnpparibas.irb.dsira.prospects.model.Prospect;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ProspectRepository extends R2dbcRepository<Prospect, Long> {
	Mono<Prospect> findByIdentifier(String identifier);
}
