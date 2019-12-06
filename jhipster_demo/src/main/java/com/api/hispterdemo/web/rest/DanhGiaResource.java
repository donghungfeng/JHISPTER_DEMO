package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.DanhGia;
import com.api.hispterdemo.service.DanhGiaService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.DanhGia}.
 */
@RestController
@RequestMapping("/api")
public class DanhGiaResource {

    private final Logger log = LoggerFactory.getLogger(DanhGiaResource.class);

    private static final String ENTITY_NAME = "danhGia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhGiaService danhGiaService;

    public DanhGiaResource(DanhGiaService danhGiaService) {
        this.danhGiaService = danhGiaService;
    }

    /**
     * {@code POST  /danh-gias} : Create a new danhGia.
     *
     * @param danhGia the danhGia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhGia, or with status {@code 400 (Bad Request)} if the danhGia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danh-gias")
    public ResponseEntity<DanhGia> createDanhGia(@Valid @RequestBody DanhGia danhGia) throws URISyntaxException {
        log.debug("REST request to save DanhGia : {}", danhGia);
        if (danhGia.getId() != null) {
            throw new BadRequestAlertException("A new danhGia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhGia result = danhGiaService.save(danhGia);
        return ResponseEntity.created(new URI("/api/danh-gias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danh-gias} : Updates an existing danhGia.
     *
     * @param danhGia the danhGia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhGia,
     * or with status {@code 400 (Bad Request)} if the danhGia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhGia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danh-gias")
    public ResponseEntity<DanhGia> updateDanhGia(@Valid @RequestBody DanhGia danhGia) throws URISyntaxException {
        log.debug("REST request to update DanhGia : {}", danhGia);
        if (danhGia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhGia result = danhGiaService.save(danhGia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhGia.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danh-gias} : get all the danhGias.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhGias in body.
     */
    @GetMapping("/danh-gias")
    public List<DanhGia> getAllDanhGias() {
        log.debug("REST request to get all DanhGias");
        return danhGiaService.findAll();
    }

    /**
     * {@code GET  /danh-gias/:id} : get the "id" danhGia.
     *
     * @param id the id of the danhGia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhGia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danh-gias/{id}")
    public ResponseEntity<DanhGia> getDanhGia(@PathVariable Long id) {
        log.debug("REST request to get DanhGia : {}", id);
        Optional<DanhGia> danhGia = danhGiaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhGia);
    }

    /**
     * {@code DELETE  /danh-gias/:id} : delete the "id" danhGia.
     *
     * @param id the id of the danhGia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danh-gias/{id}")
    public ResponseEntity<Void> deleteDanhGia(@PathVariable Long id) {
        log.debug("REST request to delete DanhGia : {}", id);
        danhGiaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
