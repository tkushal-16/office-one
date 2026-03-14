package com.spring.java.dao.model.sql;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_inactive_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInactiveTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to attendance
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false)
    private UserAttendanceEntity attendance;

    @Column(name = "pause_time", nullable = false)
    private LocalDateTime pauseTime;

    @Column(name = "resume_time")
    private LocalDateTime resumeTime;
}