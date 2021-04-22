package openspace.spacepicture.repositories;

import openspace.spacepicture.domain.Item;

import java.time.LocalDate;

public interface ItemRepository
{
    Item todayImage();

    Item yesterdayImage();

    Item getByDate(LocalDate localDate);

    Item[] getInRange(LocalDate start, LocalDate end);
}
