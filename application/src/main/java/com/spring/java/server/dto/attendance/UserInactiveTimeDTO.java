package com.spring.java.server.dto.attendance;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInactiveTimeDTO {

    private Long id;
    private String pauseTime;
    private String resumeTime;
}