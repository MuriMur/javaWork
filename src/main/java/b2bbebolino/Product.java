package b2bbebolino;
import java.math.BigDecimal;
import java.util.List;

public class Product {
    public String id = "";
    public String productCode = "";
    public String barCode;
    public String title;
    public String shortDescription;
    public String description;
    public String url;
    public Category category;
    public String manufacturer;
    public BigDecimal price;
    public BigDecimal originalPrice;
    public String status;

    public List<String> images;
    public List<String> colours;
    public String metaTitle;
    public String metaDescription;
    public List<Variant> variants;

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
                ", \ncolours=" + colours +
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

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }
}

