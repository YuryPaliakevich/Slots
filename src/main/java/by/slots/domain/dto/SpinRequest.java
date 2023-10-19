package by.slots.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SpinRequest {

//    @Min(value = 25)
//    @Max(value = 1000)
    private int stake;

}
