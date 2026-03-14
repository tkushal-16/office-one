package com.spring.java.dao.model.sql;

import com.spring.java.common.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "user_attendance",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "date"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    // Equivalent to related_name='inactive_periods'
    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInactiveTimeEntity> inactivePeriods;
}