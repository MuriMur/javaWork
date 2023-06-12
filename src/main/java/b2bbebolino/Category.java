package b2bbebolino;

public class Category {
    public String name = "";
    public String urlAddress = "";
    public zami.bg.Category parent = null;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, zami.bg.Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public Category(String name, String urlAddress) {
        this.name = name;
        this.urlAddress = urlAddress;
    }

    public Category(String name, String urlAddress, zami.bg.Category parent) {
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

    public zami.bg.Category getParent() {
        return parent;
    }

    public void setParent(zami.bg.Category parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{ " +
                "name = " + name +
                ", urlAddress = " + urlAddress +
                ", parent = " + parent +
                '}';
    }
}