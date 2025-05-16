/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaFiscalServico {
    private int id;
    private int numeroNota;
    private LocalDate dataEmissao;
    private BigDecimal valorImpostos;
    private String cliente;
    private String descricao;

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(int numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorImpostos() {
        return valorImpostos;
    }

    public void setValorImpostos(BigDecimal valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
