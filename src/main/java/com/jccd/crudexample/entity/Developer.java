package com.jccd.crudexample.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Document("developer")
public class Developer implements Serializable {
    @Id
    private String id;
    private String name;
    private String technology;
    private String city;
}
