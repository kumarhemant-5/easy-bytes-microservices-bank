package com.eazybytes.accounts.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter @Setter @ToString  @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

//    public Accounts(LocalDateTime createdAt, String createdBy,LocalDateTime updatedAt,String updatedBy, Long customerId, Long accountNumber, String accountType, String branchAddress) {
//        super.setCreatedAt(createdAt);
//        super.setCreatedBy(createdBy);
//        super.setCreatedAt(createdAt);
//        super.setCreatedBy(createdBy);
//        this.customerId = customerId;
//        this.accountNumber = accountNumber;
//        this.accountType = accountType;
//        this.branchAddress = branchAddress;
//    }
}
