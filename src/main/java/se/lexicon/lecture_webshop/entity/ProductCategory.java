package se.lexicon.lecture_webshop.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String categoryId;

    @Column(unique = true)
    private String value;

    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "categories"
    )
    private Set<Product> products;

    public ProductCategory(String categoryId, String value, Set<Product> products) {
        this.categoryId = categoryId;
        this.value = value;
        this.products = products;
    }

    public ProductCategory() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Product> getProducts() {
    	if(products == null) return new HashSet<>();
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, value);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId='" + categoryId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
