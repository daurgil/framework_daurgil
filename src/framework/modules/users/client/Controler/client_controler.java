/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.modules.users.client.Controler;

import com.sun.glass.events.KeyEvent;
import framework.modules.Menu_config.Controler.controler_menu;
import framework.modules.Menu_config.View.log_in;
import framework.modules.Menu_config.View.menu;
import framework.modules.users.client.Model.BLL.BLL_client;
import framework.modules.users.client.Model.classes.miniSimpleTableModel_client;
import framework.modules.users.client.Model.classes.singleton_client;
import framework.modules.users.client.Model.utils.autocomplete.AutocompleteJComboBox;
import framework.modules.users.client.Model.utils.autocomplete.StringSearchable;
import framework.modules.users.client.Model.utils.pagina;
import framework.modules.users.client.View.client_create;
import framework.modules.users.client.View.client_table;
import static framework.modules.users.client.View.client_table.TABLA;
import framework.modules.users.client.View.client_update;
import framework.utils.singleton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author pelu
 */
public class client_controler implements ActionListener, MouseListener, KeyListener, FocusListener{
    
    public static JTable tabla = null;
    public static int e = 0;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_client());
    public static client_table table;
    public static client_update Update;
    public static client_create Create;
    public static AutocompleteJComboBox combo_client = null;
    
    /**
     * Funtion to iniciate de frame controler
     * @param frame
     * @param i 
     */
    public client_controler(JFrame frame, int i){
        
        if(i==0){
            table= (client_table) frame;
        }
        if(i==1){
            Create= (client_create) frame;
        }
        if(i==2){
            Update= (client_update) frame;
        }
        
    }

    
    public enum Action{
        
        /////client table/////
        
        //botons//
            t_jl_back,
            t_jl_create,
            t_jl_update,
            t_jl_delete,
            t_jl_json,
            t_jl_xml,
            t_jl_txt,
            t_jb_search,
            t_jb_cancel,
            t_jt_dni,
        //table//
            tableSocio,
            primero,
            ANTERIOR,
            SIGUIENTE,
            ultimo,
            ComboPager,
            Combofiltrar,
            
        /////client create/////
            
            c_jb_save,
            c_jb_clean,
            c_jl_back,
            c_jt_dni,
            c_jt_name,
            c_jt_surname,
            c_jt_mobile,
            c_jt_email,
            c_jt_nick,
            c_jt_password,
            c_chb_password,
            c_jb_avatar,
            c_jdc_birthday,
            c_jdc_update,
            c_jt_shopping,
            c_jt_type,
        
        /////client update/////
            
            u_jb_save,
            u_jl_back,
            u_jt_name,
            u_jt_surname,
            u_jt_mobile,
            u_jt_email,
            u_jt_nick,
            u_jt_password,
            u_chb_password,
            u_jb_avatar,
            u_jdc_birthday,
            u_jdc_update,
            u_jt_shopping,
            u_jt_type,
        
    }
    
    /**
     * options to charge part of the views an control them
     * @param op 
     */
    public void Init(int op){
                
        if(op==0){
                        
            table.setVisible(true);
            
            table.setTitle("Client Menu");

            Image icono=Toolkit.getDefaultToolkit().getImage(singleton_client.icon_client);
            table.setIconImage(icono);
            table.setLocationRelativeTo(null);
            //table.setSize(700,500);//ancho x alto
            table.setResizable(false);
            //this.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada

            table.TABLA.setModel( new miniSimpleTableModel_client() );
            ((miniSimpleTableModel_client)TABLA.getModel()).cargar();
            table.TABLA.setFillsViewportHeight(true);
            table.TABLA.setRowSorter(sorter);

            pagina.inicializa();
            pagina.initLinkBox();

            pagina.itemsPerPage=Integer.parseInt(table.jComboBox1.getSelectedItem().toString());
            pagina.currentPageIndex = 1;
            pagina.initLinkBox();

            table.jLabel3.setText(String.valueOf(singleton_client.client.size()));

            table.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    table.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    //JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                    new controler_menu(new menu(), 0).Init(0);
                    table.dispose();
                    
                }
            });

            List<String> myWords = new ArrayList<String>();
            for(int i=0;i<=singleton_client.client.size()-1;i++) {
                myWords.add(singleton_client.client.get(i).getName());
            }

            StringSearchable searchable = new StringSearchable(myWords);
            combo_client = new AutocompleteJComboBox(searchable);
            //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione
            table.jPanel3.setLayout(new java.awt.BorderLayout());
            table.jPanel3.add(combo_client);

            combo_client.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //comboActionPerformed(evt);
                }
            });

            table.jl_back.setName("t_jl_back");
            table.jl_back.addMouseListener(this);

            table.jl_json.setName("t_jl_json");
            table.jl_json.addMouseListener(this);

            table.jl_xml.setName("t_jl_xml");
            table.jl_xml.addMouseListener(this);

            table.jl_txt.setName("t_jl_txt");
            table.jl_txt.addMouseListener(this);

            table.jl_create.setName("t_jl_create");
            table.jl_create.addMouseListener(this);

            table.jl_modify.setName("t_jl_update");
            table.jl_modify.addMouseListener(this);

            table.jl_delete.setName("t_jl_delete");
            table.jl_delete.addMouseListener(this);

            table.primero.setActionCommand("primero");
            table.primero.addActionListener(this);

            table.ANTERIOR.setActionCommand("ANTERIOR");
            table.ANTERIOR.addActionListener(this);

            table.SIGUIENTE.setActionCommand("SIGUIENTE");
            table.SIGUIENTE.addActionListener(this);

            table.ultimo.setActionCommand("ultimo");
            table.ultimo.addActionListener(this);

            table.jComboBox1.setActionCommand("ComboPager");
            table.jComboBox1.addActionListener(this);

            combo_client.setActionCommand("Combofiltrar");
            combo_client.addActionListener(this);

            table.TABLA.setName("tableSocio");
            table.TABLA.addMouseListener(this);
            
            table.bt_search.setActionCommand("t_jb_search");
            table.bt_search.addActionListener(this);
            
            table.bt_cancel.setActionCommand("t_jb_cancel");
            table.bt_cancel.addActionListener(this);
            
            table.jt_dni.setName("t_jt_dni");
            table.jt_dni.addKeyListener(this);
        
        }
        
        if(op==1){
            
            Create.setVisible(true);
            
            Create.setTitle("Create client");
            Image icono=Toolkit.getDefaultToolkit().getImage(singleton_client.icon_client);
            Create.setIconImage(icono);
            Create.setLocationRelativeTo(null);
            //Create.setSize(550,400);//ancho x alto
            Create.setResizable(false);
            Create.jdc_birthday.getDateEditor().setEnabled(false);
            Create.jdc_update.getDateEditor().setEnabled(false);

            //this.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada

            Create.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    Create.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    //JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                    new client_controler(new client_table(), 0).Init(0);
                    Create.dispose();
                }
            });

            Create.jb_save.setActionCommand("c_jb_save");
            Create.jb_save.addActionListener(this);

            Create.jb_clean.setActionCommand("c_jb_clean");
            Create.jb_clean.addActionListener(this);

            Create.jl_back.setName("c_jl_back");
            Create.jl_back.addMouseListener(this);

            Create.jt_dni.setName("c_jt_dni");
            Create.jt_dni.addKeyListener(this);
            Create.jt_dni.addFocusListener(this);

            Create.jt_name.setName("c_jt_name");
            Create.jt_name.addKeyListener(this);
            Create.jt_name.addFocusListener(this);

            Create.jt_surname.setName("c_jt_surname");
            Create.jt_surname.addKeyListener(this);
            Create.jt_surname.addFocusListener(this);

            Create.jt_mobile.setName("c_jt_mobile");
            Create.jt_mobile.addKeyListener(this);
            Create.jt_mobile.addFocusListener(this);

            Create.jt_email.setName("c_jt_email");
            Create.jt_email.addKeyListener(this);
            Create.jt_email.addFocusListener(this);

            Create.jt_nick.setName("c_jt_nick");
            Create.jt_nick.addKeyListener(this);
            Create.jt_nick.addFocusListener(this);

            Create.jt_password.setName("c_jt_password");
            Create.jt_password.addKeyListener(this);
            Create.jt_password.addFocusListener(this);

            Create.chb_password.setActionCommand("c_chb_password");
            Create.chb_password.addActionListener(this);

            Create.jb_avatar.setActionCommand("c_jb_avatar");
            Create.jb_avatar.addActionListener(this);

            Create.jt_shopping.setName("c_jt_shopping");
            Create.jt_shopping.addKeyListener(this);
            Create.jt_shopping.addFocusListener(this);
            
            Create.jt_type.setName("c_jt_type");
            Create.jt_type.addKeyListener(this);
            Create.jt_type.addFocusListener(this);

            //**property change**// 

    //        Create.jdc_birthday.setActionMap(am);
    //        Create.jdc_birthday.addPropertyChangeListener(listener);

        
        }
        
        if(op==2){
            
            Update.setVisible(true);
            System.out.println(Update.DNI);
            
            if(singleton.type_login=="client"){
                BLL_client.show_client_MG(Update.DNI);
            }else{
                BLL_client.show_client(Update.DNI);
            }
        
            Update.setTitle("Modify admin");
            Image icono=Toolkit.getDefaultToolkit().getImage(singleton_client.icon_client);
            Update.setIconImage(icono);
            Update.setLocationRelativeTo(null);
            //Update.setSize(550,400);//ancho x alto
            Update.setResizable(false);
            //this.jsc_form.getVerticalScrollBar().setUnitIncrement(10);
            Update.jdc_birthday.getDateEditor().setEnabled(false);
            Update.jdc_update.getDateEditor().setEnabled(false);

            Update.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    Update.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    //JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                    if(singleton.type_login=="client"){
                        new controler_menu(new log_in(), 2).Init(2);
                        Update.dispose();
                    }else{
                      new client_controler(new client_table(), 0).Init(0);
                        Update.dispose();
                      
                    }                    
                }
            });
                    
            Update.jb_save.setActionCommand("u_jb_save");
            Update.jb_save.addActionListener(this);

            if(singleton.type_login=="client"){
                Update.jl_back.setText("Log out");
            }
            Update.jl_back.setName("u_jl_back");
            Update.jl_back.addMouseListener(this);

            Update.jt_name.setName("u_jt_name");
            Update.jt_name.addKeyListener(this);
            Update.jt_name.addFocusListener(this);

            Update.jt_surname.setName("u_jt_surname");
            Update.jt_surname.addKeyListener(this);
            Update.jt_surname.addFocusListener(this);

            Update.jt_mobile.setName("u_jt_mobile");
            Update.jt_mobile.addKeyListener(this);
            Update.jt_mobile.addFocusListener(this);

            Update.jt_email.setName("u_jt_email");
            Update.jt_email.addKeyListener(this);
            Update.jt_email.addFocusListener(this);

            Update.jt_nick.setName("u_jt_nick");
            Update.jt_nick.addKeyListener(this);
            Update.jt_nick.addFocusListener(this);

            Update.jt_password.setName("u_jt_password");
            Update.jt_password.addKeyListener(this);
            Update.jt_password.addFocusListener(this);

            Update.chb_password.setActionCommand("u_chb_password");
            Update.chb_password.addActionListener(this);

            Update.jb_avatar.setActionCommand("u_jb_avatar");
            Update.jb_avatar.addActionListener(this);

            Update.jt_shopping.setName("u_jt_activity");
            Update.jt_shopping.addKeyListener(this);
            Update.jt_shopping.addFocusListener(this);
            
            Update.jt_type.setName("c_jt_type");
            Update.jt_type.addKeyListener(this);
            Update.jt_type.addFocusListener(this);
        }
        
    }
    
    /**
     * funtion to detect action changes in views
     * @param cli 
     */
    public void actionPerformed(ActionEvent cli) {
        
        switch(Action.valueOf(cli.getActionCommand())){
            
            case primero:
                pagina.currentPageIndex = 1;
                pagina.initLinkBox();
                break;
            case ANTERIOR:
                pagina.currentPageIndex -= 1;
                pagina.initLinkBox();
                break;
            case SIGUIENTE:
                pagina.currentPageIndex += 1;
                pagina.initLinkBox();
                break;
            case ultimo:
                pagina.currentPageIndex = pagina.maxPageIndex;
                pagina.initLinkBox();
                break;
            case ComboPager:
                pagina.itemsPerPage=Integer.parseInt(table.jComboBox1.getSelectedItem().toString());
                pagina.currentPageIndex = 1;
                pagina.initLinkBox();
                break;
            case Combofiltrar:
                pagina.currentPageIndex = 1;
                ((miniSimpleTableModel_client)table.TABLA.getModel()).filtrar();
                ((JComboBox)combo_client).requestFocus();
                break;
            case t_jb_search:
                client_update.DNI=table.jt_dni.getText();
                new client_controler(new client_update(), 2).Init(2);

                table.ask_dni.dispose();
                table.dispose();
                break;
            case t_jb_cancel:
                table.ask_dni.dispose();
                break;
            case c_jb_save:
                BLL_client.create();
                if(BLL_client.ok==true){
                    
                    Timer delay=new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Create.dispose();
                            new client_controler(new client_table(), 0).Init(0);
                        }
                    });

                    delay.setRepeats(false);
                    delay.start();
                    Create.jt_alert.setText("User created correctly");

                    break;
                }else{
                    Create.jt_alert.setText("Change the Id card");
                    break;
                }
            case c_jb_clean:
                BLL_client.clean();
                break;
            case c_chb_password:
                if(Create.chb_password.isSelected()==false){
                    Create.jt_password.setEchoChar('*'); 
                 }
                 Create.jt_password.requestFocus();
                 break;
            case c_jb_avatar:
                int select;
        
                select=Create.jfc_avatar.showOpenDialog(Create);
                if(select==Create.jfc_avatar.APPROVE_OPTION){
                    Create.jt_avatar.setText(Create.jfc_avatar.getSelectedFile().toString());
                    break;
                }else{

                }
                break;
            case u_jb_save:
                if(singleton.type_login=="client"){
                    BLL_client.modify();
                }else{
                    BLL_client.modify();
                    if(BLL_client.ok==true){

                        Timer delay=new Timer(3000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Update.dispose();
                                new client_controler(new client_table(), 0).Init(0);
                            }
                        });

                        delay.setRepeats(false);
                        delay.start();
                        Update.jt_alert.setText("User created correctly");
                        break;
                    }else{
                        Update.jt_alert.setText("Error data, revise it");
                        break;
                }
                }
            case u_chb_password:
                if(Update.chb_password.isSelected()==false){
                    Update.jt_password.setEchoChar('*'); 
                 }
                 Update.jt_password.requestFocus();
                 break;
            case u_jb_avatar:
                int select2;
        
                select2=Update.jfc_avatar.showOpenDialog(Update);
                if(select2==Update.jfc_avatar.APPROVE_OPTION){
                    Update.jt_avatar.setText(Update.jfc_avatar.getSelectedFile().toString());
                    break;
                }else{

                }
                break;
        }
    }
    
    /**
     * funtion to detect mouse changes in views
     * @param cli 
     */
    @Override
    public void mouseClicked(MouseEvent cli) {
        
        switch(Action.valueOf(cli.getComponent().getName())){
            case t_jl_back:
                new controler_menu(new menu(),0).Init(0);
                table.dispose();
                break;
            case t_jl_create:
                new client_controler(new client_create(), 1).Init(1);
                table.dispose();
                break;
            case t_jl_update:
                table.ask_dni.setSize(400,150);
                table.ask_dni.setLocation(100, 100);
                table.ask_dni.setVisible(true);
                break;
            case t_jl_delete:
                BLL_client.delete_line();
                break;
            case t_jl_json:
                BLL_client.save_json();
                break;
            case t_jl_xml:
                BLL_client.save_xml();
                break;
            case t_jl_txt:
                BLL_client.save_txt();
                break;
            case c_jl_back:
                new client_controler(new client_table(), 0).Init(0);
                Create.dispose();
                break;
            case u_jl_back:
                if(singleton.type_login=="client"){
                    new controler_menu(new log_in(), 2).Init(2);
                    Update.dispose();
                    break;
                }else{
                    new client_controler(new client_table(), 0).Init(0);
                    Update.dispose();
                    break;
                }
            case tableSocio:
                if (cli.getClickCount() == 2) {
                    boolean modificar;

                    modificar = BLL_client.modifiy_line();
                    if (modificar == false) {
                        break;
                    } else {
                        table.dispose();
                        break;
                    }

                }
                break;
                            
        }
    }
    
    /**
     * funtion to detect mouse changes in views
     * @param cli 
     */
    @Override
    public void mouseEntered(MouseEvent cli) {
        
        switch(Action.valueOf(cli.getComponent().getName())){
            case t_jl_back:
                table.jl_back.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
                break;
            case t_jl_json:
                table.jl_json.setBorder(BorderFactory.createLineBorder(Color.black));
                break;
            case t_jl_xml:
                table.jl_xml.setBorder(BorderFactory.createLineBorder(Color.black));
                break;
            case t_jl_txt:
                table.jl_txt.setBorder(BorderFactory.createLineBorder(Color.black));
                break;
            case c_jl_back:
                Create.jl_back.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
                break;
            case u_jl_back:
                Update.jl_back.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
                break;
        }
    }

    /**
     * funtion to detect mouse changes in views
     * @param cli 
     */
    @Override
    public void mouseExited(MouseEvent cli) {
        
        switch(Action.valueOf(cli.getComponent().getName())){
            case t_jl_back:
                table.jl_back.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
                break;
            case t_jl_json:
                table.jl_json.setBorder(null);
                break;
            case t_jl_xml:
                table.jl_xml.setBorder(null);
                break;
            case t_jl_txt:
                table.jl_txt.setBorder(null);
                break;
            case c_jl_back:
                Create.jl_back.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
                break;
            case u_jl_back:
                Update.jl_back.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
                break;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    /**
     * funtion to detect key actions in views
     * @param cli 
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent cli) {
       switch(Action.valueOf(cli.getComponent().getName())){
            case t_jt_dni:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("dni_search");
                    table.bt_search.requestFocus();
                  }
                break;
            case c_jt_dni:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("dni");
                    Create.jt_name.requestFocus();
                }
                break;
            case c_jt_name:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("name");
                    Create.jt_surname.requestFocus();
                }
                break;
            case c_jt_surname:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("surname");
                    Create.jt_mobile.requestFocus();
                }
                break;
            case c_jt_mobile:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("mobile");
                    Create.jt_email.requestFocus();
                }
                break;
            case c_jt_email:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("email");
                    Create.jt_nick.requestFocus();
                }
                break;
            case c_jt_nick:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("nick");
                    Create.jt_password.requestFocus();
                }
                break;
            case c_jt_password:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("password");
                }
                break;
            case c_jt_shopping:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("shopping");
                }
                break;
            case c_jt_type:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.give_data("type");
                }
            case u_jt_name:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("name");
                    Update.jt_surname.requestFocus();
                }
                break;
            case u_jt_surname:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("surname");
                    Update.jt_mobile.requestFocus();
                }
                break;
            case u_jt_mobile:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("mobile");
                    Update.jt_email.requestFocus();
                }
                break;
            case u_jt_email:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("email");
                    Update.jt_nick.requestFocus();
                }
                break;
            case u_jt_nick:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("nick");
                    Update.jt_password.requestFocus();
                }
                break;
            case u_jt_password:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("password");
                }
                break;
            case u_jt_shopping:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("shopping");
                }
                break;
            case u_jt_type:
                if(cli.getKeyCode()==KeyEvent.VK_ENTER){
                    BLL_client.update_data("type");
                }
        }
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        
    }

    /**
     * funtion to detect key actions in views
     * @param cli 
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent cli) {
        switch(Action.valueOf(cli.getComponent().getName())){
            case t_jt_dni:
                BLL_client.give_data("dni_search");
                break;
            case c_jt_dni:
                BLL_client.give_data("dni");
                break;
            case c_jt_name:
                BLL_client.give_data("name");
                break;
            case c_jt_surname:
                BLL_client.give_data("surname");
                break;
            case c_jt_mobile:
                BLL_client.give_data("mobile");
                break;
            case c_jt_email:
                BLL_client.give_data("email");
                break;
            case c_jt_nick:
                BLL_client.give_data("nick");
                break;
            case c_jt_password:
                if(Create.chb_password.isSelected()){
                    Create.jt_password.setEchoChar((char)0);
                    BLL_client.give_data("password");
                }else{
                    Create.jt_password.setEchoChar('*');
                    BLL_client.give_data("password");
                }
                break;
            case c_jt_shopping:
                BLL_client.give_data("shopping");
                break;
            case c_jt_type:
                BLL_client.give_data("type");
                break;
            case u_jt_name:
                BLL_client.update_data("name");
                break;
            case u_jt_surname:
                BLL_client.update_data("surname");
                break;
            case u_jt_mobile:
                BLL_client.update_data("mobile");
                break;
            case u_jt_email:
                BLL_client.update_data("email");
                break;
            case u_jt_nick:
                BLL_client.update_data("nick");
                break;
            case u_jt_password:
                if(Update.chb_password.isSelected()){
                    Update.jt_password.setEchoChar((char)0);
                    BLL_client.update_data("password");
                }else{
                    Update.jt_password.setEchoChar('*');
                    BLL_client.update_data("password");
                }
                break;
            case u_jt_shopping:
                BLL_client.update_data("shopping");
                break;
            case u_jt_type:
                BLL_client.update_data("type");
                break;
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        
    }

    /**
     * funtion to detect focus changes in views
     * @param cli 
     */
    @Override
    public void focusLost(FocusEvent cli) {
        switch(Action.valueOf(cli.getComponent().getName())){
            case c_jt_dni:
                BLL_client.give_data("dni");
                break;
            case c_jt_name:
                BLL_client.give_data("name");
                break;
            case c_jt_surname:
                BLL_client.give_data("surname");
                break;
            case c_jt_mobile:
                BLL_client.give_data("mobile");
                break;
            case c_jt_email:
                BLL_client.give_data("email");
                break;
            case c_jt_nick:
                BLL_client.give_data("nick");
                break;
            case c_jt_password:
                BLL_client.give_data("password");
                break;
            case c_jt_shopping:
                BLL_client.give_data("shopping");
                break;
            case c_jt_type:
                BLL_client.give_data("type");
                break;
            case u_jt_name:
                BLL_client.update_data("name");
                break;
            case u_jt_surname:
                BLL_client.update_data("surname");
                break;
            case u_jt_mobile:
                BLL_client.update_data("mobile");
                break;
            case u_jt_email:
                BLL_client.update_data("email");
                break;
            case u_jt_nick:
                BLL_client.update_data("nick");
                break;
            case u_jt_password:
                BLL_client.update_data("password");
                break;
            case u_jt_shopping:
                BLL_client.update_data("shopping");
                break;
            case u_jt_type:
                BLL_client.update_data("type");
                break;
        }
    }
    
}
