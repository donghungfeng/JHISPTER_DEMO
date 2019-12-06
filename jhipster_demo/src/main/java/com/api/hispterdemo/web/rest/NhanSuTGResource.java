package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.NhanSuTG;
import com.api.hispterdemo.service.NhanSuTGService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.NhanSuTG}.
 */
@RestController
@RequestMapping("/api")
public class NhanSuTGResource {

    private final Logger log = LoggerFactory.getLogger(NhanSuTGResource.class);

    private static final String ENTITY_NAME = "nhanSuTG";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhanSuTGService nhanSuTGService;

    public NhanSuTGResource(NhanSuTGService nhanSuTGService) {
        this.nhanSuTGService = nhanSuTGService;
    }

    /**
     * {@code POST  /nhan-su-tgs} : Create a new nhanSuTG.
     *
     * @param nhanSuTG the nhanSuTG to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhanSuTG, or with status {@code 400 (Bad Request)} if the nhanSuTG has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhan-su-tgs")
    public ResponseEntity<NhanSuTG> createNhanSuTG(@Valid @RequestBody NhanSuTG nhanSuTG) throws URISyntaxException {
        log.debug("REST request to save NhanSuTG : {}", nhanSuTG);
        if (nhanSuTG.getId() != null) {
            throw new BadRequestAlertException("A new nhanSuTG cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanSuTG result = nhanSuTGService.save(nhanSuTG);
        return ResponseEntity.created(new URI("/api/nhan-su-tgs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhan-su-tgs} : Updates an existing nhanSuTG.
     *
     * @param nhanSuTG the nhanSuTG to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanSuTG,
     * or with status {@code 400 (Bad Request)} if the nhanSuTG is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhanSuTG couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhan-su-tgs")
    public ResponseEntity<NhanSuTG> updateNhanSuTG(@Valid @RequestBody NhanSuTG nhanSuTG) throws URISyntaxException {
        log.debug("REST request to update NhanSuTG : {}", nhanSuTG);
        if (nhanSuTG.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanSuTG result = nhanSuTGService.save(nhanSuTG);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhanSuTG.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nhan-su-tgs} : get all the nhanSuTGS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhanSuTGS in body.
     */
    @GetMapping("/nhan-su-tgs")
    public List<NhanSuTG> getAllNhanSuTGS() {
        log.debug("REST request to get all NhanSuTGS");
        return nhanSuTGService.findAll();
    }

    /**
     * {@code GET  /nhan-su-tgs/:id} : get the "id" nhanSuTG.
     *
     * @param id the id of the nhanSuTG to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhanSuTG, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhan-su-tgs/{id}")
    public ResponseEntity<NhanSuTG> getNhanSuTG(@PathVariable Long id) {
        log.debug("REST request to get NhanSuTG : {}", id);
        Optional<NhanSuTG> nhanSuTG = nhanSuTGService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhanSuTG);
    }

    /**
     * {@code DELETE  /nhan-su-tgs/:id} : delete the "id" nhanSuTG.
     *
     * @param id the id of the nhanSuTG to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhan-su-tgs/{id}")
    public ResponseEntity<Void> deleteNhanSuTG(@PathVariable Long id) {
        log.debug("REST request to delete NhanSuTG : {}", id);
        nhanSuTGService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
