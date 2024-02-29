package com.compassuol.sp.challenge.msuser.domain.openFeing;

import com.compassuol.sp.challenge.msuser.domain.exceptions.CepNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ErrorDecoderFeign implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 404 -> new CepNotFoundException("Cep not found");
            case 422 -> new RuntimeException("Cep not valid");
            default -> new Exception(response.reason());
        };
    }
}