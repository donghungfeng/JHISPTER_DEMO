package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.TrangThai;
import com.api.hispterdemo.service.TrangThaiService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.TrangThai}.
 */
@RestController
@RequestMapping("/api")
public class TrangThaiResource {

    private final Logger log = LoggerFactory.getLogger(TrangThaiResource.class);

    private static final String ENTITY_NAME = "trangThai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrangThaiService trangThaiService;

    public TrangThaiResource(TrangThaiService trangThaiService) {
        this.trangThaiService = trangThaiService;
    }

    /**
     * {@code POST  /trang-thais} : Create a new trangThai.
     *
     * @param trangThai the trangThai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trangThai, or with status {@code 400 (Bad Request)} if the trangThai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trang-thais")
    public ResponseEntity<TrangThai> createTrangThai(@Valid @RequestBody TrangThai trangThai) throws URISyntaxException {
        log.debug("REST request to save TrangThai : {}", trangThai);
        if (trangThai.getId() != null) {
            throw new BadRequestAlertException("A new trangThai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrangThai result = trangThaiService.save(trangThai);
        return ResponseEntity.created(new URI("/api/trang-thais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trang-thais} : Updates an existing trangThai.
     *
     * @param trangThai the trangThai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trangThai,
     * or with status {@code 400 (Bad Request)} if the trangThai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trangThai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trang-thais")
    public ResponseEntity<TrangThai> updateTrangThai(@Valid @RequestBody TrangThai trangThai) throws URISyntaxException {
        log.debug("REST request to update TrangThai : {}", trangThai);
        if (trangThai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrangThai result = trangThaiService.save(trangThai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trangThai.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trang-thais} : get all the trangThais.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trangThais in body.
     */
    @GetMapping("/trang-thais")
    public List<TrangThai> getAllTrangThais() {
        log.debug("REST request to get all TrangThais");
        return trangThaiService.findAll();
    }

    /**
     * {@code GET  /trang-thais/:id} : get the "id" trangThai.
     *
     * @param id the id of the trangThai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trangThai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trang-thais/{id}")
    public ResponseEntity<TrangThai> getTrangThai(@PathVariable Long id) {
        log.debug("REST request to get TrangThai : {}", id);
        Optional<TrangThai> trangThai = trangThaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trangThai);
    }

    /**
     * {@code DELETE  /trang-thais/:id} : delete the "id" trangThai.
     *
     * @param id the id of the trangThai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trang-thais/{id}")
    public ResponseEntity<Void> deleteTrangThai(@PathVariable Long id) {
        log.debug("REST request to delete TrangThai : {}", id);
        trangThaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
