package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.entity.chat.Message;
import com.kosign.phone_shop_api.entity.user.User;
import com.kosign.phone_shop_api.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }

//    @MessageMapping("/join")
//    @SendTo("/chatroom/public")
//    public Message join(@Payload Message message) {
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Integer userId = user.getId();
//
//        user.setFirstname(message.getReceiverName());
//        userRepository.save(user);
//        return message;
//    }
}
