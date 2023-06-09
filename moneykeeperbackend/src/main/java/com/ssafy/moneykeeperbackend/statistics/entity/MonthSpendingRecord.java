package com.ssafy.moneykeeperbackend.statistics.entity;

import com.ssafy.moneykeeperbackend.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"member_member_id", "ymonth"})
})
public class MonthSpendingRecord {
    @Id
    @GeneratedValue
    private Long id;
    private int amount;
    private int groupAvg;
    @ManyToOne
    private Member member;
    private LocalDate ymonth;

    @Builder
    public MonthSpendingRecord(Member member, LocalDate ymonth, int amount, int groupAvg) {
        this.member = member;
        this.amount = amount;
        this.groupAvg = groupAvg;
        this.ymonth = ymonth;
    }
}
