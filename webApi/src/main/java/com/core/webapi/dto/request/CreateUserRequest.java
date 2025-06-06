package com.core.webapi.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String email;
    private String fullName;
}
