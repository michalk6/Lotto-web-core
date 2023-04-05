package pl.lotto.infrastructure.services.winningnumberservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfiguration {

    @Bean
    public WebClient winningNumberWebClient(
            @Value("${lotto.number-generator-base-url}") String numberGeneratorBaseUrl,
            WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(numberGeneratorBaseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
