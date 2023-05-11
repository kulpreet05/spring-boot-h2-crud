package com.ks.springbooth2crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JOB_STATUS")
public class JobStatus {
    @Id
    @GeneratedValue
    @Column(name = "JOB_ID")
    private long id;
    @Column(name = "JOB_NAME")
    private String name;
    @Column(name = "JOB_STATUS")
    private char status;
}
