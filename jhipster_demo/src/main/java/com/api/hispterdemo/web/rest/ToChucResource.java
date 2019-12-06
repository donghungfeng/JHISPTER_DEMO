package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.ToChuc;
import com.api.hispterdemo.service.ToChucService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.ToChuc}.
 */
@RestController
@RequestMapping("/api")
public class ToChucResource {

    private final Logger log = LoggerFactory.getLogger(ToChucResource.class);

    private static final String ENTITY_NAME = "toChuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToChucService toChucService;

    public ToChucResource(ToChucService toChucService) {
        this.toChucService = toChucService;
    }

    /**
     * {@code POST  /to-chucs} : Create a new toChuc.
     *
     * @param toChuc the toChuc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toChuc, or with status {@code 400 (Bad Request)} if the toChuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/to-chucs")
    public ResponseEntity<ToChuc> createToChuc(@Valid @RequestBody ToChuc toChuc) throws URISyntaxException {
        log.debug("REST request to save ToChuc : {}", toChuc);
        if (toChuc.getId() != null) {
            throw new BadRequestAlertException("A new toChuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToChuc result = toChucService.save(toChuc);
        return ResponseEntity.created(new URI("/api/to-chucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /to-chucs} : Updates an existing toChuc.
     *
     * @param toChuc the toChuc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toChuc,
     * or with status {@code 400 (Bad Request)} if the toChuc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toChuc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/to-chucs")
    public ResponseEntity<ToChuc> updateToChuc(@Valid @RequestBody ToChuc toChuc) throws URISyntaxException {
        log.debug("REST request to update ToChuc : {}", toChuc);
        if (toChuc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ToChuc result = toChucService.save(toChuc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toChuc.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /to-chucs} : get all the toChucs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toChucs in body.
     */
    @GetMapping("/to-chucs")
    public List<ToChuc> getAllToChucs() {
        log.debug("REST request to get all ToChucs");
        return toChucService.findAll();
    }

    /**
     * {@code GET  /to-chucs/:id} : get the "id" toChuc.
     *
     * @param id the id of the toChuc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toChuc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/to-chucs/{id}")
    public ResponseEntity<ToChuc> getToChuc(@PathVariable Long id) {
        log.debug("REST request to get ToChuc : {}", id);
        Optional<ToChuc> toChuc = toChucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toChuc);
    }

    /**
     * {@code DELETE  /to-chucs/:id} : delete the "id" toChuc.
     *
     * @param id the id of the toChuc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/to-chucs/{id}")
    public ResponseEntity<Void> deleteToChuc(@PathVariable Long id) {
        log.debug("REST request to delete ToChuc : {}", id);
        toChucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
