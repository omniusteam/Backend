package view.backing;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import view.utils.ADFUtils;

public class adminParadas {
    private RichPopup p_newParada;

    public adminParadas() {
        super();
    }

    public void setP_newParada(RichPopup p_newParada) {
        this.p_newParada = p_newParada;
    }

    public RichPopup getP_newParada() {
        return p_newParada;
    }
    
    public String initNewParada (){
        ADFUtils.executeBindingOperation("CreateInsert");
        ADFUtils.showPopup("p_newParada");
        
        return null;
    }

    public String saveParada() {
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Ruta guardada");
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

    public String cancelParada() {
        this.p_newParada.hide();
        return null;
    }
    private RichPopup p_newRuta;
    


    public void setP_newRuta(RichPopup p_newRuta) {
        this.p_newRuta = p_newRuta;
    }

    public RichPopup getP_newRuta() {
        return p_newRuta;
    }

    public String initNewRuta() {
        ADFUtils.executeBindingOperation("CreateInsertRuta");
        ADFUtils.showPopup("p_newRuta"); 
        return null;
    }
    
    public String saveRuta(){
        if (ADFUtils.commitDB()) {
            ADFUtils.showConfirmationMessage("Ruta guardada");
            refreshDataSetRuta();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_newRuta.hide();
        return null;
    }
    
    public void refreshDataSetRuta() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteRuta");
        Object result = operationBinding.execute();
    }

    public String cancelarNewRuta() {
        p_newRuta.hide();
        return null;
    }
}
