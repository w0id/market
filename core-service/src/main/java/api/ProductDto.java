package api;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    public ProductDto() {
    }

    public ProductDto(final Long id, final String name, final BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}