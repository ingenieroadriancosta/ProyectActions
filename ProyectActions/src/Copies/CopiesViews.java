package Copies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADRIAN COSTA
 */
public class CopiesViews {
    public static boolean copies( String FViewsIn, String FViewsOut ){
        if( FViewsIn==null || FViewsOut==null ){
            return false;
        }
        if( !(new File(FViewsIn)).exists() || !(new File(FViewsOut).exists()) ){
            return false;
        }
        // deleteAllInFolder(new File(FViewsOut) );
        copyAllInFolder( new File(FViewsIn), new File(FViewsOut) );
        return true;
    }
    
    
    
    
    
    static void copyAllInFolder( File flin, File flout ){
        if( flin.isFile() ){
            File flo = new File( flout.getAbsoluteFile() + "\\" + flin.getName() );
            try {
                Files.copy(flin.toPath(), flo.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
            }
        }else{
            File flList[] = flin.listFiles();
            File flsc = new File( flout.getAbsoluteFile() + "\\" + flin.getName() );
            flsc.mkdir();
            for (File flList1 : flList) {
                copyAllInFolder( flList1, flsc );
            }
        }
    }
    
    
    static void deleteAllInFolder( File fl ){
        if( fl.isFile() ){
            fl.delete();
        }else{
            File flList[] = fl.listFiles();
            for (File flList1 : flList) {
                deleteAllInFolder( flList1 );
                if( flList1.isDirectory() ){
                    flList1.delete();
                }
            }
        }
    }
    
}
