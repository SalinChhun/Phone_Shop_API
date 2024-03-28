package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.payload.auth.ResetPasswordRequest;
import com.kosign.phone_shop_api.payload.user.AllUserResponse;
import com.kosign.phone_shop_api.payload.user.ChangePasswordRequest;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface UserService {

    Object getAllUser(Pageable pageable);

    AllUserResponse getUserById(Integer id);

    void deleteUserById(Integer id);

    public void changePassword(ChangePasswordRequest request, Principal connectedUser);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    AllUserResponse getUserDetailsByCurrentUser();

}
