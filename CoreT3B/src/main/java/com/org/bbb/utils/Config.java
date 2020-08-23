package com.org.bbb.utils;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("configCore")
@PropertySource(value = "file:ConexionBBB.properties")
public class Config {

	@Value("${dbUser}")
	private String dbUser;

	@Value("${dbPass}")
	private String dbPass;

	@Value("${dbHost}")
	private String dbHost;

	@Value("${dbPort}")
	private String dbPort;

	@Value("${dbName}")
	private String dbName;

	@Value("${dbClassDriver}")
	private String dbClassDriver;

	@Value("${dbUrl}")
	private String dbUrl;

	@Value("${appName}")
	private String appName;

	@Value("${appDvlp}")
	private String appDvlp;

	@Value("${appOwne}")
	private String appOwne;

	@Value("${jars}")
	private String jars;

	@Value("${njars}")
	private String njars;

	@Value("${moduloInicio}")
	private String moduloInicio;

	@Value("${host}")
	private String metodoInicio;

	@Value("${metodoInicio}")
	private String paramInicio;

	@Value("${sistema}")
	private String sistema;

	@Value("${id_sistema}")
	private String id_sistema;

	@Value("${idUser}")
	private int idUser;
	
	public Object devuelveConfiguracion(Object dtoConfig) {
		try {
			if (dtoConfig != null) {
				dtoConfig.getClass().getField("dbUser").set(dtoConfig, dbUser);
				dtoConfig.getClass().getField("dbPass").set(dtoConfig, dbPass);
				dtoConfig.getClass().getField("dbHost").set(dtoConfig, dbHost);
				dtoConfig.getClass().getField("dbPort").set(dtoConfig, dbPort);
				dtoConfig.getClass().getField("dbName").set(dtoConfig, dbName);
				dtoConfig.getClass().getField("dbClassDriver").set(dtoConfig, dbClassDriver);
				dtoConfig.getClass().getField("appName").set(dtoConfig, appName);
				dtoConfig.getClass().getField("appDvlp").set(dtoConfig, appDvlp);
				dtoConfig.getClass().getField("appOwne").set(dtoConfig, appOwne);
				dtoConfig.getClass().getField("dbUrl").set(dtoConfig, dbUrl);
			}
		} catch (Exception ex) {
			Logger3B.Log(1, "T3BCore", "t3b.core.utils.Config", "devuelveConfiguracion", ex.getMessage());
			dtoConfig = null;
		}
		return dtoConfig;
	}
	
	
	public HashMap<String, String> devuelveConfiguracion() {
		HashMap<String, String> dtoConfig = new HashMap<>();
		try {
			dtoConfig.put("dbUser", dbUser);
			dtoConfig.put("dbPass", dbPass);
			dtoConfig.put("dbHost", dbHost);
			dtoConfig.put("dbPort", dbPort);
			dtoConfig.put("dbName", dbName);
			dtoConfig.put("dbClassDriver", dbClassDriver);
			dtoConfig.put("appName", appName);
			dtoConfig.put("appDvlp", appDvlp);
			dtoConfig.put("appOwne", appOwne);
			dtoConfig.put("dbUrl", dbUrl);
		} catch (Exception ex) {
			Logger3B.Log(1, "T3BCore", "t3b.core.utils.Config", "devuelveConfiguracion", ex.getMessage());
			dtoConfig = null;
		}
		return dtoConfig;
	}
	

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbClassDriver() {
		return dbClassDriver;
	}

	public void setDbClassDriver(String dbClassDriver) {
		this.dbClassDriver = dbClassDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDvlp() {
		return appDvlp;
	}

	public void setAppDvlp(String appDvlp) {
		this.appDvlp = appDvlp;
	}

	public String getAppOwne() {
		return appOwne;
	}

	public void setAppOwne(String appOwne) {
		this.appOwne = appOwne;
	}

	public String getJars() {
		return jars;
	}

	public void setJars(String jars) {
		this.jars = jars;
	}

	public String getNjars() {
		return njars;
	}

	public void setNjars(String njars) {
		this.njars = njars;
	}

	public String getModuloInicio() {
		return moduloInicio;
	}

	public void setModuloInicio(String moduloInicio) {
		this.moduloInicio = moduloInicio;
	}

	public String getMetodoInicio() {
		return metodoInicio;
	}

	public void setMetodoInicio(String metodoInicio) {
		this.metodoInicio = metodoInicio;
	}

	public String getParamInicio() {
		return paramInicio;
	}

	public void setParamInicio(String paramInicio) {
		this.paramInicio = paramInicio;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getId_sistema() {
		return id_sistema;
	}

	public void setId_sistema(String id_sistema) {
		this.id_sistema = id_sistema;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
