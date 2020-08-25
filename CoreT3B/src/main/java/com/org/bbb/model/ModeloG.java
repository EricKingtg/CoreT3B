package com.org.bbb.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.org.bbb.consulta.Consulta;
import com.org.bbb.controller.ControladorG;
import com.org.bbb.dto.CampoDTO;
import com.org.bbb.dto.ColumnaDTO;
import com.org.bbb.dto.QryRespDTO;
import com.org.bbb.utils.Config;

@Service("modeloG")
public class ModeloG {

	@Autowired
	@Qualifier("controladorG")
	private ControladorG controladorG;

	@Autowired
	@Qualifier("configCore")
	private Config configCore;

	@Autowired
	@Qualifier("consulta")
	private Consulta consulta;

	public ModeloG() {

	}

	public ModeloG(ControladorG c, Config cf) {
		controladorG = c;
		configCore = cf;
	}

	public ArrayList<HashMap<String, String>> ejecutaConsulta(String SqlQuery) {
		ArrayList<HashMap<String, String>> resultados = new ArrayList<>();
		QryRespDTO dtotmp = consulta.consulta("mysql", SqlQuery);

		if (dtotmp.getRes() == 1) {
			for (HashMap<String, CampoDTO> tmp : dtotmp.getDatosTabla()) {
				HashMap<String, String> resultado = new HashMap<>();
				for (ColumnaDTO tmp2 : dtotmp.getColumnas()) {
					tmp.get(tmp2.getEtiqueta()).getValor(); // obtiene el valor de la celda
					tmp.get(tmp2.getEtiqueta()).getClase();
					resultado.put(tmp2.getEtiqueta(), tmp.get(tmp2.getEtiqueta()).getValor().toString());
				}
				resultados.add(resultado);
			}
		}
		return resultados;
	}

	public ArrayList<HashMap<String, String>> ejecutaConsultaSP(String sqlQuery, ArrayList<Object> paramIn) {
		ArrayList<HashMap<String, String>> resultados = new ArrayList<>();
		QryRespDTO dtotmp = consulta.ejecutaSelectSP("dirver", sqlQuery, paramIn);
		if (dtotmp.getRes() == 1) {
			for (HashMap<String, CampoDTO> tmp : dtotmp.getDatosTabla()) {
				HashMap<String, String> resultado = new HashMap<String, String>();
				for (ColumnaDTO tmp2 : dtotmp.getColumnas()) {
					// CampoDTO dto1 = tmp1.get(tmp2.getEtiqueta());
					tmp.get(tmp2.getEtiqueta()).getValor(); // obtiene el valor de la celda
					tmp.get(tmp2.getEtiqueta()).getClase();
					resultado.put(tmp2.getEtiqueta(), tmp.get(tmp2.getEtiqueta()).getValor().toString());

				}

				resultados.add(resultado);
			}
		}
		return resultados;
	}
	
	public QryRespDTO ejecutaSP(String sqlQuery, ArrayList<Object> paramIn, ArrayList<Integer> paramOut) {
		QryRespDTO resultado = new QryRespDTO();
		resultado = consulta.ejecutaSP("", sqlQuery, paramIn, paramOut);
		return resultado;
	}

}
