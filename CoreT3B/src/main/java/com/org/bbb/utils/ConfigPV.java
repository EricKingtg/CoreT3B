package com.org.bbb.utils;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("configCore")
@PropertySource(value = "file:LinPosBBB.properties")
public class ConfigPV {

	private String tienda;
	private String caja;
	private String cajero;
	private String ptoImpresora;
	private String ptoScanner;
	private String region;
	private String userId = "";
	private String acceso = "";
	private String prefijoGranel;
	private String zona;
	private String apagar;

	@Override
	public String toString() {
		return "ConfigPV [tienda=" + tienda + ", caja=" + caja + ", cajero=" + cajero + ", ptoImpresora=" + ptoImpresora
				+ ", ptoScanner=" + ptoScanner + ", region=" + region + ", userId=" + userId + ", acceso=" + acceso
				+ ", prefijoGranel=" + prefijoGranel + ", zona=" + zona + ", apagar=" + apagar + "]";
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

	public String getPtoImpresora() {
		return ptoImpresora;
	}

	public void setPtoImpresora(String ptoImpresora) {
		this.ptoImpresora = ptoImpresora;
	}

	public String getPtoScanner() {
		return ptoScanner;
	}

	public void setPtoScanner(String ptoScanner) {
		this.ptoScanner = ptoScanner;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public String getPrefijoGranel() {
		return prefijoGranel;
	}

	public void setPrefijoGranel(String prefijoGranel) {
		this.prefijoGranel = prefijoGranel;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getApagar() {
		return apagar;
	}

	public void setApagar(String apagar) {
		this.apagar = apagar;
	}

}
