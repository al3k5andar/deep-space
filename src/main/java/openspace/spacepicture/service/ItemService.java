package openspace.spacepicture.service;

import openspace.spacepicture.api.v1.model.ItemDto;

import java.util.List;

public interface ItemService
{
    ItemDto todayImage();

    ItemDto yesterdayImage();

    ItemDto getByDate(String dateString);

    List<ItemDto> getInRange(String start, String end);
}
