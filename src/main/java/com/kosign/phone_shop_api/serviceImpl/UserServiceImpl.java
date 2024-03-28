package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.common.api.StatusCode;
import com.kosign.phone_shop_api.exception.BusinessException;
import com.kosign.phone_shop_api.exception.EntityNotFoundException;
import com.kosign.phone_shop_api.payload.auth.ResetPasswordRequest;
import com.kosign.phone_shop_api.payload.user.AllUserResponse;
import com.kosign.phone_shop_api.payload.user.MainUserResponse;
import com.kosign.phone_shop_api.repository.UserRepository;
import com.kosign.phone_shop_api.payload.user.ChangePasswordRequest;
import com.kosign.phone_shop_api.entity.User;
import com.kosign.phone_shop_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public Object getAllUser(Pageable pageable) {

        Page<User> allUser = userRepository.getAllUser(pageable);
        List<AllUserResponse> allUserResponses = allUser.stream()
                .map(user -> AllUserResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstname())
                        .lastName(user.getLastname())
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .build()
                ).toList();
        return MainUserResponse.builder()
                .users(allUserResponses)
                .page(allUser)
                .build();
    }

    @Override
    public AllUserResponse getUserById(Integer id) {

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
        return AllUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public void deleteUserById(Integer id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BusinessException(StatusCode.BAD_CREDENTIALS);
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new BusinessException(StatusCode.VERIFY_PASSWORD);
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {

        var user = userRepository.findUserByEmail(resetPasswordRequest.getEmail());

        // check if the two new passwords are the same
        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword())) {
            throw new BusinessException(StatusCode.VERIFY_PASSWORD);
        }

        if (user.getIsPinCodeEnable()) {
            // update the password
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            user.setIsPinCodeEnable(false);

            // save the new password
            userRepository.save(user);
        } else {
            throw new BusinessException(StatusCode.PINCODE_REQUIRED);
        }

    }

    @Override
    public AllUserResponse getUserDetailsByCurrentUser() {

        // get user id by login user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getId();

        return getUserById(userId);
    }

}
