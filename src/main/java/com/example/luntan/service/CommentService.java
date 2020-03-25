package com.example.luntan.service;


import com.example.luntan.dto.CommentDTO;
import com.example.luntan.model.User;
import org.springframework.stereotype.Service;


public interface CommentService {

    int insert(CommentDTO commentDTO, User user);

}
