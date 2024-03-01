package com.compassuol.sp.challenge.msuser.domain.openFeing;

import com.compassuol.sp.challenge.msuser.config.FeignConfig;
import com.compassuol.sp.challenge.msuser.web.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msuser.web.dto.CepDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "addressConsumer", url = "localhost:8081/v1/address/", configuration = FeignConfig.class)
public interface MsAddressConsumer {

    @PostMapping
    AddressResponseDto saveAddress(@Valid @RequestBody CepDto dto, @RequestHeader("Authorization") String token);

}
