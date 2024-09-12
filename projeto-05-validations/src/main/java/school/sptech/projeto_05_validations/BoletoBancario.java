package school.sptech.projeto_05_validations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class BoletoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank // nem nulo, nem "", nem " "
    @Size(min = 3, max = 3)
    private String banco;

    @NotBlank
    @Size(min = 4, max = 4)
    private String agencia;

    @NotBlank
    @Size(min = 4, max = 4)
    private String conta;

    @NotBlank
    @Pattern(regexp = "\\d{5}\\.\\d{5} \\d{5}\\.\\d{6} \\d{5}\\.\\d{6} \\d \\d{14}")
    private String codigoBarra;

    @NotNull
    @DecimalMax("10000.00")
    @DecimalMin("0.1")
    private Double valor;

    @NotNull
    @Future
    private LocalDate dataVencimento;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomePagador;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    private BoletoBancario(){}

    public BoletoBancario(Integer id,String banco, String agencia, String conta, String codigoBarra, Double valor, LocalDate dataVencimento, String nomePagador, String email, String cpf) {
        this.id = id;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.codigoBarra = codigoBarra;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.nomePagador = nomePagador;
        this.email = email;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNomePagador() {
        return nomePagador;
    }

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
