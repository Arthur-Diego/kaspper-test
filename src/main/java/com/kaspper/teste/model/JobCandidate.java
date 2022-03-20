package com.kaspper.teste.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Setter
@Getter
@Entity
@Table(name = "tb_job_candidate", schema = "test_kaspper")
public class JobCandidate {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_job", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    value = ConstraintMode.CONSTRAINT,
                    name = "id_job",
                    foreignKeyDefinition = " foreign key(id_job) references tb_job(id)"))
    private Job job;

    @ManyToOne
    @JoinColumn(name = "id_candidate", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    value = ConstraintMode.CONSTRAINT,
                    name = "id_candidate",
                    foreignKeyDefinition = "foreign key(id_candidate) references tb_candidate(id)"))
    private Candidate candidate;

}
