package demo.romanos.Entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
    private Long id;
    private String message;
    private String type;


}
