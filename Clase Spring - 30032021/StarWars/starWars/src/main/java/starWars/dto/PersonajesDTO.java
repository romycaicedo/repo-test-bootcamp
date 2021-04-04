package starWars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PersonajesDTO {
    public String name;
    public String height;
    public String mass;
    @JsonProperty("hair_color")
    public String hairColor;
    @JsonProperty("skin_color")
    public String skinColor;
    @JsonProperty("eye_color")
    public String eyeColor;
    @JsonProperty("birth_year")
    public String birthYear;
    public String gender;
    public String homeworld;
    public String species;


}
