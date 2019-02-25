package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.Exemple;
import io.github.jhipster.application.service.ExempleService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Exemple.
 */
@RestController
@RequestMapping("/api")
public class ExempleResource {

    private final Logger log = LoggerFactory.getLogger(ExempleResource.class);

    private static final String ENTITY_NAME = "exemple";

    private final ExempleService exempleService;

    public ExempleResource(ExempleService exempleService) {
        this.exempleService = exempleService;
    }

    /**
     * POST  /exemples : Create a new exemple.
     *
     * @param exemple the exemple to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exemple, or with status 400 (Bad Request) if the exemple has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/exemples")
    public ResponseEntity<Exemple> createExemple(@Valid @RequestBody Exemple exemple) throws URISyntaxException {
        log.debug("REST request to save Exemple : {}", exemple);
        if (exemple.getId() != null) {
            throw new BadRequestAlertException("A new exemple cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Exemple result = exempleService.save(exemple);
        return ResponseEntity.created(new URI("/api/exemples/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /exemples : Updates an existing exemple.
     *
     * @param exemple the exemple to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exemple,
     * or with status 400 (Bad Request) if the exemple is not valid,
     * or with status 500 (Internal Server Error) if the exemple couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/exemples")
    public ResponseEntity<Exemple> updateExemple(@Valid @RequestBody Exemple exemple) throws URISyntaxException {
        log.debug("REST request to update Exemple : {}", exemple);
        if (exemple.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Exemple result = exempleService.save(exemple);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exemple.getId().toString()))
            .body(result);
    }

    /**
     * GET  /exemples : get all the exemples.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of exemples in body
     */
    @GetMapping("/exemples")
    public ResponseEntity<List<Exemple>> getAllExemples(Pageable pageable) {
        log.debug("REST request to get a page of Exemples");
        Page<Exemple> page = exempleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exemples");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /exemples/:id : get the "id" exemple.
     *
     * @param id the id of the exemple to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exemple, or with status 404 (Not Found)
     */
    @GetMapping("/exemples/{id}")
    public ResponseEntity<Exemple> getExemple(@PathVariable String id) {
        log.debug("REST request to get Exemple : {}", id);
        Optional<Exemple> exemple = exempleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exemple);
    }

    /**
     * DELETE  /exemples/:id : delete the "id" exemple.
     *
     * @param id the id of the exemple to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/exemples/{id}")
    public ResponseEntity<Void> deleteExemple(@PathVariable String id) {
        log.debug("REST request to delete Exemple : {}", id);
        exempleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
