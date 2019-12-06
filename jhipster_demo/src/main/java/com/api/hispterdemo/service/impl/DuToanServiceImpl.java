package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.DuToanService;
import com.api.hispterdemo.domain.DuToan;
import com.api.hispterdemo.repository.DuToanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DuToan}.
 */
@Service
@Transactional
public class DuToanServiceImpl implements DuToanService {

    private final Logger log = LoggerFactory.getLogger(DuToanServiceImpl.class);

    private final DuToanRepository duToanRepository;

    public DuToanServiceImpl(DuToanRepository duToanRepository) {
        this.duToanRepository = duToanRepository;
    }

    /**
     * Save a duToan.
     *
     * @param duToan the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DuToan save(DuToan duToan) {
        log.debug("Request to save DuToan : {}", duToan);
        return duToanRepository.save(duToan);
    }

    /**
     * Get all the duToans.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DuToan> findAll() {
        log.debug("Request to get all DuToans");
        return duToanRepository.findAll();
    }


    /**
     * Get one duToan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DuToan> findOne(Long id) {
        log.debug("Request to get DuToan : {}", id);
        return duToanRepository.findById(id);
    }

    /**
     * Delete the duToan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DuToan : {}", id);
        duToanRepository.deleteById(id);
    }
}
