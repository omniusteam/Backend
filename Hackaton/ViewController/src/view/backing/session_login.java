package view.backing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.adf.view.rich.component.rich.RichPopup;


import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.server.ViewObjectImpl;

import view.utils.ADFUtils;
import view.utils.ListsSingleton;

public class session_login {
    private RichPopup p_newParada;
    private RichPopup p_newRuta;
    private RichPopup p_cat;
    private List<SelectItem> lis_categorias= ListsSingleton.getInstance().getListCategorias();
    private Integer idRutaSelect;
    private List<Integer> selectedPermisos;
    private RichPopup p_rutaParada;

    public void setIdRutaSelect(Integer idRutaSelect) {
        this.idRutaSelect = idRutaSelect;
    }

    public Integer getIdRutaSelect() {
        return idRutaSelect;
    }
    //---------- Rol - Perfil -----------------//
    private List<SelectItem> availablePermisos;

    public void setDel_idrol(int del_idrol) {
        this.del_idrol = del_idrol;
    }

    public int getDel_idrol() {
        return del_idrol;
    }

    public void setSelectedPermisos(List<Integer> selectedPermisos) {
        this.selectedPermisos = selectedPermisos;
    }

    public List<Integer> getSelectedPermisos() {
        return selectedPermisos;
    }
    private int del_idrol;
    

    public void setLis_categorias(List<SelectItem> lis_categorias) {
        this.lis_categorias = lis_categorias;
    }

    public List<SelectItem> getLis_categorias() {
        return lis_categorias;
    }

    public session_login() {
        super();
    }
    
    public void setAvailablePermisos(List<SelectItem> avPer) {
        this.availablePermisos = avPer;
    }

  
    public String init_newRuta() {
        ADFUtils.executeBindingOperation("CreateInserRutat");
        ADFUtils.showPopup("p_newRuta");
        return null;
    }

    public String initNewParada() {
        ADFUtils.executeBindingOperation("CreateInsert");
        ADFUtils.showPopup("p_newParada");
        return null;
    }

    public void setP_newParada(RichPopup p_newParada) {
        this.p_newParada = p_newParada;
    }

    public RichPopup getP_newParada() {
        return p_newParada;
    }

    public String saveParada() {
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Parada guardada");
            refreshDataSet();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_newParada.hide();
        return null;
    }
    
    public void refreshDataSet() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("Execute");
        Object result = operationBinding.execute();
    }
    
    public String saveRuta() {
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Ruta guardada");
            refreshDataSetRuta();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_newParada.hide();
        return null;
    }
    
    public void refreshDataSetRuta() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteRuta");
        Object result = operationBinding.execute();
    }

    public String cancelParada() {
        
        this.p_newParada.hide();
        return null;
    }

    public String cancelRuta() {
        ADFUtils.executeBindingOperation("Rollback");
        this.p_newRuta.hide();
        return null;
    }

    public void setP_newRuta(RichPopup p_newRuta) {
        this.p_newRuta = p_newRuta;
    }

    public RichPopup getP_newRuta() {
        return p_newRuta;
    }

    public void setP_cat(RichPopup p_cat) {
        this.p_cat = p_cat;
    }

    public RichPopup getP_cat() {
        return p_cat;
    }


    public String initNewCat() {
        ADFUtils.executeBindingOperation("CreateInsertCat");
        ADFUtils.showPopup("p_cat");
        
        return null;
    }

    public String saveCatalogo() {
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Catálogo guardada");
            refreshDataSetCat();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_cat.hide();
        return null;
    }

    public String cancelCat() {
        ADFUtils.executeBindingOperation("Rollback");
        this.p_cat.hide();
        return null;
    }
    
    public void refreshDataSetCat() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteCat");
        Object result = operationBinding.execute();
    }

    public void setP_rutaParada(RichPopup p_rutaParada) {
        this.p_rutaParada = p_rutaParada;
    }

    public RichPopup getP_rutaParada() {
        return p_rutaParada;
    }

    public String initNewRutaParda() {
        ADFUtils.executeBindingOperation("CreateInsertRutaParada");
        ADFUtils.showPopup("p_rutaParada");
        return null;
    }
    
    public String saveRutaParada() {
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Parada agregada a la ruta");
            refreshDataSetRutaParada();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_rutaParada.hide();
        return null;
    }

    public String cancelRutaParada() {
        ADFUtils.executeBindingOperation("Rollback");
        this.p_rutaParada.hide();
        return null;
    }
    
    public void refreshDataSetRutaParada() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteRutaParada");
        Object result = operationBinding.execute();
    }
}
