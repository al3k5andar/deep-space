package openspace.spacepicture.service;

import openspace.spacepicture.api.v1.mapper.ItemMapper;
import openspace.spacepicture.api.v1.model.ItemDto;
import openspace.spacepicture.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemCollectionRepository;
    private final ItemMapper mapper;

    public ItemServiceImpl(ItemRepository itemCollectionRepository, ItemMapper mapper) {
        this.itemCollectionRepository = itemCollectionRepository;
        this.mapper = mapper;
    }

    @Override
    public ItemDto todayImage() {
        return mapper.itemToIdemDto(itemCollectionRepository.todayImage());
    }
}
