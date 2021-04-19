package openspace.spacepicture.repositories;

import openspace.spacepicture.domain.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ItemRepositoryImpl implements ItemRepository
{
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public ItemRepositoryImpl(RestTemplate restTemplate,
                              @Value("${api_url}") String apiUrl,
                              @Value("${api_key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public Item todayImage(){
        Logger logger= Logger.getGlobal();
        UriComponentsBuilder uriBuilder= UriComponentsBuilder.fromUriString(apiUrl);
        uriBuilder.queryParam("api_key", apiKey);

        logger.info(uriBuilder.toUriString());

        return restTemplate.getForObject(uriBuilder.toUriString(),Item.class);
    }

    @Override
    public List<Item> showAll() {
        return null;
    }
}
