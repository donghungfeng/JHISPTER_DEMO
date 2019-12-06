package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.OfficerService;
import com.api.hispterdemo.domain.Officer;
import com.api.hispterdemo.repository.OfficerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Officer}.
 */
@Service
@Transactional
public class OfficerServiceImpl implements OfficerService {

    private final Logger log = LoggerFactory.getLogger(OfficerServiceImpl.class);

    private final OfficerRepository officerRepository;

    public OfficerServiceImpl(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    /**
     * Save a officer.
     *
     * @param officer the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Officer save(Officer officer) {
        log.debug("Request to save Officer : {}", officer);
        return officerRepository.save(officer);
    }

    /**
     * Get all the officers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Officer> findAll() {
        log.debug("Request to get all Officers");
        return officerRepository.findAll();
    }


    /**
     * Get one officer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Officer> findOne(Long id) {
        log.debug("Request to get Officer : {}", id);
        return officerRepository.findById(id);
    }

    /**
     * Delete the officer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Officer : {}", id);
        officerRepository.deleteById(id);
    }
}
