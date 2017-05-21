package com.swiftwayz.domain.billing;

import com.swiftwayz.domain.util.BaseEntity;
import com.swiftwayz.domain.util.Status;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sydney on 2017/05/21.
 */
@Entity
@Table(name = "banking_transaction")
public class BankingTransaction extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="banking_transaction_transaction_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "type")
    private String type;

    @Column(name = "initiator")
    private String initiator;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "status")
    private String status;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Status getStatus() {
        return Status.getStatus(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
