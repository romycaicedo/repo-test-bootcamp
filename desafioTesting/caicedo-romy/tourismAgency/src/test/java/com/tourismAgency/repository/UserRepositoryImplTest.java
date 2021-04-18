package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.mockito.MockitoAnnotations.openMocks;

class UserRepositoryImplTest {

 UserRepository userRepository;
 private static final ObjectMapper objectMapper = new ObjectMapper();
 private static List<UserDTO> users;

 @BeforeEach
 public void setup(){
  openMocks(this);
  userRepository = new UserRepositoryImpl();
 }

 @Test
 @DisplayName("Check if user exists")
 void userExists(){
  boolean exists = userRepository.userExist("seba_gonzalez@unmail.com");
  //assert equals
  Assertions.assertEquals(true, exists);
 }


}
