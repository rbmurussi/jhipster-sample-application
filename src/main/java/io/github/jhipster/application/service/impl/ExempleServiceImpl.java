package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ExempleService;
import io.github.jhipster.application.domain.Exemple;
import io.github.jhipster.application.repository.ExempleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Exemple.
 */
@Service
public class ExempleServiceImpl implements ExempleService {

    private final Logger log = LoggerFactory.getLogger(ExempleServiceImpl.class);

    private final ExempleRepository exempleRepository;

    public ExempleServiceImpl(ExempleRepository exempleRepository) {
        this.exempleRepository = exempleRepository;
    }

    /**
     * Save a exemple.
     *
     * @param exemple the entity to save
     * @return the persisted entity
     */
    @Override
    public Exemple save(Exemple exemple) {
        log.debug("Request to save Exemple : {}", exemple);
        return exempleRepository.save(exemple);
    }

    /**
     * Get all the exemples.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Exemple> findAll(Pageable pageable) {
        log.debug("Request to get all Exemples");
        return exempleRepository.findAll(pageable);
    }


    /**
     * Get one exemple by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Exemple> findOne(String id) {
        log.debug("Request to get Exemple : {}", id);
        return exempleRepository.findById(id);
    }

    /**
     * Delete the exemple by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Exemple : {}", id);        exempleRepository.deleteById(id);
    }
}
