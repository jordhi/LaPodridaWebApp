package cat.jhz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    private String id;
    private String num;
    private String pal;

    public Card() {
    }

    public Card(String id, String num, String pal) {
        this.id = id;
        this.num = num;
        this.pal = pal;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    @Override
    public String toString() {
        return "{id=" + id + "}";
    }
}
