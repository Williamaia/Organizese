package br.com.bugoffice.organizese.models;

public class Financas {
    private float valor;
    private String desc;
    private String tipo;
    private String status;

    public Financas() { }

    public  Financas(float valor, String desc, String tipo, String status){
        this.valor = valor;
        this.desc = desc;
        this.tipo = tipo;
        this.status = status;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


