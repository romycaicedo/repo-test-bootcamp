package store.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {
    public double code;
    public String message;
}
