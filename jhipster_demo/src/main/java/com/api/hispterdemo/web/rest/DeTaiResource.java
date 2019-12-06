package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.DeTai;
import com.api.hispterdemo.service.DeTaiService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.DeTai}.
 */
@RestController
@RequestMapping("/api")
public class DeTaiResource {

    private final Logger log = LoggerFactory.getLogger(DeTaiResource.class);

    private static final String ENTITY_NAME = "deTai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeTaiService deTaiService;

    public DeTaiResource(DeTaiService deTaiService) {
        this.deTaiService = deTaiService;
    }

    /**
     * {@code POST  /de-tais} : Create a new deTai.
     *
     * @param deTai the deTai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deTai, or with status {@code 400 (Bad Request)} if the deTai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/de-tais")
    public ResponseEntity<DeTai> createDeTai(@Valid @RequestBody DeTai deTai) throws URISyntaxException {
        log.debug("REST request to save DeTai : {}", deTai);
        if (deTai.getId() != null) {
            throw new BadRequestAlertException("A new deTai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeTai result = deTaiService.save(deTai);
        return ResponseEntity.created(new URI("/api/de-tais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /de-tais} : Updates an existing deTai.
     *
     * @param deTai the deTai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deTai,
     * or with status {@code 400 (Bad Request)} if the deTai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deTai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/de-tais")
    public ResponseEntity<DeTai> updateDeTai(@Valid @RequestBody DeTai deTai) throws URISyntaxException {
        log.debug("REST request to update DeTai : {}", deTai);
        if (deTai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeTai result = deTaiService.save(deTai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deTai.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /de-tais} : get all the deTais.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deTais in body.
     */
    @GetMapping("/de-tais")
    public List<DeTai> getAllDeTais() {
        log.debug("REST request to get all DeTais");
        return deTaiService.findAll();
    }

    /**
     * {@code GET  /de-tais/:id} : get the "id" deTai.
     *
     * @param id the id of the deTai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deTai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/de-tais/{id}")
    public ResponseEntity<DeTai> getDeTai(@PathVariable Long id) {
        log.debug("REST request to get DeTai : {}", id);
        Optional<DeTai> deTai = deTaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deTai);
    }

    /**
     * {@code DELETE  /de-tais/:id} : delete the "id" deTai.
     *
     * @param id the id of the deTai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/de-tais/{id}")
    public ResponseEntity<Void> deleteDeTai(@PathVariable Long id) {
        log.debug("REST request to delete DeTai : {}", id);
        deTaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
