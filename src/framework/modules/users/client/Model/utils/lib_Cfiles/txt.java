/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.modules.users.client.Model.utils.lib_Cfiles;

import framework.modules.users.client.Model.classes.client_class;
import framework.modules.users.client.Model.classes.singleton_client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author pelu
 */
public class txt {
   
   public static void savetxt_client(){
		String PATH = null;
		
		try {
			File f;
			JFileChooser filechooser = new JFileChooser();
			
			filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT(*.txt)", "txt"));
			
			int selection = filechooser.showSaveDialog(null);
			if (selection == JFileChooser.APPROVE_OPTION) {
				File JFC = filechooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				PATH = PATH +".txt";
				f = new File(PATH);
				
				FileOutputStream fo = new FileOutputStream(f);
				ObjectOutputStream o = new ObjectOutputStream(fo);
				o.writeObject(singleton_client.client);
				o.close();
				JOptionPane.showMessageDialog(null, "Archivo TXT guardado con exito",
						"Archivo TXT", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al grabar el TXT",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	} 
    
   public static void opentxt_client() {
		String PATH = null;
        try {
            File f;
            JFileChooser filechooser = new JFileChooser();
            
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT(*.txt)", "txt"));
            
            int selection = filechooser.showOpenDialog(null);
            if (selection == JFileChooser.APPROVE_OPTION) {
                File JFC = filechooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                f = new File(PATH);
                
                FileInputStream fi=new FileInputStream(f);
    			ObjectInputStream oi=new ObjectInputStream(fi);
    			singleton_client.client = (ArrayList<client_class>)oi.readObject();
    			oi.close();
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error al leer el TXT", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
