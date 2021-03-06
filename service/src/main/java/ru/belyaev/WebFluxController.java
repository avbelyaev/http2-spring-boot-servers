package ru.belyaev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author anton.belyaev@bostongene.com
 */
@RestController
@RequestMapping("/sse")
class WebFluxController {

    private static Logger log = LoggerFactory.getLogger(UploadController.class);

    @GetMapping(
            value = "/mono",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Mono<String> mono() {
        log.info("Hello Webflux (mono) request!");
        return Mono.just("Hello WebFlux!");
    }

    @GetMapping(
            value = "/flux",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> randomStrings() {
        log.info("Webflux Flux request!");
        return Flux.interval(Duration.ofSeconds(2))
                .map(Long::intValue)
                .map(WebFluxController::generateRandomNumbericString);
    }

    private static String generateRandomNumbericString(int t) {
        return 0 == t % 2
                ? " tick_" + t
                : " tack_" + t;
    }
}
