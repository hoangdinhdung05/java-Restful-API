package vn.hoangdung.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.hoangdung.dto.request.UserRequestDTO;
import vn.hoangdung.exception.ResourceNotFoundException;
import vn.hoangdung.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public int addUser(UserRequestDTO requestDTO) {
        log.info("Adding user with first name: {}", requestDTO.getFirstName());

        if(!requestDTO.getFirstName().equalsIgnoreCase("Tay")) {
            log.error("User with first name 'Tay' is not allowed");
            throw new ResourceNotFoundException("User with first name 'Tay' is not allowed");
        }
        return 0;
    }
}
