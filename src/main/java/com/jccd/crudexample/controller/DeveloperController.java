package com.jccd.crudexample.controller;

import com.jccd.crudexample.entity.Developer;
import com.jccd.crudexample.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class DeveloperController {

    private final String DEVELOPER_URL = "/developers/";

    private final DeveloperService developerService;

    @GetMapping(DEVELOPER_URL)
    public Flux<Developer> getAll() {
        return developerService.getAll();
    }

    @GetMapping(DEVELOPER_URL +"{id}")
    public Mono getById(@PathVariable("id") final String id) {
        return developerService.getById(id) ;
    }

    @PutMapping(DEVELOPER_URL +"{id}")
    public Mono updateById(@RequestBody final Developer developer) {
        return developerService.update(developer);
    }

    @PostMapping(DEVELOPER_URL)
    public Mono save(@RequestBody final Developer developer) {
        return developerService.save(developer);
    }

    @DeleteMapping(DEVELOPER_URL +"{id}")
    public Mono delete(@PathVariable final String id) {
        return developerService.delete(id);
    }

}
