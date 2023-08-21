package org.example.stockmarket.cash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "cashmodified")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class CashModified {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat
    private Date modified;
    private float balance;

}
