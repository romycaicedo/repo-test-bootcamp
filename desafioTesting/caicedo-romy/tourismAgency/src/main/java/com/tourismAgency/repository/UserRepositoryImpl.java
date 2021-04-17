package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{



    @Override
    public boolean userExist(String user){
        boolean exist = false;
        List<UserDTO> userDTOS = loadData();
        for (UserDTO dto : userDTOS) {
            if(dto.getMail().equals(user))
                exist = true;
        }
        return exist;
    }

    private List<UserDTO> loadData()  { File file = null;
        try{
            file = ResourceUtils.getFile("classpath:dbUsers.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDTO>> typeRef = new TypeReference<>() {};
        List<UserDTO> userDTO = null;

        try {
            userDTO = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return userDTO;

    }
}
