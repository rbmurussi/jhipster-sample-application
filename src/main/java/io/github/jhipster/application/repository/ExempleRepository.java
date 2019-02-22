package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Exemple;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Exemple entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExempleRepository extends MongoRepository<Exemple, String> {

}
