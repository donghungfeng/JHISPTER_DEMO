package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.ToChucService;
import com.api.hispterdemo.domain.ToChuc;
import com.api.hispterdemo.repository.ToChucRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ToChuc}.
 */
@Service
@Transactional
public class ToChucServiceImpl implements ToChucService {

    private final Logger log = LoggerFactory.getLogger(ToChucServiceImpl.class);

    private final ToChucRepository toChucRepository;

    public ToChucServiceImpl(ToChucRepository toChucRepository) {
        this.toChucRepository = toChucRepository;
    }

    /**
     * Save a toChuc.
     *
     * @param toChuc the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ToChuc save(ToChuc toChuc) {
        log.debug("Request to save ToChuc : {}", toChuc);
        return toChucRepository.save(toChuc);
    }

    /**
     * Get all the toChucs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ToChuc> findAll() {
        log.debug("Request to get all ToChucs");
        return toChucRepository.findAll();
    }


    /**
     * Get one toChuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ToChuc> findOne(Long id) {
        log.debug("Request to get ToChuc : {}", id);
        return toChucRepository.findById(id);
    }

    /**
     * Delete the toChuc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ToChuc : {}", id);
        toChucRepository.deleteById(id);
    }
}
