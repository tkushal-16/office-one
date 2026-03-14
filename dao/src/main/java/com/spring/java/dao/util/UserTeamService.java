package com.spring.java.dao.util;



import com.spring.java.common.user.User;
import com.spring.java.common.user.UserRole;
import com.spring.java.dao.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTeamService {

    private final UserRepository userRepository;

    public List<User> getTeamMembers(User currentUser) {

        if (currentUser.getRole() == UserRole.TEAM_LEAD ||
            currentUser.getRole() == UserRole.ADMIN) {

            return userRepository.findBySupervisor(currentUser);
        }

        return Collections.emptyList();
    }
}