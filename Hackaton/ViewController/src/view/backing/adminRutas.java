package view.backing;


import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import view.utils.ADFUtils;

public class adminRutas {
    private RichPopup p_newRuta;
    

    public adminRutas() {
        super();
    }

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
            refreshDataSet();
        } else {
            ADFUtils.showWarnMessage("Error al guardar");
        }
        this.p_newRuta.hide();
        return null;
    }
    
    public void refreshDataSet() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteRuta");
        Object result = operationBinding.execute();
    }

    public String cancelarNewRuta() {
        p_newRuta.hide();
        return null;
    }


}
