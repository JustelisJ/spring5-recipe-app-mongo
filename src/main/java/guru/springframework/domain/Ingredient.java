package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Builder
@AllArgsConstructor
public class Ingredient {

    private String id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uom;
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

}
