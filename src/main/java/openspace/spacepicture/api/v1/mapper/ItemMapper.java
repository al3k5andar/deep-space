package openspace.spacepicture.api.v1.mapper;

import openspace.spacepicture.api.v1.model.ItemDto;
import openspace.spacepicture.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemMapper INSTANCE= Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "url",target = "url")
    ItemDto itemToIdemDto(Item item);

    Item itemDtoToItem(ItemDto itemDto);
}
