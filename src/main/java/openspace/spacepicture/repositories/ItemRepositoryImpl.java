package openspace.spacepicture.repositories;

import openspace.spacepicture.domain.Item;
import openspace.spacepicture.exceptions.BadDateRangeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public Item yesterdayImage() {
        ZoneId zoneId= ZoneId.of("America/New_York");
        UriComponentsBuilder uriBuilder =  UriComponentsBuilder.fromUriString(apiUrl);
        uriBuilder.queryParam("api_key", apiKey);
        uriBuilder.queryParam("date", LocalDate.now(zoneId).minusDays(1));
        return restTemplate.getForObject(uriBuilder.toUriString(), Item.class);
    }

    @Override
    public Item getByDate(LocalDate localDate) {

        checkIsDateValid(localDate);

        UriComponentsBuilder uriBuilder= UriComponentsBuilder.fromUriString(apiUrl);
        uriBuilder.queryParam("api_key", apiKey);
        uriBuilder.queryParam("date",localDate);

        return restTemplate.getForObject(uriBuilder.toUriString(),Item.class);
    }

    @Override
    public Item[] getInRange(LocalDate start, LocalDate end) {
        checkIsDateValid(start);
        checkIsDateValid(end);

        UriComponentsBuilder uriBuilder= UriComponentsBuilder.fromUriString(apiUrl);
        uriBuilder.queryParam("api_key", apiKey);
        uriBuilder.queryParam("start_date", start);
        uriBuilder.queryParam("end_date", end);

        return restTemplate.getForObject(uriBuilder.toUriString(), Item[].class);
    }

    private static void checkIsDateValid(LocalDate date){
        //        Check if the chosen date is in future
        int inFuture= date.compareTo(LocalDate.now());

//        minDate is date date when did first image is taken
        LocalDate minDate= LocalDate.of(1995, 6, 16);

        if(minDate.compareTo(date)> 0 || inFuture> 0) {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd MMM yyyy");
            throw new BadDateRangeException("Date must be between "
                    + LocalDate.of(1995, 6, 16).format(formatter) +
                    " and " + LocalDate.now().format(formatter));
        }
    }
}
