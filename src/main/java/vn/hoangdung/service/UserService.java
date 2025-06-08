package vn.hoangdung.service;

import vn.hoangdung.dto.request.UserRequestDTO;
import vn.hoangdung.dto.response.PageResponse;
import vn.hoangdung.dto.response.UserDetailResponse;
import vn.hoangdung.util.UserStatus;

public interface UserService {

    long saveUser(UserRequestDTO request);

    void updateUser(long userId, UserRequestDTO request);

    void changeStatus(long userId, UserStatus status);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy);

    PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts);

    PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy);

    PageResponse<?> advanceSearchWithCriteria(int pageNo, int pageSize, String sortBy, String address, String... search);

}

