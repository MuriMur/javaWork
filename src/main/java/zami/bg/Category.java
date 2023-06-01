package zami.bg;

public class Category {
    public String name = "";
    public String urlAddress = "";
    public Category parent = null;

    public Category(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", urlAddress='" + urlAddress + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }
}
