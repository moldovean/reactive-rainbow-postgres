package net.vrabie.takereactive002.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    private Integer id;
    private String name;

    public Reservation(String name) {
        this.name = name;
    }
}
