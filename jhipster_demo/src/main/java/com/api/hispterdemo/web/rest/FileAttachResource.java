package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.domain.FileAttach;
import com.api.hispterdemo.service.FileAttachService;
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
 * REST controller for managing {@link com.api.hispterdemo.domain.FileAttach}.
 */
@RestController
@RequestMapping("/api")
public class FileAttachResource {

    private final Logger log = LoggerFactory.getLogger(FileAttachResource.class);

    private static final String ENTITY_NAME = "fileAttach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileAttachService fileAttachService;

    public FileAttachResource(FileAttachService fileAttachService) {
        this.fileAttachService = fileAttachService;
    }

    /**
     * {@code POST  /file-attaches} : Create a new fileAttach.
     *
     * @param fileAttach the fileAttach to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileAttach, or with status {@code 400 (Bad Request)} if the fileAttach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-attaches")
    public ResponseEntity<FileAttach> createFileAttach(@Valid @RequestBody FileAttach fileAttach) throws URISyntaxException {
        log.debug("REST request to save FileAttach : {}", fileAttach);
        if (fileAttach.getId() != null) {
            throw new BadRequestAlertException("A new fileAttach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileAttach result = fileAttachService.save(fileAttach);
        return ResponseEntity.created(new URI("/api/file-attaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-attaches} : Updates an existing fileAttach.
     *
     * @param fileAttach the fileAttach to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileAttach,
     * or with status {@code 400 (Bad Request)} if the fileAttach is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileAttach couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-attaches")
    public ResponseEntity<FileAttach> updateFileAttach(@Valid @RequestBody FileAttach fileAttach) throws URISyntaxException {
        log.debug("REST request to update FileAttach : {}", fileAttach);
        if (fileAttach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileAttach result = fileAttachService.save(fileAttach);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileAttach.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-attaches} : get all the fileAttaches.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileAttaches in body.
     */
    @GetMapping("/file-attaches")
    public List<FileAttach> getAllFileAttaches() {
        log.debug("REST request to get all FileAttaches");
        return fileAttachService.findAll();
    }

    /**
     * {@code GET  /file-attaches/:id} : get the "id" fileAttach.
     *
     * @param id the id of the fileAttach to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileAttach, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-attaches/{id}")
    public ResponseEntity<FileAttach> getFileAttach(@PathVariable Long id) {
        log.debug("REST request to get FileAttach : {}", id);
        Optional<FileAttach> fileAttach = fileAttachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileAttach);
    }

    /**
     * {@code DELETE  /file-attaches/:id} : delete the "id" fileAttach.
     *
     * @param id the id of the fileAttach to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-attaches/{id}")
    public ResponseEntity<Void> deleteFileAttach(@PathVariable Long id) {
        log.debug("REST request to delete FileAttach : {}", id);
        fileAttachService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
