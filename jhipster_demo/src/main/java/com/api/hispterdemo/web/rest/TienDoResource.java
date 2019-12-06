package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.TienDo;
import com.api.hispterdemo.service.TienDoService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.TienDo}.
 */
@RestController
@RequestMapping("/api")
public class TienDoResource {

    private final Logger log = LoggerFactory.getLogger(TienDoResource.class);

    private static final String ENTITY_NAME = "tienDo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TienDoService tienDoService;

    public TienDoResource(TienDoService tienDoService) {
        this.tienDoService = tienDoService;
    }

    /**
     * {@code POST  /tien-dos} : Create a new tienDo.
     *
     * @param tienDo the tienDo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tienDo, or with status {@code 400 (Bad Request)} if the tienDo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tien-dos")
    public ResponseEntity<TienDo> createTienDo(@Valid @RequestBody TienDo tienDo) throws URISyntaxException {
        log.debug("REST request to save TienDo : {}", tienDo);
        if (tienDo.getId() != null) {
            throw new BadRequestAlertException("A new tienDo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienDo result = tienDoService.save(tienDo);
        return ResponseEntity.created(new URI("/api/tien-dos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tien-dos} : Updates an existing tienDo.
     *
     * @param tienDo the tienDo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tienDo,
     * or with status {@code 400 (Bad Request)} if the tienDo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tienDo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tien-dos")
    public ResponseEntity<TienDo> updateTienDo(@Valid @RequestBody TienDo tienDo) throws URISyntaxException {
        log.debug("REST request to update TienDo : {}", tienDo);
        if (tienDo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienDo result = tienDoService.save(tienDo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tienDo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tien-dos} : get all the tienDos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tienDos in body.
     */
    @GetMapping("/tien-dos")
    public List<TienDo> getAllTienDos() {
        log.debug("REST request to get all TienDos");
        return tienDoService.findAll();
    }

    /**
     * {@code GET  /tien-dos/:id} : get the "id" tienDo.
     *
     * @param id the id of the tienDo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tienDo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tien-dos/{id}")
    public ResponseEntity<TienDo> getTienDo(@PathVariable Long id) {
        log.debug("REST request to get TienDo : {}", id);
        Optional<TienDo> tienDo = tienDoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tienDo);
    }

    /**
     * {@code DELETE  /tien-dos/:id} : delete the "id" tienDo.
     *
     * @param id the id of the tienDo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tien-dos/{id}")
    public ResponseEntity<Void> deleteTienDo(@PathVariable Long id) {
        log.debug("REST request to delete TienDo : {}", id);
        tienDoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
