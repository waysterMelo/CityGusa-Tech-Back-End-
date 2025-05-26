package com.citygusa.com.citygusaapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "controle_operacional")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleOperacionalEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Não precisa de @NotNull aqui se @PrePersist sempre define
    private LocalDate createdAt;

    // Não precisa de @NotNull aqui se @PrePersist sempre define
    private String horas;

    // Exemplo de validação: 'a' deve ser um valor positivo, se aplicável
    @PositiveOrZero(message = "O valor de 'a' não pode ser negativo.")
    private Integer a;

    @NotNull(message = "Gaiola não pode ser nula.")
    @PositiveOrZero(message = "Gaiola não pode ser negativa.")
    private Integer gaiola;

    @NotNull(message = "Carga/Hora não pode ser nula.")
    @PositiveOrZero(message = "Carga/Hora não pode ser negativa.")
    private Integer cargaHora;

    @NotNull(message = "Carga Seca não pode ser nula.")
    @PositiveOrZero(message = "Carga Seca não pode ser negativa.")
    private Integer cargaSeca;

    @NotNull(message = "Vazão não pode ser nula.")
    @PositiveOrZero(message = "Vazão não pode ser negativa.")
    private Double vazao;

    @NotNull(message = "Pressão da Coroa não pode ser nula.")
    @PositiveOrZero(message = "Pressão da Coroa não pode ser negativa.")
    private Double pressaoCoroa;

    @NotNull(message = "Pressão do Topo não pode ser nula.")
    @PositiveOrZero(message = "Pressão do Topo não pode ser negativa.")
    private Double pressaoTopo;

    @NotNull(message = "Temperatura da Coroa não pode ser nula.")
    // Adicione @Min/@Max se houver limites razoáveis
    private Integer temperaturaCoroa;

    @NotNull(message = "Temperatura do Topo não pode ser nula.")
    // Adicione @Min/@Max se houver limites razoáveis
    private Integer temperaturaTopo;

    @NotNull(message = "Sonda não pode ser nula.")
    @PositiveOrZero(message = "Sonda não pode ser negativa.")
    private Double sonda;

    @NotNull(message = "Densidade (kg) não pode ser nula.")
    @Positive(message = "Densidade (kg) deve ser positiva.") // Densidade geralmente não é zero
    private Integer densidadeKg;

    // Campos calculados geralmente não precisam de validação de entrada,
    // mas se forem persistidos e tiverem regras, podem ter.
    // Ex: @Digits para precisão e escala se necessário.
    @Digits(integer = 10, fraction = 2, message = "Densidade Média deve ter no máximo 10 dígitos inteiros e 2 fracionários.")
    private Double densidadeMedia;

    @NotNull(message = "Umidade não pode ser nula.")
    @Min(value = 0, message = "Umidade não pode ser menor que 0.")
    @Max(value = 100, message = "Umidade não pode ser maior que 100.") // Assumindo percentual
    private Integer umidade;

    @Digits(integer = 5, fraction = 2, message = "Umidade Média deve ter no máximo 5 dígitos inteiros e 2 fracionários.")
    private BigDecimal umidadeMedia;

    @NotNull(message = "Gusa (kg) não pode ser nulo.")
    @Positive(message = "Gusa (kg) deve ser positivo.") // Gusa produzido geralmente não é zero
    private Integer gusaKg;

    // Acumulado Kilos é calculado, mas se tiver regras de valor ao ser persistido:
    @PositiveOrZero(message = "Acumulado Kilos não pode ser negativo.")
    @Digits(integer = 12, fraction = 2, message = "Acumulado Kilos inválido.")
    private Double acumuladoKilos; // Considere BigDecimal para valores monetários ou que exigem precisão

    @Digits(integer = 10, fraction = 4, message = "Peso Carvão Calculado inválido.")
    private BigDecimal pesoCarvaoCalc;

    @PositiveOrZero(message = "Acumulado Carga não pode ser negativo.")
    private Integer acumuladoCarga;

    @PositiveOrZero(message = "Acumulado Carga Seca não pode ser negativo.")
    private Integer acumuladoCargaSeca;

    @Digits(integer = 10, fraction = 2, message = "Média Hora Carga inválida.")
    private BigDecimal mediaHoraCarga;

    @Digits(integer = 10, fraction = 2, message = "RT inválido.")
    private BigDecimal rt;

    // Se fatorBaseDensidadeSeca é um valor que vem da entrada e não calculado:
    @NotNull(message = "Fator Base Densidade Seca não pode ser nulo.")
    @Positive(message = "Fator Base Densidade Seca deve ser positivo.")
    // Se for Double na entrada, ajuste o tipo aqui e no DTO. BigInteger é incomum para um fator.
    // Assumindo que era para ser Double ou BigDecimal na entrada e foi mapeado para BigInteger por engano.
    // Se for realmente BigInteger e obrigatório, @NotNull é suficiente.
    // Vou assumir que deveria ser BigDecimal para consistência com outros fatores/valores decimais.
    @Digits(integer = 5, fraction = 4, message = "Fator Base Densidade Seca inválido.")
    private BigDecimal fatorBaseDensidadeSeca; // Alterado de BigInteger para BigDecimal

    @Digits(integer = 10, fraction = 2, message = "Carvão Enfornado inválido.")
    private BigDecimal carvaoEnfornado;

    // Se for BigInteger, @NotNull pode ser suficiente se vier da entrada.
    // Se calculado, a validação é mais na lógica de cálculo.
    private BigInteger carvaoEnfornadoMedia;

    @Digits(integer = 10, fraction = 2, message = "Consumo Kg inválido.")
    private BigDecimal consumoKg;

    @Digits(integer = 10, fraction = 4, message = "Consumo Metros inválido.")
    private BigDecimal consumoMetros;

    @Digits(integer = 10, fraction = 2, message = "Positivo/Negativo inválido.")
    private BigDecimal positivoNegativo;


    @PrePersist
    protected void onCreate(){
        if (this.createdAt == null) { // Garante que só define se realmente for nulo
            this.createdAt = LocalDate.now();
        }
        if (this.horas == null) { // Garante que só define se realmente for nulo
            this.horas = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }
}
