package openspace.spacepicture.service;

import openspace.spacepicture.api.v1.mapper.ItemMapper;
import openspace.spacepicture.api.v1.model.ItemDto;
import openspace.spacepicture.domain.Item;
import openspace.spacepicture.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper mapper) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    @Override
    public ItemDto todayImage() {
        ItemDto itemDto= mapper.itemToIdemDto(itemRepository.todayImage());
        checkIsVideo(itemDto);
        return itemDto;
    }

    @Override
    public ItemDto yesterdayImage() {
        ItemDto itemDto= mapper.itemToIdemDto(itemRepository.yesterdayImage());
        checkIsVideo(itemDto);
        return itemDto;
    }

    @Override
    public ItemDto getByDate(String dateString) {

        if(dateString.isEmpty())
            throw new DateTimeException("Date field must be filled");
        LocalDate localDate= convertStringToDate(dateString);

        ItemDto itemDto= mapper.itemToIdemDto(itemRepository.getByDate(localDate));
        checkIsVideo(itemDto);

        return itemDto;
    }

    @Override
    public List<ItemDto> getInRange(String start, String end) {

        if(start.isEmpty()|| end.isEmpty())
            throw new DateTimeException("All fields must be filled");

        LocalDate startDate= convertStringToDate(start);
        LocalDate endDate= convertStringToDate(end);

        return getDtoList(itemRepository.getInRange(startDate, endDate));
    }

    private static void checkIsVideo(ItemDto itemDto){
        Logger logger= Logger.getGlobal();
        if(itemDto.getUrl().contains("www.youtube.com")) {
            itemDto.setVideo(true);
            logger.info(itemDto.getUrl());
        }
    }

    private  List<ItemDto> getDtoList(Item[] array){
        List<ItemDto> items= new ArrayList<>();

        for (Item item: array){
            ItemDto itemDto= mapper.itemToIdemDto(item);
            checkIsVideo(itemDto);
            items.add(itemDto);
        }
        return items;
    }

    private static LocalDate convertStringToDate(String date){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,formatter);
    }
}
