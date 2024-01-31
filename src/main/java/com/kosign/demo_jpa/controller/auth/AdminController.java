package com.kosign.demo_jpa.controller.auth;

import com.kosign.demo_jpa.controller.PhoneShopResController;
import com.kosign.demo_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController extends PhoneShopResController {

    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping("/deleteUserById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ok();
    }

}
