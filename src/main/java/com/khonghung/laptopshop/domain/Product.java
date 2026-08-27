package com.khonghung.laptopshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name Laptop must not be empty")
    @Size(min = 2, message = "Name Laptop must be at least 2 characters long")
    private String name;

    @NotNull(message = "Price must not be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    private String image;

    @NotBlank(message = "Detail description must not be empty")
    @Size(min = 10, message = "Detail description must be at least 10 characters long")
    private String detailDesc;

    @NotBlank(message = "Short description must not be empty")
    @Size(min = 5, message = "Short description must be at least 5 characters long")
    private String shortDesc;

    @NotNull(message = "Quantity must not be empty")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Long quantity;

    @NotNull(message = "Sold must not be empty")
    @Min(value = 0, message = "Sold must be at least 0")
    private long sold;

    private String factory;

    private String target;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", quantity=" + quantity +
                ", sold=" + sold +
                ", factory='" + factory + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
