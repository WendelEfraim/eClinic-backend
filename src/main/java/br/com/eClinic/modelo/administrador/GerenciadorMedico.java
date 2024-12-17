package br.com.eClinic.modelo.administrador;

import org.hibernate.annotations.SQLRestriction;

import br.com.eClinic.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GerenciadorMedico")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GerenciadorMedico extends EntidadeAuditavel {

    @Column
    private String nomeCompleto;

    @Column
    private String especialidades;

}
