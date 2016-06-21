package view.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.util.FacesMessageUtils;



import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class ADFUtils {
    public ADFUtils() {
        super();
    }
    
    //------------------ OPERACIONES DE BASE DE DATOS -------------------------------------
    public static boolean commitDB() {
        try{
            OperationBinding operationBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Commit");
            operationBinding.execute();
            if (operationBinding.getErrors().isEmpty()) {
                operationBinding = null;    
                return true;
            }
            operationBinding = null;
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return false;
    }
    
    public static boolean executeBindingOperation(String oper){
        try{
            OperationBinding operationBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding(oper);
            operationBinding.execute();
            if(operationBinding.getErrors().isEmpty()) {
                operationBinding = null;
                return true;
            }
            operationBinding = null;
        }catch(Exception ex){}
        return false;
    }
    
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        
        return (ApplicationModule)JSFUtils.resolveExpression("#{data." + name + ".dataProvider}");
    }
     public static void showConfirmationMessage(String msg){
            FacesContext.getCurrentInstance().addMessage(null, FacesMessageUtils.getConfirmationMessage(null,msg));
        }
        
    public static void showInfoMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
    }
        
    
    public static void showPopup(String popupId){
        Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class).addScript(FacesContext.getCurrentInstance(),"AdfPage.PAGE.findComponent('"+popupId+"').show();");
    }
    
    public static void showWarnMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, msg));
    }
    
    public static void showErrorMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
    }
    
}
