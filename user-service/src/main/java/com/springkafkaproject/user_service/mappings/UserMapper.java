package com.springkafkaproject.user_service.mappings;

import com.springkafkaproject.user_service.dto.requestDTO.UserRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.UserResponseDTO;
import com.springkafkaproject.user_service.entities.Alert;
import com.springkafkaproject.user_service.entities.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Request DTO to Entity (for creating or updating)
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

    // Entity to Response DTO (for returning the user with alert IDs)
    @Mapping(target = "alertIds", source = "alerts")
    UserResponseDTO userToUserResponseDTO(User user);

    @IterableMapping(elementTargetType = UserResponseDTO.class)
    List<UserResponseDTO> userListtoUserResponseDTOList(List<User> users);

    default List<Long> mapAlertsToAlertIds(List<Alert> alerts) {
        return alerts.stream()
                .map(Alert::getId)
                .collect(Collectors.toList());
    }
}

