package com.idega.block.importer.business;

import com.idega.util.text.TextSoap;
import com.idega.business.IBOLookup;
import java.rmi.RemoteException;
import java.util.Collection;
import com.idega.block.importer.data.ImportFile;
import com.idega.business.IBOServiceBean;
import com.idega.user.data.Group;

/**
 * <p>Title: IdegaWeb classes</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Idega Software</p>
 * @author <a href="mailto:eiki@idega.is"> Eirikur Sveinn Hrafnsson</a>
 * @version 1.0
 */

public class ImportBusinessBean extends IBOServiceBean implements ImportBusiness{

  public ImportBusinessBean() {
  }

  public ImportFileHandler getHandlerForImportFile(Class importFileClass) throws RemoteException{
    return (ImportFileHandler)getServiceInstance(importFileClass);
  }

  public ImportFileHandler getHandlerForImportFile(String importFileClassName)  throws RemoteException, ClassNotFoundException{
    return getHandlerForImportFile(Class.forName( TextSoap.findAndReplace(importFileClassName,".data.",".business.")+"Handler"));
  }

  public boolean importRecords(ImportFile file) throws RemoteException{
    try{
      boolean status = false;
      ImportFileHandler handler = getHandlerForImportFile(file.getClass().getName());
      handler.setImportFile(file);
      /**@todo temporary workaround**/
      //((NackaImportFileHandler)handler).setOnlyImportRelations(true);
      //((NackaImportFileHandler)handler).setStartRecord(52000);
      //((NackaImportFileHandler)handler).setImportRelations(false);
      status = handler.handleRecords();

      /*Collection col = file.getRecords();
      if( col == null ) return false;
      status = handler.handleRecords(col);*/



      return status;
    }
    catch(NoRecordsException ex){
     ex.printStackTrace();
     return false;
    }
    catch(ClassNotFoundException ex){
     ex.printStackTrace();
     return false;
    }
  }
  
   public boolean importRecords(Group group, ImportFile file) throws RemoteException{
    try{
      boolean status = false;
      //ImportFileHandler handler = getHandlerForImportFile(file.getClass().getName());
      
      try{
      	
      is.idega.idegaweb.member.business.KRImportFileHandlerBean handler = new is.idega.idegaweb.member.business.KRImportFileHandlerBean();
      handler.setImportFile(file);
      handler.setRootGroup(group);
      
      /**@todo temporary workaround**/
      //((NackaImportFileHandler)handler).setOnlyImportRelations(true);
      //((NackaImportFileHandler)handler).setStartRecord(52000);
      //((NackaImportFileHandler)handler).setImportRelations(false);
      status = handler.handleRecords();


      }
      catch(Exception e){
      	e.printStackTrace();
      	//status=false;	
      }
      /*Collection col = file.getRecords();
      if( col == null ) return false;
      status = handler.handleRecords(col);*/



      return status;
    }
    catch(NoRecordsException ex){
     ex.printStackTrace();
     return false;
    }
    catch(ClassNotFoundException ex){
     ex.printStackTrace();
     return false;
    }
  }

}