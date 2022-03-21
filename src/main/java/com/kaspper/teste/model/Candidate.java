package com.kaspper.teste.model;

import com.kaspper.teste.model.type.CandidateProfileEnum;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_candidate", schema = "test_kaspper")
public class Candidate {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile")
    private CandidateProfileEnum profile;

    @Column(name = "salary_expectation")
    private Double salaryExpectation;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<JobCandidate> job;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "curriculum")
    private byte[] curriculum;

}
