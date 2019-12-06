package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.Officer;
import com.api.hispterdemo.service.OfficerService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.Officer}.
 */
@RestController
@RequestMapping("/api")
public class OfficerResource {

    private final Logger log = LoggerFactory.getLogger(OfficerResource.class);

    private static final String ENTITY_NAME = "officer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfficerService officerService;

    public OfficerResource(OfficerService officerService) {
        this.officerService = officerService;
    }

    /**
     * {@code POST  /officers} : Create a new officer.
     *
     * @param officer the officer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new officer, or with status {@code 400 (Bad Request)} if the officer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/officers")
    public ResponseEntity<Officer> createOfficer(@Valid @RequestBody Officer officer) throws URISyntaxException {
        log.debug("REST request to save Officer : {}", officer);
        if (officer.getId() != null) {
            throw new BadRequestAlertException("A new officer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Officer result = officerService.save(officer);
        return ResponseEntity.created(new URI("/api/officers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /officers} : Updates an existing officer.
     *
     * @param officer the officer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated officer,
     * or with status {@code 400 (Bad Request)} if the officer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the officer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/officers")
    public ResponseEntity<Officer> updateOfficer(@Valid @RequestBody Officer officer) throws URISyntaxException {
        log.debug("REST request to update Officer : {}", officer);
        if (officer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Officer result = officerService.save(officer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /officers} : get all the officers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of officers in body.
     */
    @GetMapping("/officers")
    public List<Officer> getAllOfficers() {
        log.debug("REST request to get all Officers");
        return officerService.findAll();
    }

    /**
     * {@code GET  /officers/:id} : get the "id" officer.
     *
     * @param id the id of the officer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the officer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/officers/{id}")
    public ResponseEntity<Officer> getOfficer(@PathVariable Long id) {
        log.debug("REST request to get Officer : {}", id);
        Optional<Officer> officer = officerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(officer);
    }

    /**
     * {@code DELETE  /officers/:id} : delete the "id" officer.
     *
     * @param id the id of the officer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/officers/{id}")
    public ResponseEntity<Void> deleteOfficer(@PathVariable Long id) {
        log.debug("REST request to delete Officer : {}", id);
        officerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
