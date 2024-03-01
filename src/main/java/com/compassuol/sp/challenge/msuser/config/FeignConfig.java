package com.compassuol.sp.challenge.msuser.config;

import com.compassuol.sp.challenge.msuser.domain.openFeing.ErrorDecoderFeign;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoderFeign errorDecoder() {
        return new ErrorDecoderFeign();
    }

}
