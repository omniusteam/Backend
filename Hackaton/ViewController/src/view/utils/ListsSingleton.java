package view.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.server.ViewObjectImpl;

public class ListsSingleton {
    
    private static ListsSingleton instance = null;
    private List<SelectItem> listCategorias;

    public void setListCategorias(List<SelectItem> listCategorias) {
        this.listCategorias = listCategorias;
    }

    public List<SelectItem> getListCategorias() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        String baseQuery = String.format("SELECT \n" + 
        "C.IDCATALOGO,\n" + 
        "C.NOMBRE\n" + 
        "FROM CATALOGO C\n" + 
        "WHERE C.IDCATEGORIA IS NULL ");
        ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObjectImpl vo = null;
        if (am.findViewObject("DynamicViewObject1") != null) {
            am.findViewObject("DynamicViewObject1").remove();
        } 
        vo = (ViewObjectImpl) am.createViewObjectFromQueryStmt("DynamicViewObject1",baseQuery);
        //System.out.println(":::NEW:::"+baseQuery);
        vo.executeQuery();
        vo.setRangeSize(-1);
        RowIterator empRowIterator = vo;
        while (empRowIterator.hasNext()) {
            Row empRow = empRowIterator.next();
            list.add(new SelectItem(Integer.parseInt(empRow.getAttribute("IDCATALOGO").toString()), (String)empRow.getAttribute("NOMBRE")));
        }      
        return list;
        //return listCategorias;
    }

    public ListsSingleton() {
        super();
    }
    
    public static void setInstance(ListsSingleton instance) {
        ListsSingleton.instance = instance;
    }
    
    public static ListsSingleton getInstance() {
        if (instance == null) {
            instance = new ListsSingleton();
        }
        return instance;
    }
    
    private List<SelectItem> getListFromCatalogo(int idcatalogo) {
        List<SelectItem> list = new ArrayList<SelectItem>();
        String baseQuery = String.format("SELECT \n" + 
        "C.IDCATALOGO,\n" + 
        "C.NOMBRE\n" + 
        "FROM CATALOGO C\n" + 
        "WHERE C.IDCATEGORIA =%s",idcatalogo);
        ApplicationModule am = ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
        ViewObjectImpl vo = null;
        if (am.findViewObject("DynamicViewObject1") != null) {
            am.findViewObject("DynamicViewObject1").remove();
        } 
        vo = (ViewObjectImpl) am.createViewObjectFromQueryStmt("DynamicViewObject1",baseQuery);
        //System.out.println(":::NEW:::"+baseQuery);
        vo.executeQuery();
        vo.setRangeSize(-1);
        RowIterator empRowIterator = vo;
        while (empRowIterator.hasNext()) {
            Row empRow = empRowIterator.next();
            list.add(new SelectItem(Integer.parseInt(empRow.getAttribute("IDCATALOGO").toString()), (String)empRow.getAttribute("NOMBRE")));
        }      
        return list;
    }
 
}
