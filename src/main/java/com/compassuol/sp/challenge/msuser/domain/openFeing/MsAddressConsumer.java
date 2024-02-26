package com.compassuol.sp.challenge.msuser.domain.openFeing;

import com.compassuol.sp.challenge.msuser.web.dto.CepDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "addressConsumer", url = "localhost:8081/v1/address/")
public interface MsAddressConsumer {

    @PostMapping
    void saveAddress(@Valid @RequestBody CepDto dto);

}
