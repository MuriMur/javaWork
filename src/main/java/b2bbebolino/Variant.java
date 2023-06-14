package b2bbebolino;

public class Variant {
    private String id;
    private String colour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Variant() {
    }

    public Variant(String id, String colour) {
        this.id = id;
        this.colour = colour;
    }
}
