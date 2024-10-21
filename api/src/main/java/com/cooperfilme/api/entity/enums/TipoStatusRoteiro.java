package com.cooperfilme.api.entity.enums;

public enum TipoStatusRoteiro {

    AGUARDANDO_ANALISE(1,"Aguardando Análise"),
    EM_ANALISE(2,"Em Análise"),
    AGUARDANDO_REVISAO(3, "Aguardando Revisão"),
    EM_REVISAO(4, "Em Revisão"),
    AGUARDANDO_APROVACAO(5, "Aguardando Aprovação"),
    EM_APROVAÇÃO(6, "Em Aprovação"),
    APROVADO(7, "Aprovado"),
    RECUSADO(8, "Recusado"),
    ERROR(9, "Status de Erro");

    private Integer cod;
    private String descricao;

    private TipoStatusRoteiro(Integer cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
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

    public static TipoStatusRoteiro toEnum(String descricao) {
        
        for(TipoStatusRoteiro tc: TipoStatusRoteiro.values()){
            if (tc.getDescricao().equals(descricao)) {
                return tc;
            }
        }

        throw new IllegalArgumentException("Tipo de Status do Roteiro Inválido");
    }

    public static TipoStatusRoteiro toEnum(Integer cod) {
        
        for(TipoStatusRoteiro tc: TipoStatusRoteiro.values()){
            if (tc.getCod().intValue() == cod.intValue()) {
                return tc;
            }
        }

        throw new IllegalArgumentException("Tipo de Status do Roteiro Inválido");
    }
    
    public boolean isAguardandoAnalise(){
        return this == AGUARDANDO_ANALISE;
    }

    public boolean isEmAnalise(){
        return this == EM_ANALISE;
    }

    public boolean isAguardandoRevisao(){
        return this == AGUARDANDO_REVISAO;
    }

    public boolean isEmRevisao(){
        return this == EM_REVISAO;
    }

    public boolean isAguardandoAprovacao(){
        return this == AGUARDANDO_APROVACAO;
    }

    public boolean isEmAprovacao(){
        return this == EM_APROVAÇÃO;
    }

    public boolean isAprovado(){
        return this == APROVADO;
    }

    public boolean isRecusado(){
        return this == RECUSADO;
    }

}
