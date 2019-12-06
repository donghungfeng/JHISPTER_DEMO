package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.TienDoService;
import com.api.hispterdemo.domain.TienDo;
import com.api.hispterdemo.repository.TienDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TienDo}.
 */
@Service
@Transactional
public class TienDoServiceImpl implements TienDoService {

    private final Logger log = LoggerFactory.getLogger(TienDoServiceImpl.class);

    private final TienDoRepository tienDoRepository;

    public TienDoServiceImpl(TienDoRepository tienDoRepository) {
        this.tienDoRepository = tienDoRepository;
    }

    /**
     * Save a tienDo.
     *
     * @param tienDo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TienDo save(TienDo tienDo) {
        log.debug("Request to save TienDo : {}", tienDo);
        return tienDoRepository.save(tienDo);
    }

    /**
     * Get all the tienDos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TienDo> findAll() {
        log.debug("Request to get all TienDos");
        return tienDoRepository.findAll();
    }


    /**
     * Get one tienDo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TienDo> findOne(Long id) {
        log.debug("Request to get TienDo : {}", id);
        return tienDoRepository.findById(id);
    }

    /**
     * Delete the tienDo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TienDo : {}", id);
        tienDoRepository.deleteById(id);
    }
}
