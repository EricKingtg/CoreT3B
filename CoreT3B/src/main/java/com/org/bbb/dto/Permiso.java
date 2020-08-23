package com.org.bbb.dto;

public class Permiso {

	private int idPermiso;
    private String descPermiso;
    private String aliasPermiso;
    private int estatus;
    private int idSistema;
    private int idAccionModulo;
    private AccionModulo am;
    
	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public String getDescPermiso() {
		return descPermiso;
	}
	public void setDescPermiso(String descPermiso) {
		this.descPermiso = descPermiso;
	}
	public String getAliasPermiso() {
		return aliasPermiso;
	}
	public void setAliasPermiso(String aliasPermiso) {
		this.aliasPermiso = aliasPermiso;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public int getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}
	public int getIdAccionModulo() {
		return idAccionModulo;
	}
	public void setIdAccionModulo(int idAccionModulo) {
		this.idAccionModulo = idAccionModulo;
	}
	public AccionModulo getAm() {
		return am;
	}
	public void setAm(AccionModulo am) {
		this.am = am;
	}
	
}
