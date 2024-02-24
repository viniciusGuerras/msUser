package com.compassuol.sp.challenge.msuser.openFeing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "addressConsumer", url = "localhost:8081/address/")
public interface MsAddressConsumer {

    @PostMapping(value = "{cep}")
    Long saveAddress(@PathVariable String cep);

}
