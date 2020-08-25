package com.org.bbb.controller;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.org.bbb.dto.AccionModulo;
import com.org.bbb.dto.Perfil;
import com.org.bbb.dto.Permiso;
import com.org.bbb.dto.PermisoPerfil;
import com.org.bbb.dto.Usuario;
import com.org.bbb.utils.Config;
import com.org.bbb.utils.FormatoNumero;

@Controller("controladorG")
public class ControladorG {

	@Autowired
	@Qualifier("formatNumber")
	private FormatoNumero formato;

	@Autowired
	@Qualifier("configCore")
	private Config config;

	private Object cVentas;
	private Object cLogin;
	private Object cImpresion;
	private Object cOperaciones;
	private Object cTiempoAire;
	private Object cCardif;

	private JDesktopPane escritorio;
	private JInternalFrame vistaAnterior;
	private HashMap<String, Object> modulos;
	private HashMap<String, Object> params;
	private Object moduloAnterior;
	private Usuario usuario;
	private Perfil perfil;
	private ArrayList<AccionModulo> accionModulos;
	private ArrayList<PermisoPerfil> permisoPerfiles;
	private ArrayList<Permiso> permisos;
	private int idUser;
	private boolean permitido;
	private JInternalFrame vista;
	private JInternalFrame vistaOcultaTemporal;

	public void ejecuta(Object instancia, String accion, String[] parametros) {

		Method metodo = null;
		try {
			metodo = instancia.getClass().getDeclaredMethod("ejecutaAccion",
					new Class[] { String.class, String[].class });

			// Method metodo = instancia.getClass().getDeclaredMethod("sayHello", new
			// Class[]{String.class});
			metodo.setAccessible(true);
			String salida = (String) metodo.invoke(instancia, new Object[] { "argumento1" });
			System.out.println(salida);
			metodo.setAccessible(true);

			metodo.invoke(instancia, new Object[] { accion, parametros });
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (SecurityException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}

	}

	public void ejecuta(Object instancia, String accion, HashMap<String, Object> parametros) {

		Method metodo = null;
		try {
			metodo = instancia.getClass().getDeclaredMethod("ejecutaAccion",
					new Class[] { String.class, String[].class });

			// Method metodo = instancia.getClass().getDeclaredMethod("sayHello", new
			// Class[]{String.class});
			// metodo.setAccessible(true);
			// String salida = (String)metodo.invoke(instancia, new
			// Object[]{"argumento1"}));

			metodo.setAccessible(true);

			metodo.invoke(instancia, new Object[] { accion, parametros });
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void mensajePermisoDenegado() {

		JOptionPane.showMessageDialog(null, "Permiso Denegado");

	}

	public void verificaPermiso(String accion) {

		permitido = false;
		for (Permiso permiso : getPermisos()) {
			AccionModulo tmp = permiso.getAm();
			System.out.println("Permisos cargados: " + tmp.getAccion() + " Accion: " + accion);
			if (tmp.getAccion().equals(accion)) {
				if (tmp.getVerificar() == 1 && this.getIdUser() > 0) {
					permitido = true;
					break;
				}
				if (tmp.getVerificar() == 0) {
					permitido = true;
					break;
				}
			}
		}
		if (permitido == false) {
			mensajePermisoDenegado();
		}
	}

	public void ejecutaAccion(String modulo, String accion, String[] parametros, Object moduloAnterior) {
		Object tmp = this.modulos.get(modulo);
		setModuloAnterior(moduloAnterior);
		ejecuta(tmp, accion, parametros);
	}

	public void ejecutaAccion(String modulo, String accion, HashMap<String, Object> parametros, Object moduloAnterior) {
		Object tmp = this.modulos.get(modulo);
		setModuloAnterior(moduloAnterior);
		ejecuta(tmp, accion, parametros);
	}

	private JInternalFrame centraVista(JInternalFrame vista_tmp) {
		Dimension desktopSize = escritorio.getSize();
		Dimension jInternalFrameSize = vista_tmp.getSize();
		vista_tmp.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
		System.out.println("Tamano Escritorio: Alto: " + desktopSize.height + " Ancho: " + desktopSize.width);
		return vista_tmp;
	}

	public int tipoVista() {
		int regreso = 0;
		// regreso=1 Tipo 1 < 800x 600
		// regreso=2 Tipo 2 > 800 x 600 <1024x768
		// regreso=3 Tipo 3 > 1024 x 768

		Dimension desktopSize = escritorio.getSize();

		if (desktopSize.height <= 600 && desktopSize.width <= 800) {
			regreso = 1;// Full 800
		}
		if (desktopSize.height >= 600 && desktopSize.width >= 800 && desktopSize.height <= 768
				&& desktopSize.width <= 1024) {
			regreso = 2;// Centrado 800
		}
		if (desktopSize.height <= 768 && desktopSize.width >= 1024) {
			regreso = 3; // Full 1024
		}
		if (desktopSize.height >= 768 && desktopSize.width >= 1024) {
			regreso = 4; // Centrado 1024
		}
		return regreso;

	}

	public void agregaVista(JInternalFrame vista_tmp, boolean centrado, boolean maximun, boolean cAnterior,
			boolean cEliminar) {

		this.vista = vista_tmp;

		if (centrado) {
			vista_tmp = this.centraVista(vista_tmp);
		}

		escritorio.add(vista_tmp);
		vista_tmp.setVisible(true);

		if (maximun) {
			try {
				vista_tmp.setMaximum(true);
			} catch (PropertyVetoException ex) {
				Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		if (vistaAnterior != null && cAnterior == true) {
			vistaAnterior.setVisible(false);
		}
		if (vistaAnterior != null && cEliminar == true) {
			vistaAnterior.dispose();
		}

		vistaAnterior = vista_tmp;

	}

	public void agregaVista(JInternalFrame vista_tmp, boolean centrado, boolean maximun) {

		this.vista = vista_tmp;

		if (centrado) {
			vista_tmp = this.centraVista(vista_tmp);
		}

		escritorio.add(vista_tmp);
		vista_tmp.setVisible(true);

		if (maximun) {
			try {
				vista_tmp.setMaximum(true);
			} catch (PropertyVetoException ex) {
				Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		vistaAnterior = vista_tmp;

	}
	
    public void agregaVistaTemporal(JInternalFrame vista_tmp, boolean centrado, boolean maximun) {

        // this.vista=vista_tmp;
        if (centrado) {
            vista_tmp = this.centraVista(vista_tmp);
        }

        escritorio.add(vista_tmp);
        vista_tmp.setVisible(true);

        if (maximun) {
            try {
                vista_tmp.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ControladorG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // vistaAnterior=vista_tmp;
    }

	public void ejecutaAccion(String accion) {

	}

	public void ejecutaAccion(String accion, String[] parametros) {

	}

	public void ejecutaAccion(String accion, HashMap<String, Object> parametros) {

	}

	public void ejecutaAccion(String accion, HashMap<String, Object> parametros, HashMap<String, Object> regreso) {

	}

	public void cierraVista() {
		this.vista.setVisible(false);
		this.vista.dispose();
	}

	public void ocultaVistaActual() {
		this.vistaOcultaTemporal = this.vista;
		this.vista.setVisible(false);
	}

	public void regresaVistaOculta() {
		this.vista = this.vistaOcultaTemporal;
		this.muestraVista();
	}

	public void muestraVista() {
		this.vista.setVisible(true);
	}
	
	

	public ArrayList<PermisoPerfil> getPermisoPerfiles() {
		return permisoPerfiles;
	}

	public void setPermisoPerfiles(ArrayList<PermisoPerfil> permisoPerfiles) {
		this.permisoPerfiles = permisoPerfiles;
	}

	public ArrayList<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(ArrayList<Permiso> permisos) {
		this.permisos = permisos;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Object getModuloAnterior() {
		return moduloAnterior;
	}

	public void setModuloAnterior(Object moduloAnterior) {
		this.moduloAnterior = moduloAnterior;
	}

	public Object getcVentas() {
		return cVentas;
	}

	public void setcVentas(Object cVentas) {
		this.cVentas = cVentas;
	}

	public Object getcLogin() {
		return cLogin;
	}

	public void setcLogin(Object cLogin) {
		this.cLogin = cLogin;
	}

	public Object getcImpresion() {
		return cImpresion;
	}

	public void setcImpresion(Object cImpresion) {
		this.cImpresion = cImpresion;
	}

	public Object getcOperaciones() {
		return cOperaciones;
	}

	public void setcOperaciones(Object cOperaciones) {
		this.cOperaciones = cOperaciones;
	}

	public Object getcTiempoAire() {
		return cTiempoAire;
	}

	public void setcTiempoAire(Object cTiempoAire) {
		this.cTiempoAire = cTiempoAire;
	}

	public Object getcCardif() {
		return cCardif;
	}

	public void setcCardif(Object cCardif) {
		this.cCardif = cCardif;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public ArrayList<AccionModulo> getAccionModulos() {
		return accionModulos;
	}

	public void setAccionModulos(ArrayList<AccionModulo> accionModulos) {
		this.accionModulos = accionModulos;
	}

}