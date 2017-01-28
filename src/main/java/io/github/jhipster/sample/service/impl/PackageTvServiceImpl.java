package io.github.jhipster.sample.service.impl;

import io.github.jhipster.sample.service.PackageTvService;
import io.github.jhipster.sample.domain.PackageTv;
import io.github.jhipster.sample.repository.PackageTvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing PackageTv.
 */
@Service
@Transactional
public class PackageTvServiceImpl implements PackageTvService{

    private final Logger log = LoggerFactory.getLogger(PackageTvServiceImpl.class);
    
    @Inject
    private PackageTvRepository packageTvRepository;

    /**
     * Save a packageTv.
     *
     * @param packageTv the entity to save
     * @return the persisted entity
     */
    public PackageTv save(PackageTv packageTv) {
        log.debug("Request to save PackageTv : {}", packageTv);
        PackageTv result = packageTvRepository.save(packageTv);
        return result;
    }

    /**
     *  Get all the packageTvs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PackageTv> findAll(Pageable pageable) {
        log.debug("Request to get all PackageTvs");
        Page<PackageTv> result = packageTvRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one packageTv by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PackageTv findOne(Long id) {
        log.debug("Request to get PackageTv : {}", id);
        PackageTv packageTv = packageTvRepository.findOne(id);
        return packageTv;
    }

    /**
     *  Delete the  packageTv by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PackageTv : {}", id);
        packageTvRepository.delete(id);
    }
}
