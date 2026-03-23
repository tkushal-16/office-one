package com.spring.java.dao.model.sql;

import com.spring.java.common.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "user_leaves_record",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "year"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLeavesRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "sick_leaves")
    private Double sickLeaves = 5.0;

    @Column(name = "paid_leaves")
    private Double paidLeaves = 12.0;
}
