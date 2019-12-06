package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.FileAttachService;
import com.api.hispterdemo.domain.FileAttach;
import com.api.hispterdemo.repository.FileAttachRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FileAttach}.
 */
@Service
@Transactional
public class FileAttachServiceImpl implements FileAttachService {

    private final Logger log = LoggerFactory.getLogger(FileAttachServiceImpl.class);

    private final FileAttachRepository fileAttachRepository;

    public FileAttachServiceImpl(FileAttachRepository fileAttachRepository) {
        this.fileAttachRepository = fileAttachRepository;
    }

    /**
     * Save a fileAttach.
     *
     * @param fileAttach the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileAttach save(FileAttach fileAttach) {
        log.debug("Request to save FileAttach : {}", fileAttach);
        return fileAttachRepository.save(fileAttach);
    }

    /**
     * Get all the fileAttaches.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FileAttach> findAll() {
        log.debug("Request to get all FileAttaches");
        return fileAttachRepository.findAll();
    }


    /**
     * Get one fileAttach by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileAttach> findOne(Long id) {
        log.debug("Request to get FileAttach : {}", id);
        return fileAttachRepository.findById(id);
    }

    /**
     * Delete the fileAttach by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileAttach : {}", id);
        fileAttachRepository.deleteById(id);
    }
}
