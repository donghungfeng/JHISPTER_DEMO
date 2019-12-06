package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.NhanSuTGService;
import com.api.hispterdemo.domain.NhanSuTG;
import com.api.hispterdemo.repository.NhanSuTGRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link NhanSuTG}.
 */
@Service
@Transactional
public class NhanSuTGServiceImpl implements NhanSuTGService {

    private final Logger log = LoggerFactory.getLogger(NhanSuTGServiceImpl.class);

    private final NhanSuTGRepository nhanSuTGRepository;

    public NhanSuTGServiceImpl(NhanSuTGRepository nhanSuTGRepository) {
        this.nhanSuTGRepository = nhanSuTGRepository;
    }

    /**
     * Save a nhanSuTG.
     *
     * @param nhanSuTG the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NhanSuTG save(NhanSuTG nhanSuTG) {
        log.debug("Request to save NhanSuTG : {}", nhanSuTG);
        return nhanSuTGRepository.save(nhanSuTG);
    }

    /**
     * Get all the nhanSuTGS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NhanSuTG> findAll() {
        log.debug("Request to get all NhanSuTGS");
        return nhanSuTGRepository.findAll();
    }


    /**
     * Get one nhanSuTG by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhanSuTG> findOne(Long id) {
        log.debug("Request to get NhanSuTG : {}", id);
        return nhanSuTGRepository.findById(id);
    }

    /**
     * Delete the nhanSuTG by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhanSuTG : {}", id);
        nhanSuTGRepository.deleteById(id);
    }
}
