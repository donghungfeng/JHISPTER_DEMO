package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.DuToan;
import com.api.hispterdemo.service.DuToanService;
import com.api.hispterdemo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.api.hispterdemo.domain.DuToan}.
 */
@RestController
@RequestMapping("/api")
public class DuToanResource {

    private final Logger log = LoggerFactory.getLogger(DuToanResource.class);

    private static final String ENTITY_NAME = "duToan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DuToanService duToanService;

    public DuToanResource(DuToanService duToanService) {
        this.duToanService = duToanService;
    }

    /**
     * {@code POST  /du-toans} : Create a new duToan.
     *
     * @param duToan the duToan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duToan, or with status {@code 400 (Bad Request)} if the duToan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/du-toans")
    public ResponseEntity<DuToan> createDuToan(@Valid @RequestBody DuToan duToan) throws URISyntaxException {
        log.debug("REST request to save DuToan : {}", duToan);
        if (duToan.getId() != null) {
            throw new BadRequestAlertException("A new duToan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DuToan result = duToanService.save(duToan);
        return ResponseEntity.created(new URI("/api/du-toans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /du-toans} : Updates an existing duToan.
     *
     * @param duToan the duToan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duToan,
     * or with status {@code 400 (Bad Request)} if the duToan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duToan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/du-toans")
    public ResponseEntity<DuToan> updateDuToan(@Valid @RequestBody DuToan duToan) throws URISyntaxException {
        log.debug("REST request to update DuToan : {}", duToan);
        if (duToan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DuToan result = duToanService.save(duToan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duToan.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /du-toans} : get all the duToans.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duToans in body.
     */
    @GetMapping("/du-toans")
    public List<DuToan> getAllDuToans() {
        log.debug("REST request to get all DuToans");
        return duToanService.findAll();
    }

    /**
     * {@code GET  /du-toans/:id} : get the "id" duToan.
     *
     * @param id the id of the duToan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duToan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/du-toans/{id}")
    public ResponseEntity<DuToan> getDuToan(@PathVariable Long id) {
        log.debug("REST request to get DuToan : {}", id);
        Optional<DuToan> duToan = duToanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duToan);
    }

    /**
     * {@code DELETE  /du-toans/:id} : delete the "id" duToan.
     *
     * @param id the id of the duToan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/du-toans/{id}")
    public ResponseEntity<Void> deleteDuToan(@PathVariable Long id) {
        log.debug("REST request to delete DuToan : {}", id);
        duToanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
