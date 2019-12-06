package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.CapDeTaiService;
import com.api.hispterdemo.domain.CapDeTai;
import com.api.hispterdemo.repository.CapDeTaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CapDeTai}.
 */
@Service
@Transactional
public class CapDeTaiServiceImpl implements CapDeTaiService {

    private final Logger log = LoggerFactory.getLogger(CapDeTaiServiceImpl.class);

    private final CapDeTaiRepository capDeTaiRepository;

    public CapDeTaiServiceImpl(CapDeTaiRepository capDeTaiRepository) {
        this.capDeTaiRepository = capDeTaiRepository;
    }

    /**
     * Save a capDeTai.
     *
     * @param capDeTai the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CapDeTai save(CapDeTai capDeTai) {
        log.debug("Request to save CapDeTai : {}", capDeTai);
        return capDeTaiRepository.save(capDeTai);
    }

    /**
     * Get all the capDeTais.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CapDeTai> findAll() {
        log.debug("Request to get all CapDeTais");
        return capDeTaiRepository.findAll();
    }


    /**
     * Get one capDeTai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CapDeTai> findOne(Long id) {
        log.debug("Request to get CapDeTai : {}", id);
        return capDeTaiRepository.findById(id);
    }

    /**
     * Delete the capDeTai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CapDeTai : {}", id);
        capDeTaiRepository.deleteById(id);
    }
}
