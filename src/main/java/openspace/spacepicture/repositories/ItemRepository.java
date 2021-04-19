package openspace.spacepicture.repositories;

import openspace.spacepicture.domain.Item;

import java.util.List;

public interface ItemRepository
{
    Item todayImage();

    List<Item> showAll();
}
