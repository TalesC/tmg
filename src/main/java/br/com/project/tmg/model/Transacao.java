package br.com.project.tmg.model;

import java.util.Objects;

public class Transacao {

    private String descricao;
    private Long data;
    private Integer valor;

    public Transacao(String descricao, Long data, Integer valor) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getData() {
        return data;
    }

    public Integer getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return descricao.equals(transacao.descricao) && data.equals(transacao.data) && valor.equals(transacao.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, data, valor);
    }
}
