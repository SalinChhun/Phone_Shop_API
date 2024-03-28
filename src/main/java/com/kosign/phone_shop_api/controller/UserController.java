package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.payload.MultiSortBuilder;
import com.kosign.phone_shop_api.payload.auth.ResetPasswordRequest;
import com.kosign.phone_shop_api.payload.user.ChangePasswordRequest;
import com.kosign.phone_shop_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends PhoneShopResController {

    private final UserService userService;

    @GetMapping("/getAllUser")
    ResponseEntity<?> getAllUsers(
            @RequestParam(name = "page_number", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort_column", required = false, defaultValue = "id:desc") String sortColumns
    ) {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(userService.getAllUser(pageRequest));
    }

    @GetMapping("/getUserById/{id}")
    ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ok(userService.getUserById(id));
    }

    @GetMapping("/getUserByCurrentUser")
    public ResponseEntity<?> getUserByCurrentUser() {
        return ok(userService.getUserDetailsByCurrentUser());
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest resetPasswordRequest
    ) {
        userService.resetPassword(resetPasswordRequest);
        return ok();
    }

}
