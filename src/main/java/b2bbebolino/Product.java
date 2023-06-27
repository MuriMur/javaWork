package b2bbebolino;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    public String id = "";
    public String productCode = "";
    public String barCode;
    public String title;
    public String shortDescription;
    public String description;
    public String url;
    public Category category = null;
    public String manufacturer;
    public BigDecimal price;
    public BigDecimal originalPrice;
    public String status;

    public List<String> images;
    public String metaTitle;
    public String metaDescription;
    public List<Variant> variants = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(productCode, product.productCode) &&
                Objects.equals(barCode, product.barCode) &&
                Objects.equals(title, product.title) &&
                Objects.equals(shortDescription, product.shortDescription) &&
                Objects.equals(description, product.description) &&
                Objects.equals(url, product.url) &&
                Objects.equals(category, product.category) &&
                Objects.equals(manufacturer, product.manufacturer) &&
                Objects.equals(price, product.price) &&
                Objects.equals(originalPrice, product.originalPrice) &&
                Objects.equals(status, product.status) &&
                Objects.equals(images, product.images) &&
                Objects.equals(metaTitle, product.metaTitle) &&
                Objects.equals(metaDescription, product.metaDescription) &&
                Objects.equals(variants, product.variants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, barCode, title, shortDescription, description, url, category, manufacturer, price, originalPrice, status, images, metaTitle, metaDescription, variants);
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "=== Product ===" +
                "\nid='" + id + '\'' +
                ", \nproductCode='" + productCode + '\'' +
                ", \nbarCode='" + barCode + '\'' +
                ", \ntitle='" + title + '\'' +
                ", \nshortDescription='" + shortDescription + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nurl='" + url + '\'' +
                ", \ncategory=" + category +
                ", \nmanufacturer='" + manufacturer + '\'' +
                ", \nprice=" + price +
                ", \noriginalPrice=" + originalPrice +
                ", \nstatus='" + status + '\'' +
                ", \nimages=" + images +
                ", \nmetaTitle='" + metaTitle + '\'' +
                ", \nmetaDescription='" + metaDescription + '\'' +
                ", \nvariants=" + variants +
                "\n=== ^^^ ===";
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

