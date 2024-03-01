package com.compassuol.sp.challenge.msuser.domain.service;

import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.model.UserAddress;
import com.compassuol.sp.challenge.msuser.domain.openFeing.MsAddressConsumer;
import com.compassuol.sp.challenge.msuser.domain.repository.UserAddressRepository;
import com.compassuol.sp.challenge.msuser.web.dto.CepDto;
import com.compassuol.sp.challenge.msuser.web.dto.mapper.CepMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.compassuol.sp.challenge.msuser.domain.jwt.JwtAuthenticationFilter.JWT_BEARER;


@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final JwtService service;

    private final MsAddressConsumer msAddressConsumer;

    private final UserAddressRepository userAddressRepository;

    @Transactional
    public void deleteAndSaveAddress(User oldUser, User newUser) {
            //arrange
            CepDto cep = CepMapper.toDto(newUser.getCep().replace("-", ""));
            String token = JWT_BEARER + service.GenerateToken(newUser.getEmail());

            //act
            Long addressId = msAddressConsumer.saveAddress(cep, token).getId();
            UserAddress address = userAddressRepository.findUserAddressByUserId(oldUser.getId());
            address.setAddressId(addressId);
    }
}
