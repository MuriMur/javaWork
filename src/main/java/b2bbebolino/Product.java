package b2bbebolino;

import zami.bg.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product {
    public String name = "";
    public BigDecimal price = null;
    public zami.bg.Category category = null;
    public List<String> images = new ArrayList<>();
    public String catalogNumber = "";
    public HashMap<String , String> features = new HashMap<>();
    public String description = "";
    public BigDecimal promoPrice = null;


    public Product(String name, BigDecimal price, zami.bg.Category category, List<String> images, String catalogNumber, HashMap<String, String> features, String description, BigDecimal promoPrice) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.images = images;
        this.catalogNumber = catalogNumber;
        this.features = features;
        this.description = description;
        this.promoPrice = promoPrice;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public HashMap<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(HashMap<String, String> features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public zami.bg.Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + "лв." + '\'' +
                ", category=" + category +
                ", images=" + images +
                ", catalogNumber='" + catalogNumber + '\'' +
                ", features=" + features +
                ", description='" + '\'' +
                ", promoPrice=" + promoPrice + "лв." +
                '}';
    }
}
