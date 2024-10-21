package com.cooperfilme.api.entity.enums;

import java.util.List;
import java.util.Arrays;

public enum TipoCargo {

    ANALISTA(0, "Analista", Arrays.asList(TipoStatusRoteiro.AGUARDANDO_ANALISE, TipoStatusRoteiro.EM_ANALISE)),
    REVISOR(1, "Revisor", Arrays.asList(TipoStatusRoteiro.AGUARDANDO_ANALISE, TipoStatusRoteiro.AGUARDANDO_REVISAO, TipoStatusRoteiro.EM_REVISAO)),
    APROVADOR(2, "Aprovador", Arrays.asList(TipoStatusRoteiro.AGUARDANDO_ANALISE, TipoStatusRoteiro.AGUARDANDO_APROVACAO, TipoStatusRoteiro.EM_APROVAÇÃO));

    private Integer cod;
    private String descricao;
    private List<TipoStatusRoteiro> statusRoteiros;

    private TipoCargo(Integer cod, String descricao, List<TipoStatusRoteiro> statusRoteiros){
        this.cod = cod;
        this.descricao = descricao;
        this.statusRoteiros = statusRoteiros;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TipoStatusRoteiro> getStatusRoteiros() {
        return statusRoteiros;
    }

    public void setStatusRoteiros(List<TipoStatusRoteiro> statusRoteiros) {
        this.statusRoteiros = statusRoteiros;
    }

    public boolean isAprovador(){
        return this == APROVADOR;
    }

    public static TipoCargo toEnum(String descricao) {
        
        for(TipoCargo tc: TipoCargo.values()){
            if (tc.getDescricao().equals(descricao)) {
                return tc;
            }
        }

        throw new IllegalArgumentException("Tipo de Cargo Inválido");
    }

}
