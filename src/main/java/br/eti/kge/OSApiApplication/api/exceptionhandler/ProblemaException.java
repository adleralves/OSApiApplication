/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author adler
 */
public class ProblemaException {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    
    private List<CampoProblema> listaCamposProblema;

    public List<CampoProblema> getCampos() {
        return listaCamposProblema;
    }

    public void setCampos(List<CampoProblema> campos) {
        this.listaCamposProblema = campos;
    }
    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public static class CampoProblema {
        private String nomeCampo;
        private String mensagemCampo;

        public CampoProblema(String nomeCampo, String mensagemCampo) {
            this.nomeCampo = nomeCampo;
            this.mensagemCampo = mensagemCampo;
        }
        

        public String getNomeCampo() {
            return nomeCampo;
        }

        public void setNomeCampo(String nomeCampo) {
            this.nomeCampo = nomeCampo;
        }

        public String getMensagemCampo() {
            return mensagemCampo;
        }

        public void setMensagemCampo(String mensagemCampo) {
            this.mensagemCampo = mensagemCampo;
        }
        
        
    }
}
