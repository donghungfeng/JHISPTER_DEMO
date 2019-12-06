package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.XepLoai;
import com.api.hispterdemo.service.XepLoaiService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.XepLoai}.
 */
@RestController
@RequestMapping("/api")
public class XepLoaiResource {

    private final Logger log = LoggerFactory.getLogger(XepLoaiResource.class);

    private static final String ENTITY_NAME = "xepLoai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final XepLoaiService xepLoaiService;

    public XepLoaiResource(XepLoaiService xepLoaiService) {
        this.xepLoaiService = xepLoaiService;
    }

    /**
     * {@code POST  /xep-loais} : Create a new xepLoai.
     *
     * @param xepLoai the xepLoai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new xepLoai, or with status {@code 400 (Bad Request)} if the xepLoai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/xep-loais")
    public ResponseEntity<XepLoai> createXepLoai(@Valid @RequestBody XepLoai xepLoai) throws URISyntaxException {
        log.debug("REST request to save XepLoai : {}", xepLoai);
        if (xepLoai.getId() != null) {
            throw new BadRequestAlertException("A new xepLoai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        XepLoai result = xepLoaiService.save(xepLoai);
        return ResponseEntity.created(new URI("/api/xep-loais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /xep-loais} : Updates an existing xepLoai.
     *
     * @param xepLoai the xepLoai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated xepLoai,
     * or with status {@code 400 (Bad Request)} if the xepLoai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the xepLoai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/xep-loais")
    public ResponseEntity<XepLoai> updateXepLoai(@Valid @RequestBody XepLoai xepLoai) throws URISyntaxException {
        log.debug("REST request to update XepLoai : {}", xepLoai);
        if (xepLoai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        XepLoai result = xepLoaiService.save(xepLoai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, xepLoai.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /xep-loais} : get all the xepLoais.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of xepLoais in body.
     */
    @GetMapping("/xep-loais")
    public List<XepLoai> getAllXepLoais() {
        log.debug("REST request to get all XepLoais");
        return xepLoaiService.findAll();
    }

    /**
     * {@code GET  /xep-loais/:id} : get the "id" xepLoai.
     *
     * @param id the id of the xepLoai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the xepLoai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/xep-loais/{id}")
    public ResponseEntity<XepLoai> getXepLoai(@PathVariable Long id) {
        log.debug("REST request to get XepLoai : {}", id);
        Optional<XepLoai> xepLoai = xepLoaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(xepLoai);
    }

    /**
     * {@code DELETE  /xep-loais/:id} : delete the "id" xepLoai.
     *
     * @param id the id of the xepLoai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/xep-loais/{id}")
    public ResponseEntity<Void> deleteXepLoai(@PathVariable Long id) {
        log.debug("REST request to delete XepLoai : {}", id);
        xepLoaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
