package openspace.spacepicture.api.v1.model;

import java.util.List;

public class ItemCollectionDto {

    private List<ItemDto> data;

    public ItemCollectionDto() {
    }

    public List<ItemDto> getData() {
        return data;
    }

    public void setData(List<ItemDto> data) {
        this.data = data;
    }
}
