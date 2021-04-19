package openspace.spacepicture.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemCollection {

    @JsonProperty("")
    private List<Item> data;

    public ItemCollection() {
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
