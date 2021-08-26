package com.example.demo.entities.wfi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wfi_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WfiData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
