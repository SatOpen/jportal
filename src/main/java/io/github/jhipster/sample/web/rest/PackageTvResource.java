package io.github.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.sample.domain.PackageTv;
import io.github.jhipster.sample.service.PackageTvService;
import io.github.jhipster.sample.web.rest.util.HeaderUtil;
import io.github.jhipster.sample.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PackageTv.
 */
@RestController
@RequestMapping("/api")
public class PackageTvResource {

    private final Logger log = LoggerFactory.getLogger(PackageTvResource.class);
        
    @Inject
    private PackageTvService packageTvService;

    /**
     * POST  /package-tvs : Create a new packageTv.
     *
     * @param packageTv the packageTv to create
     * @return the ResponseEntity with status 201 (Created) and with body the new packageTv, or with status 400 (Bad Request) if the packageTv has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/package-tvs")
    @Timed
    public ResponseEntity<PackageTv> createPackageTv(@Valid @RequestBody PackageTv packageTv) throws URISyntaxException {
        log.debug("REST request to save PackageTv : {}", packageTv);
        if (packageTv.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("packageTv", "idexists", "A new packageTv cannot already have an ID")).body(null);
        }
        PackageTv result = packageTvService.save(packageTv);
        return ResponseEntity.created(new URI("/api/package-tvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("packageTv", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /package-tvs : Updates an existing packageTv.
     *
     * @param packageTv the packageTv to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated packageTv,
     * or with status 400 (Bad Request) if the packageTv is not valid,
     * or with status 500 (Internal Server Error) if the packageTv couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/package-tvs")
    @Timed
    public ResponseEntity<PackageTv> updatePackageTv(@Valid @RequestBody PackageTv packageTv) throws URISyntaxException {
        log.debug("REST request to update PackageTv : {}", packageTv);
        if (packageTv.getId() == null) {
            return createPackageTv(packageTv);
        }
        PackageTv result = packageTvService.save(packageTv);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("packageTv", packageTv.getId().toString()))
            .body(result);
    }

    /**
     * GET  /package-tvs : get all the packageTvs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of packageTvs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/package-tvs")
    @Timed
    public ResponseEntity<List<PackageTv>> getAllPackageTvs(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PackageTvs");
        Page<PackageTv> page = packageTvService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/package-tvs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /package-tvs/:id : get the "id" packageTv.
     *
     * @param id the id of the packageTv to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the packageTv, or with status 404 (Not Found)
     */
    @GetMapping("/package-tvs/{id}")
    @Timed
    public ResponseEntity<PackageTv> getPackageTv(@PathVariable Long id) {
        log.debug("REST request to get PackageTv : {}", id);
        PackageTv packageTv = packageTvService.findOne(id);
        return Optional.ofNullable(packageTv)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /package-tvs/:id : delete the "id" packageTv.
     *
     * @param id the id of the packageTv to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/package-tvs/{id}")
    @Timed
    public ResponseEntity<Void> deletePackageTv(@PathVariable Long id) {
        log.debug("REST request to delete PackageTv : {}", id);
        packageTvService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("packageTv", id.toString())).build();
    }

}
