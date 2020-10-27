package Copies;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.*;
import java.nio.file.*;
/**
 *
 * @author ADRIAN COSTA
 */
public class CopiesPlugins {
    String plan = "";
    public String getplan(){
        StringSelection stringSelection = new StringSelection(plan);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        return plan;
    }
    public boolean copies(String pluginsIn, String pluginsOut){
        if( pluginsIn==null || pluginsOut==null ){
            return false;
        }
        if( !(new File(pluginsIn)).exists() || !(new File(pluginsOut).exists()) ){
            return false;
        }
        if( pluginsOut.endsWith(plan) ){
            
        }
        plan = "";
        copiesJARsToFolder( new File(pluginsIn), new File(pluginsOut) );
        if( plan.isEmpty() ){
            return false;
        }
        plan = plan + "\n</plan>\n";
        return true;
    }
    
    
    void copiesJARsToFolder( File flin, File flOut ){
        //JOptionPane.showMessageDialog( null, flin.getAbsolutePath() );
        if( flin.isFile() ){
            if( flin.toString().endsWith(".jar") && flin.toString().contains("libs") ){
                try {
                    String Name = flin.getName();
                    String Path = flin.getPath();
                    
                    System.out.println( Path );
                    
                    String Nameln = Name;
                    Nameln = Nameln.substring(0, Nameln.indexOf('-'));
                    String ProyName = Nameln.substring( 0, Nameln.indexOf('.') );
                    
                    System.out.println( ProyName );
                    
                    File flt = new File( flOut.getPath() + "\\" + ProyName );
                    System.out.println( "ProyName:" + flt.toString() );
                    flt.mkdirs();
                    
                    File flo = new File( flOut.getAbsoluteFile() + "\\" + ProyName + "\\" + flin.getName() );
                    Files.copy(flin.toPath(), flo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    
                    if( !plan.contains("<plan id=") ){
                        plan = "<plan id=\"" + ProyName + "\" reloadable=\"true\">";
                    }
                    
                    
                    plan = plan + "\n" + 
                            "<plugin name=\"" + Nameln + "\" path=\"../plugins/" + ProyName + "/" +
                                    Name + "\"/>";
                    
                } catch (IOException e) {
                }
            }
        }else{
            if( flin.isDirectory() ){
                File flList[] = flin.listFiles();
                for (File flList1 : flList) {
                    copiesJARsToFolder( flList1, flOut );
                }
            }
        }
    }
    
    
    
    
    void deleteJARsInFolder( File fl ){
        if( fl.isFile() ){
            if( fl.toString().endsWith(".jar")){
                fl.delete();
            }
        }else{
            File flList[] = fl.listFiles();
            for (File flList1 : flList) {
                deleteJARsInFolder( flList1 );
                if( flList1.isDirectory() ){
                    flList1.delete();
                }
            }
        }
    }
}
