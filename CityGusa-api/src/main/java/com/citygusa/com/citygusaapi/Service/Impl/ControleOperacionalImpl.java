package com.citygusa.com.citygusaapi.Service.Impl;

import com.citygusa.com.citygusaapi.Dto.ControleOperacionalDto;
import com.citygusa.com.citygusaapi.Entity.ControleOperacionalEntity;
import com.citygusa.com.citygusaapi.Exceptions.NoOperacionalFound;
import com.citygusa.com.citygusaapi.Mapper.ControleOperacionalMapper;
import com.citygusa.com.citygusaapi.Repository.ControleOperacionalRepository;
import com.citygusa.com.citygusaapi.Service.ControleOperacionalService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ControleOperacionalImpl implements ControleOperacionalService {

    private static final Logger logger = LoggerFactory.getLogger(ControleOperacionalImpl.class);

    private final ControleOperacionalRepository controleOperacionalRepository;
    private final ControleOperacionalMapper controleOperacionalMapper;

    @Autowired
    public ControleOperacionalImpl(ControleOperacionalRepository controleOperacionalRepository, ControleOperacionalMapper controleOperacionalMapper) {
        this.controleOperacionalRepository = controleOperacionalRepository;
        this.controleOperacionalMapper = controleOperacionalMapper;
    }

    @Override
    public Integer getCargaHora(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaHoraByCreatedAt(createdAt);
    }

    @Override
    public Integer getGusaKg(LocalDate createdAt) {
        return controleOperacionalRepository.findGusaKgByCreatedAt(createdAt);
    }

    @Override
    public Integer getCargaAcumulada(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaAcumulado(createdAt);
    }

    @Override
    public Integer getCargaAcumuladaSeca(LocalDate createdAt) {
        return controleOperacionalRepository.findCargaAcumuladoSeca(createdAt);
    }

    @Override
    public BigDecimal getUmidadeMedia(LocalDate createdAt) {
        return controleOperacionalRepository.findMediaUmidade(createdAt);
    }


    @Override
    public Double getDensidadeKgMedia(LocalDate createdAt) {
        return controleOperacionalRepository.findMediaDensidade(createdAt);
    }

    @Override
    public BigDecimal getMediaHora(LocalDate data) {
        return controleOperacionalRepository.findMediaHora(data);
    }

    @Override
    public Integer getUltimaDensidade(LocalDate data) {
        return controleOperacionalRepository.findUltimaDensidade(data);
    }

    @Override
    public BigInteger getMediaCarvaoEnfornado(LocalDate data) {
        return controleOperacionalRepository.findMediaPesoCarvao(data);
    }

    @Override
    @Transactional
    public Optional<ControleOperacionalDto> save(ControleOperacionalEntity entity) {
        ControleOperacionalEntity rs; // Entidade que será trabalhada e salva

        // ⚠️ Validação inicial crucial
        if (entity == null) {
            logger.error("Tentativa de salvar uma entidade nula. Operação abortada.");
            return Optional.empty();
        }
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDate.now()); // Ou outra lógica para definir a data
            logger.warn("CreatedAt da entidade recebida era nulo, definindo para data atual: {}", entity.getCreatedAt());
        }

        try {
            // Salvar entidade inicialmente para garantir ID e estado inicial no BD
            rs = controleOperacionalRepository.save(entity);
            logger.info("Entidade ID: {} salva/atualizada inicialmente.", rs.getId());
        } catch (Exception e) {
            logger.error("Erro crítico ao salvar entidade inicial ou ao definir createdAt. Entidade: {}. Detalhes: {}", entity, e.getMessage(), e);
            return Optional.empty();
        }

        // --- Início dos Cálculos ---
        try {
            rs.setAcumuladoCarga(getCargaAcumulada(rs.getCreatedAt()));
            logger.info("ID: {} - AcumuladoCarga: {}", rs.getId(), rs.getAcumuladoCarga());

            rs.setAcumuladoCargaSeca(getCargaAcumuladaSeca(rs.getCreatedAt()));
            logger.info("ID: {} - AcumuladoCargaSeca: {}", rs.getId(), rs.getAcumuladoCargaSeca());

            rs.setMediaHoraCarga(getMediaHora(rs.getCreatedAt()));
            logger.info("ID: {} - MediaHoraCarga: {}", rs.getId(), rs.getMediaHoraCarga());
        } catch (Exception e) {
            logger.error("ID: {} - Erro ao calcular acumulados ou média/hora. Detalhes: {}", rs.getId(), e.getMessage(), e);
            // Definir valores padrão pode ser uma opção, ou deixar que o fluxo continue
            // e os campos fiquem com os valores retornados pelos métodos auxiliares (que já devem tratar erros)
        }

        try {
            if (rs.getMediaHoraCarga() == null) {
                logger.warn("ID: {} - MediaHoraCarga em 'rs' é nulo. Não é possível calcular RT. Definindo RT como ZERO.", rs.getId());
                rs.setRt(BigDecimal.ZERO);
            } else if (entity.getGusaKg() == null) { // Usando 'entity' conforme o código original
                logger.warn("ID: {} - GusaKg da entidade de entrada é nulo. Não é possível calcular RT. Definindo RT como ZERO.", rs.getId());
                rs.setRt(BigDecimal.ZERO);
            } else {
                BigDecimal horasDoDia = BigDecimal.valueOf(24);
                BigDecimal gusaConvertido = BigDecimal.valueOf(entity.getGusaKg()); // Pode dar NPE se entity.getGusaKg() for null
                BigDecimal rt = rs.getMediaHoraCarga().multiply(gusaConvertido).multiply(horasDoDia)
                        .setScale(2, RoundingMode.HALF_UP);
                rs.setRt(rt);
                logger.info("ID: {} - RT: {}", rs.getId(), rs.getRt());
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular RT. MediaHoraCarga (rs): {}, GusaKg (entity): {}. Detalhes: {}", rs.getId(), rs.getMediaHoraCarga(), entity.getGusaKg(), npe.getMessage(), npe);
            rs.setRt(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular RT. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setRt(BigDecimal.ZERO);
        }

        try {
            rs.setUmidadeMedia(getUmidadeMedia(rs.getCreatedAt()));
            logger.info("ID: {} - UmidadeMedia: {}", rs.getId(), rs.getUmidadeMedia());

            rs.setDensidadeMedia(getDensidadeKgMedia(rs.getCreatedAt()));
            logger.info("ID: {} - DensidadeMedia: {}", rs.getId(), rs.getDensidadeMedia());
        } catch (Exception e) {
            logger.error("ID: {} - Erro ao calcular médias de umidade ou densidade. Detalhes: {}", rs.getId(), e.getMessage(), e);
        }

        try {
            // Calcular carvão peso CALC
            if (entity.getFatorBaseDensidadeSeca() == null) {
                logger.warn("ID: {} - FatorBaseDensidadeSeca (entity) é nulo. Definindo PesoCarvaoCalc como ZERO.", rs.getId());
                rs.setPesoCarvaoCalc(BigDecimal.ZERO);
            } else if (entity.getUmidade() == null) {
                logger.warn("ID: {} - Umidade (entity) é nula. Definindo PesoCarvaoCalc como ZERO.", rs.getId());
                rs.setPesoCarvaoCalc(BigDecimal.ZERO);
            } else {
                BigDecimal fatorDecimal = new BigDecimal(entity.getFatorBaseDensidadeSeca().toString()); // Usar toString() para evitar problemas com construtor de Double
                BigDecimal umidadeBD = BigDecimal.valueOf(entity.getUmidade());
                BigDecimal sete = BigDecimal.valueOf(7);
                BigDecimal cem = BigDecimal.valueOf(100);

                BigDecimal diferencaUmidadePercentual = umidadeBD.compareTo(sete) > 0
                        ? umidadeBD.subtract(sete).divide(cem, 2, RoundingMode.HALF_UP)
                        : BigDecimal.ZERO;

                rs.setPesoCarvaoCalc(fatorDecimal.add(fatorDecimal.multiply(diferencaUmidadePercentual)));
                logger.info("ID: {} - PesoCarvaoCalc: {}", rs.getId(), rs.getPesoCarvaoCalc());
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular PesoCarvaoCalc. FatorBaseDensidadeSeca (entity): {}, Umidade (entity): {}. Detalhes: {}", rs.getId(), entity.getFatorBaseDensidadeSeca(), entity.getUmidade(), npe.getMessage(), npe);
            rs.setPesoCarvaoCalc(BigDecimal.ZERO);
        } catch (NumberFormatException nfe) {
            logger.error("ID: {} - NumberFormatException ao converter FatorBaseDensidadeSeca para BigDecimal. Valor: '{}'. Detalhes: {}", rs.getId(), entity.getFatorBaseDensidadeSeca(), nfe.getMessage(), nfe);
            rs.setPesoCarvaoCalc(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular PesoCarvaoCalc. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setPesoCarvaoCalc(BigDecimal.ZERO);
        }

        try {
            // Calcular carvão enfornado
            if (entity.getAcumuladoKilos() == null) {
                logger.warn("ID: {} - AcumuladoKilos (entity) é nulo. Definindo CarvaoEnfornado como ZERO.", rs.getId());
                rs.setCarvaoEnfornado(BigDecimal.ZERO);
            } else if (entity.getCargaHora() == null) {
                logger.warn("ID: {} - CargaHora (entity) é nula. Definindo CarvaoEnfornado como ZERO.", rs.getId());
                rs.setCarvaoEnfornado(BigDecimal.ZERO);
            } else if (entity.getCargaHora().intValue() == 0) {
                logger.warn("ID: {} - CargaHora (entity) é zero. Divisão por zero evitada. Definindo CarvaoEnfornado como ZERO.", rs.getId());
                rs.setCarvaoEnfornado(BigDecimal.ZERO);
            } else {
                BigDecimal carvaoAcumulado = BigDecimal.valueOf(entity.getAcumuladoKilos());
                BigDecimal cargaHoraBD = BigDecimal.valueOf(entity.getCargaHora());
                rs.setCarvaoEnfornado(carvaoAcumulado.divide(cargaHoraBD, 2, RoundingMode.HALF_UP));
                logger.info("ID: {} - CarvaoEnfornado: {}", rs.getId(), rs.getCarvaoEnfornado());
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular CarvaoEnfornado. AcumuladoKilos (entity): {}, CargaHora (entity): {}. Detalhes: {}", rs.getId(), entity.getAcumuladoKilos(), entity.getCargaHora(), npe.getMessage(), npe);
            rs.setCarvaoEnfornado(BigDecimal.ZERO);
        } catch (ArithmeticException ae) {
            logger.error("ID: {} - ArithmeticException (divisão por zero) ao calcular CarvaoEnfornado. CargaHora (entity) era {}. Detalhes: {}", rs.getId(), entity.getCargaHora(), ae.getMessage(), ae);
            rs.setCarvaoEnfornado(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular CarvaoEnfornado. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setCarvaoEnfornado(BigDecimal.ZERO);
        }

        // ⚠️ O save intermediário pode ser removido se o método for @Transactional
        // e todos os cálculos forem feitos antes do save final.
        // Se mantido, envolver em try-catch também.
        try {
            rs = controleOperacionalRepository.save(rs);
            logger.info("ID: {} - Entidade salva após cálculos intermediários.", rs.getId());
        } catch (Exception e) {
            logger.error("ID: {} - Erro ao salvar entidade após cálculos intermediários. Alguns valores podem não ter sido persistidos. Detalhes: {}", rs.getId(), e.getMessage(), e);
            // Considerar se deve retornar aqui ou tentar continuar.
        }

        try {
            BigInteger mediaCarvaoEnfornado = getMediaCarvaoEnfornado(rs.getCreatedAt()); // Método auxiliar já trata nulo
            rs.setCarvaoEnfornadoMedia(mediaCarvaoEnfornado);
            logger.info("ID: {} - MediaCarvaoEnfornado: {}", rs.getId(), rs.getCarvaoEnfornadoMedia());
        } catch (Exception e) {
            logger.error("ID: {} - Erro ao calcular média de carvão enfornado. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setCarvaoEnfornadoMedia(BigInteger.ZERO);
        }

        try {
            // Calcular consumo em kilos por tonelada
            Integer cargaInput = entity.getCargaHora();
            Integer gusaInput = entity.getGusaKg();
            Double acumuloKgInput = entity.getAcumuladoKilos(); // Vem da entidade original

            if (gusaInput == null || cargaInput == null || acumuloKgInput == null) {
                logger.warn("ID: {} - Um ou mais valores para cálculo de ConsumoKg são nulos (Gusa: {}, Carga: {}, AcumuloKg: {}). Definindo ConsumoKg como ZERO.", rs.getId(), gusaInput, cargaInput, acumuloKgInput);
                rs.setConsumoKg(BigDecimal.ZERO);
            } else if (gusaInput == 0 || cargaInput == 0) {
                logger.warn("ID: {} - Gusa ({}) ou Carga ({}) é zero. Evitando divisão por zero para ConsumoKg. Definindo como ZERO.", rs.getId(), gusaInput, cargaInput);
                rs.setConsumoKg(BigDecimal.ZERO);
            } else {
                // (acumuloKg / (gusa * carga)) * 1000
                double consumoCalculado = (acumuloKgInput / (gusaInput.doubleValue() * cargaInput.doubleValue())) * 1000.0;
                BigDecimal kilosTonelada = BigDecimal.valueOf(consumoCalculado).setScale(0, RoundingMode.HALF_UP);
                rs.setConsumoKg(kilosTonelada);
                logger.info("ID: {} - ConsumoKg (kg/t): {}", rs.getId(), rs.getConsumoKg());
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular ConsumoKg. Carga (entity): {}, Gusa (entity): {}, AcumuloKg (entity): {}. Detalhes: {}", rs.getId(), entity.getCargaHora(), entity.getGusaKg(), entity.getAcumuladoKilos(), npe.getMessage(), npe);
            rs.setConsumoKg(BigDecimal.ZERO);
        } catch (ArithmeticException ae) {
            logger.error("ID: {} - ArithmeticException ao calcular ConsumoKg. Gusa (entity): {}, Carga (entity): {}. Detalhes: {}", rs.getId(), entity.getGusaKg(), entity.getCargaHora(), ae.getMessage(), ae);
            rs.setConsumoKg(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular ConsumoKg. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setConsumoKg(BigDecimal.ZERO);
        }

        try {
            // Calculos de M3/T
            // ⚠️ Atenção: `entity.getConsumoKg()` está sendo usado. Se o cálculo anterior de ConsumoKg
            // foi para `rs.setConsumoKg()`, então deveria ser `rs.getConsumoKg()`.
            BigDecimal consumoKgCalculado = rs.getConsumoKg(); // Usar o valor de 'rs' que foi calculado
            Integer densidadeInput = entity.getDensidadeKg();

            if (consumoKgCalculado == null) {
                logger.warn("ID: {} - ConsumoKg (rs) é nulo. Definindo ConsumoMetros como ZERO.", rs.getId());
                rs.setConsumoMetros(BigDecimal.ZERO);
            } else if (densidadeInput == null) {
                logger.warn("ID: {} - DensidadeKg (entity) é nula. Definindo ConsumoMetros como ZERO.", rs.getId());
                rs.setConsumoMetros(BigDecimal.ZERO);
            } else if (densidadeInput.intValue() == 0) {
                logger.warn("ID: {} - DensidadeKg (entity) é zero. Divisão por zero evitada. Definindo ConsumoMetros como ZERO.", rs.getId());
                rs.setConsumoMetros(BigDecimal.ZERO);
            } else {
                BigDecimal consumoMetros = consumoKgCalculado.divide(BigDecimal.valueOf(densidadeInput), 4, RoundingMode.HALF_UP);
                rs.setConsumoMetros(consumoMetros);
                logger.info("ID: {} - ConsumoMetros (M3/T): {}", rs.getId(), rs.getConsumoMetros());
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular ConsumoMetros. ConsumoKg (rs): {}, Densidade (entity): {}. Detalhes: {}", rs.getId(), rs.getConsumoKg(), entity.getDensidadeKg(), npe.getMessage(), npe);
            rs.setConsumoMetros(BigDecimal.ZERO);
        } catch (ArithmeticException ae) {
            logger.error("ID: {} - ArithmeticException (divisão por zero) ao calcular ConsumoMetros. Densidade (entity) era {}. Detalhes: {}", rs.getId(), entity.getDensidadeKg(), ae.getMessage(), ae);
            rs.setConsumoMetros(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular ConsumoMetros. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setConsumoMetros(BigDecimal.ZERO);
        }

        try {
            // Calculo de +/-
            // ⚠️ Usar valores de 'rs' que já foram calculados
            BigDecimal enfornadoCarvaoRs = rs.getCarvaoEnfornado();
            BigDecimal calculadoCarvaoRs = rs.getPesoCarvaoCalc();

            if (enfornadoCarvaoRs == null) {
                logger.warn("ID: {} - CarvaoEnfornado (rs) é nulo. Definindo PositivoNegativo como ZERO.", rs.getId());
                rs.setPositivoNegativo(BigDecimal.ZERO);
            } else if (calculadoCarvaoRs == null) {
                logger.warn("ID: {} - PesoCarvaoCalc (rs) é nulo. Definindo PositivoNegativo como ZERO.", rs.getId());
                rs.setPositivoNegativo(BigDecimal.ZERO);
            } else {
                BigDecimal diferencaPositoOuNegativo = enfornadoCarvaoRs.subtract(calculadoCarvaoRs);
                rs.setPositivoNegativo(diferencaPositoOuNegativo);
                logger.info("ID: {} - Valor de enf (rs): {}, Valor de calc (rs): {}, Valor de +/-: {}", rs.getId(), enfornadoCarvaoRs, calculadoCarvaoRs, diferencaPositoOuNegativo);
            }
        } catch (NullPointerException npe) {
            logger.error("ID: {} - NPE ao calcular PositivoNegativo. CarvaoEnfornado (rs): {}, PesoCarvaoCalc (rs): {}. Detalhes: {}", rs.getId(), rs.getCarvaoEnfornado(), rs.getPesoCarvaoCalc(), npe.getMessage(), npe);
            rs.setPositivoNegativo(BigDecimal.ZERO);
        } catch (Exception e) {
            logger.error("ID: {} - Erro genérico ao calcular PositivoNegativo. Detalhes: {}", rs.getId(), e.getMessage(), e);
            rs.setPositivoNegativo(BigDecimal.ZERO);
        }

        try {
            // Salvar uma última vez com todos os valores calculados.
            ControleOperacionalEntity savedEntity = controleOperacionalRepository.save(rs);
            logger.info("ID: {} - Entidade final salva com todos os cálculos.", savedEntity.getId());
            return Optional.of(controleOperacionalMapper.toDto(savedEntity));
        } catch (Exception e) {
            logger.error("ID: {} - Erro crítico ao salvar entidade final. Retornando Optional.empty(). Detalhes: {}", rs.getId(), e.getMessage(), e);
            return Optional.empty();
        }
    }


    @Override
    public List<ControleOperacionalDto> getAllDataByDate(LocalDate data) {
        List<ControleOperacionalEntity> rs = controleOperacionalRepository.findAllByCreatedAt(data);
        if (rs.isEmpty()){
            throw new NoOperacionalFound("Não há informações a serem retornadas");
        }
        return rs.stream().map(ControleOperacionalDto::new).toList();
    }

}
