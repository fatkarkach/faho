package com.example.demo.centralbank.reqObjects;

import com.example.demo.centralbank.models.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterForm {
    User user;
    MultipartFile file;

}
