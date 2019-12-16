package com.jccd.crudexample.controller;

import com.jccd.crudexample.entity.Developer;
import com.jccd.crudexample.service.DeveloperService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@WebFluxTest(DeveloperController.class)
class DeveloperControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private DeveloperService developerService;

    @Test
    void getAll() {
        webTestClient.get().uri("/api/v1/developers/").exchange().expectStatus().is2xxSuccessful();
    }

    @Test
    void getById() {
        Developer developer = Developer.builder()
                .id("5df2601d50dad32f9b7e36aa")
                .city("Ibague")
                .name("Camilo Cubillos")
                .technology("Mobile")
                .build();
        Mono<Developer> developerMono = Mono.just(developer);

        Mockito.when(developerService.getById("5df2601d50dad32f9b7e36aa")).thenReturn(developerMono);


        webTestClient.get()
                .uri("/api/v1/developers/5df2601d50dad32f9b7e36aa")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Developer.class)
                .value(devTest -> {
                    Assertions.assertEquals(devTest.getName(), developer.getName());
                    Assertions.assertEquals(devTest.getCity(), developer.getCity());
                    Assertions.assertEquals(devTest.getTechnology(), developer.getTechnology());
                });
    }


    //TODO Arregla el método put para el test.
    @Test
    void updateById() {
//        Developer oldDeveloper = Developer.builder().id("123456").city("Espinal").name("Tobias Pacheco").technology("DataBase").build();
//        Mono<Developer> oldDeveloperMono = Mono.just(oldDeveloper);
//
//        Mockito.when(developerService.save(oldDeveloper)).thenReturn(oldDeveloperMono);
//
//        webTestClient.post()
//                .uri("/api/v1/developers/")
//                .accept(MediaType.APPLICATION_JSON)
//                .body(oldDeveloperMono,Developer.class)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Developer.class)
//                .value(devResponse ->{
//                    Assertions.assertEquals(devResponse.getName(), oldDeveloper.getName());
//                    Assertions.assertEquals(devResponse.getCity(), oldDeveloper.getCity());
//                    Assertions.assertEquals(devResponse.getTechnology(), oldDeveloper.getTechnology());
//                });
//
//
//        Developer newDeveloper = Developer.builder().id("123456").city("Ibague").name("Tobias Pacheco").technology("Mobile").build();
//        Mono<Developer> newDeveloperMono = Mono.just(newDeveloper);
//
//        Mockito.when(developerService.update(newDeveloper)).thenReturn(Mono.just(newDeveloperMono));
//        webTestClient.put()
//                .uri("/api/v1/developers/5df7a5e96736ae22e1ecd2f3")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Developer.class)
//                .value(devResponse ->{
//                    Assertions.assertEquals(devResponse.getName(), newDeveloper.getName());
//                });



    }

    @Test
    void save() {
        Developer developer = Developer.builder().city("Espinal").name("Tobias Pacheco").technology("DataBase").build();
        Mono<Developer> developerMono = Mono.just(developer);

        Mockito.when(developerService.save(developer)).thenReturn(developerMono);

        webTestClient.post()
                .uri("/api/v1/developers/")
                .accept(MediaType.APPLICATION_JSON)
                .body(developerMono,Developer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Developer.class)
                .value(devResponse ->{
                    Assertions.assertEquals(devResponse.getName(), developer.getName());
                    Assertions.assertEquals(devResponse.getCity(), developer.getCity());
                    Assertions.assertEquals(devResponse.getTechnology(), developer.getTechnology());
                });
    }

    @Test
    void delete() {
        Mockito.when(developerService.delete("5df7a5e96736ae22e1ecd2f3")).thenReturn(Mono.just("Eliminación correcta"));

        webTestClient.delete()
                .uri("/api/v1/developers/5df7a5e96736ae22e1ecd2f3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("\"Eliminación correcta\"");
    }
}