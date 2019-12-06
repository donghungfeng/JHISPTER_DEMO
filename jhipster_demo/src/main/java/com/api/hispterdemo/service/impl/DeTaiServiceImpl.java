package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.DeTaiService;
import com.api.hispterdemo.domain.DeTai;
import com.api.hispterdemo.repository.DeTaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DeTai}.
 */
@Service
@Transactional
public class DeTaiServiceImpl implements DeTaiService {

    private final Logger log = LoggerFactory.getLogger(DeTaiServiceImpl.class);

    private final DeTaiRepository deTaiRepository;

    public DeTaiServiceImpl(DeTaiRepository deTaiRepository) {
        this.deTaiRepository = deTaiRepository;
    }

    /**
     * Save a deTai.
     *
     * @param deTai the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DeTai save(DeTai deTai) {
        log.debug("Request to save DeTai : {}", deTai);
        return deTaiRepository.save(deTai);
    }

    /**
     * Get all the deTais.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DeTai> findAll() {
        log.debug("Request to get all DeTais");
        return deTaiRepository.findAll();
    }


    /**
     * Get one deTai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeTai> findOne(Long id) {
        log.debug("Request to get DeTai : {}", id);
        return deTaiRepository.findById(id);
    }

    /**
     * Delete the deTai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeTai : {}", id);
        deTaiRepository.deleteById(id);
    }
}
