package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Exemple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Exemple.
 */
public interface ExempleService {

    /**
     * Save a exemple.
     *
     * @param exemple the entity to save
     * @return the persisted entity
     */
    Exemple save(Exemple exemple);

    /**
     * Get all the exemples.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Exemple> findAll(Pageable pageable);


    /**
     * Get the "id" exemple.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Exemple> findOne(String id);

    /**
     * Delete the "id" exemple.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
