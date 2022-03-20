package com.kaspper.teste.model;

import com.kaspper.teste.model.type.JobTypeEnum;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_job", schema = "test_kaspper")
public class Job {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private JobTypeEnum type;

    @Column(name = "salary")
    private Double salary;

}
