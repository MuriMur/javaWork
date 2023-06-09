package b2bbebolino;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    public String name = "";
    public String urlAddress = "";
    public Category parent = null;
    List<Product> productsOfCategory;

    public List<Product> getProductsOfCategory() {
        return productsOfCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) && this.toString().equals(category.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    public void setProductsOfCategory(List<Product> productsOfCategory) {
        this.productsOfCategory = productsOfCategory;
    }

    public Category(String name) {
        this.name = name;
        productsOfCategory = new ArrayList<>();
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        productsOfCategory = new ArrayList<>();
    }

    public Category(String name, String urlAddress) {
        this.name = name;
        this.urlAddress = urlAddress;
    }

    public Category(String name, String urlAddress, Category parent) {
        this.name = name;
        this.urlAddress = urlAddress;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addProduct(Product product){
        productsOfCategory.add(product);
    }
    @Override
    public String toString() {
        if (parent != null){
            return name + " < " + parent;
        }else {
            return name;
        }
    }
}