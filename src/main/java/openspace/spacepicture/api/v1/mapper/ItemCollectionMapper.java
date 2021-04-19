package openspace.spacepicture.api.v1.mapper;

import openspace.spacepicture.api.v1.model.ItemCollectionDto;
import openspace.spacepicture.domain.ItemCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemCollectionMapper {

    ItemCollection INSTANCE= Mappers.getMapper(ItemCollection.class);

    @Mapping(source = "data",target = "data")
    ItemCollectionDto itemCollectionToItemCollectionDto(ItemCollection itemCollection);

    ItemCollection itemCollectionDtoToItemCollection(ItemCollectionDto itemCollectionDto);
}
