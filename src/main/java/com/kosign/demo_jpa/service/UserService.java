package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.User;
import com.kosign.demo_jpa.payload.auth.ResetPasswordRequest;
import com.kosign.demo_jpa.payload.user.AllUserResponse;
import com.kosign.demo_jpa.payload.user.ChangePasswordRequest;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface UserService {

    Object getAllUser(Pageable pageable);

    AllUserResponse getUserById(Integer id);

    void deleteUserById(Integer id);

    public void changePassword(ChangePasswordRequest request, Principal connectedUser);

    void resetPassword(ResetPasswordRequest resetPasswordRequest, Principal connectedUser);

    AllUserResponse getUserDetailsByCurrentUser();

}
