package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.CapDeTai;
import com.api.hispterdemo.service.CapDeTaiService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.CapDeTai}.
 */
@RestController
@RequestMapping("/api")
public class CapDeTaiResource {

    private final Logger log = LoggerFactory.getLogger(CapDeTaiResource.class);

    private static final String ENTITY_NAME = "capDeTai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CapDeTaiService capDeTaiService;

    public CapDeTaiResource(CapDeTaiService capDeTaiService) {
        this.capDeTaiService = capDeTaiService;
    }

    /**
     * {@code POST  /cap-de-tais} : Create a new capDeTai.
     *
     * @param capDeTai the capDeTai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new capDeTai, or with status {@code 400 (Bad Request)} if the capDeTai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cap-de-tais")
    public ResponseEntity<CapDeTai> createCapDeTai(@Valid @RequestBody CapDeTai capDeTai) throws URISyntaxException {
        log.debug("REST request to save CapDeTai : {}", capDeTai);
        if (capDeTai.getId() != null) {
            throw new BadRequestAlertException("A new capDeTai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CapDeTai result = capDeTaiService.save(capDeTai);
        return ResponseEntity.created(new URI("/api/cap-de-tais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cap-de-tais} : Updates an existing capDeTai.
     *
     * @param capDeTai the capDeTai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated capDeTai,
     * or with status {@code 400 (Bad Request)} if the capDeTai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the capDeTai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cap-de-tais")
    public ResponseEntity<CapDeTai> updateCapDeTai(@Valid @RequestBody CapDeTai capDeTai) throws URISyntaxException {
        log.debug("REST request to update CapDeTai : {}", capDeTai);
        if (capDeTai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CapDeTai result = capDeTaiService.save(capDeTai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, capDeTai.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cap-de-tais} : get all the capDeTais.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of capDeTais in body.
     */
    @GetMapping("/cap-de-tais")
    public List<CapDeTai> getAllCapDeTais() {
        log.debug("REST request to get all CapDeTais");
        return capDeTaiService.findAll();
    }

    /**
     * {@code GET  /cap-de-tais/:id} : get the "id" capDeTai.
     *
     * @param id the id of the capDeTai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the capDeTai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cap-de-tais/{id}")
    public ResponseEntity<CapDeTai> getCapDeTai(@PathVariable Long id) {
        log.debug("REST request to get CapDeTai : {}", id);
        Optional<CapDeTai> capDeTai = capDeTaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(capDeTai);
    }

    /**
     * {@code DELETE  /cap-de-tais/:id} : delete the "id" capDeTai.
     *
     * @param id the id of the capDeTai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cap-de-tais/{id}")
    public ResponseEntity<Void> deleteCapDeTai(@PathVariable Long id) {
        log.debug("REST request to delete CapDeTai : {}", id);
        capDeTaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
