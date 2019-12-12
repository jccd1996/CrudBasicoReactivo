package com.jccd.crudexample.controller;

import com.jccd.crudexample.entity.Developer;
import com.jccd.crudexample.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("developers")
@AllArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public Flux<Developer> getAll() {
        return developerService.getAll();
    }

    @GetMapping("{id}")
    public Mono getById(@PathVariable("id") final String id) {
        return developerService.getById(id) ;
    }

    @PutMapping("{id}")
    public Mono updateById(@RequestBody final Developer developer) {
        return developerService.update(developer);
    }

    @PostMapping
    public Mono save(@RequestBody final Developer developer) {
        return developerService.save(developer);
    }

    @DeleteMapping("{id}")
    public Mono delete(@PathVariable final String id) {
        return developerService.delete(id);
    }

}
