package vn.hoangdung.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.hoangdung.config.Translator;
import vn.hoangdung.dto.request.AddressDTO;
import vn.hoangdung.dto.request.UserRequestDTO;
import vn.hoangdung.dto.response.PageResponse;
import vn.hoangdung.dto.response.UserDetailResponse;
import vn.hoangdung.exception.ResourceNotFoundException;
import vn.hoangdung.entity.Address;
import vn.hoangdung.entity.User;
import vn.hoangdung.repository.SearchRepository;
import vn.hoangdung.repository.UserRepository;
import vn.hoangdung.service.UserService;
import vn.hoangdung.util.UserStatus;
import vn.hoangdung.util.UserType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static vn.hoangdung.util.AppConst.SORT_BY;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SearchRepository searchRepository;

    /**
     * Save new user to DB
     *
     * @param request
     * @return userId
     */
    @Override
    public long saveUser(UserRequestDTO request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .phone(request.getPhone())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .status(request.getStatus())
                .type(UserType.valueOf(request.getType().toUpperCase()))
                .addresses(convertToAddress(request.getAddresses()))
                .build();
        request.getAddresses().forEach(a ->
                user.saveAddress(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build()));
        userRepository.save(user);

        log.info("User has added successfully, userId={}", user.getId());

        return user.getId();
    }

    /**
     * Update user by userId
     *
     * @param userId
     * @param request
     */
    @Override
    public void updateUser(long userId, UserRequestDTO request) {
        User user = getUserById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        if (!request.getEmail().equals(user.getEmail())) {
            // check email from database if not exist then allow update email otherwise throw exception
            user.setEmail(request.getEmail());
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        user.setType(UserType.valueOf(request.getType().toUpperCase()));
        user.setAddresses(convertToAddress(request.getAddresses()));
        userRepository.save(user);

        log.info("User has updated successfully, userId={}", userId);
    }

    /**
     * Change status of user by userId
     *
     * @param userId
     * @param status
     */
    @Override
    public void changeStatus(long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);

        log.info("User status has changed successfully, userId={}", userId);
    }

    /**
     * Delete user by userId
     *
     * @param userId
     */
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
        log.info("User has deleted permanent successfully, userId={}", userId);
    }

    /**
     * Get user detail by userId
     *
     * @param userId
     * @return
     */
    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return UserDetailResponse.builder()
                .id(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .phone(user.getPhone())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .type(user.getType().name())
                .build();
    }

    /**
     * Get all user per pageNo and pageSize
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }

        List<Sort.Order> sorts = new ArrayList<>();

        if (StringUtils.hasLength(sortBy)) {
            // firstName:asc|desc
            Pattern pattern = Pattern.compile(SORT_BY);
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));

        Page<User> users = (Page<User>) userRepository.findAll(pageable);
        List<UserDetailResponse> response = users.stream().map(user -> UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build()).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(response)
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }

        List<Sort.Order> orders = new ArrayList<>();

        if (sorts != null) {
            for (String sortBy : sorts) {
                log.info("sortBy: {}", sortBy);
                // firstName:asc|desc
                Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
                Matcher matcher = pattern.matcher(sortBy);
                if (matcher.find()) {
                    if (matcher.group(3).equalsIgnoreCase("asc")) {
                        orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                    } else {
                        orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                    }
                }
            }
        }

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(orders));

        Page<User> users = userRepository.findAll(pageable);
        List<UserDetailResponse> response = users.stream().map(user -> UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build()).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(response)
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy) {
        return searchRepository.searchUser(pageNo, pageSize, search, sortBy);
    }


    /**
     * Get user by userId
     *
     * @param userId
     * @return User
     */
    private User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale("user.not.found")));
    }

    /**
     * Covert Set<AddressDTO> to Set<Address>
     *
     * @param addresses
     * @return Set<Address>
     */
    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        Set<Address> result = new HashSet<>();
        addresses.forEach(a ->
                result.add(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build())
        );
        return result;
    }
}
