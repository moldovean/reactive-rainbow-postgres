package net.vrabie.takereactive002.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Person {
    @Id
    private Integer id;
    private String name;
    private int age;
}
