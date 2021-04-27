package demoStorage.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String dni;
    private String name;
    private String lastName;
}
