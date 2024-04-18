package engine.spectro.entity;

import lombok.Data;

@Data
public abstract class ProductEntity {
    Long id;
    String model;
    int amount;
    String image;
}
