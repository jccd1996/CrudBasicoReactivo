package com.jccd.crudexample.service;

import com.jccd.crudexample.entity.Developer;
import com.jccd.crudexample.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;


    public Flux<Developer> getAll() {
        return developerRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Developer> getById(final String id) {
        return developerRepository.findById(id);
    }

    public Mono update(final Developer developer) {
        return developerRepository.save(developer);
    }

    public Mono save(final Developer developer) {
        return developerRepository.save(developer);
    }

    public Mono delete(final String id) {
        final Mono<Developer> dbProduct = getById(id);
        if (Objects.isNull(dbProduct)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(developerToBeDeleted -> developerRepository
                .delete(developerToBeDeleted).then(Mono.just(developerToBeDeleted)));
    }


}
