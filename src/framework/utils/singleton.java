/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.utils;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import framework.classes.Mongo_DB;
import java.io.InputStream;
import javax.swing.ImageIcon;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author pelu
 */
public class singleton {
    
    public static String type_login=null;
    
    public static Mongo_DB mongo = null;
    public static Mongo client=null;
    public static BasicDataSource dataSource=null;
    public static InputStream input = null;
    public static String machine = null;
    public static String port = null;
    public static DB db = null;
    public static String nom_bd = null;
    public static DBCollection collection = null;
    public static String nom_table = null;
    
    public static ImageIcon empty= new ImageIcon("");
    public static ImageIcon apply= new ImageIcon("src/framework/img/apply.png");
    public static ImageIcon cancel= new ImageIcon("src/framework/img/cancel.png");
    public static ImageIcon setting= new ImageIcon("src/framework/img/setting.png");
    public static ImageIcon setting2= new ImageIcon("src/framework/img/setting2.png");
    public static ImageIcon create= new ImageIcon("src/framework/img/create.png");
    public static ImageIcon modify= new ImageIcon("src/framework/img/modify.png");
    public static ImageIcon delete= new ImageIcon("src/framework/img/delete.png");
    
    public static String icon_app= "src/framework/img/icon_app.png";
    public static ImageIcon icon_u_admin= new ImageIcon("src/framework/img/icon_u_admin.png");
    public static ImageIcon icon_u_client= new ImageIcon("src/framework/img/icon_u_client.png");
    public static ImageIcon icon_u_reg= new ImageIcon("src/framework/img/icon_u_reg.png");
    public static ImageIcon icon_user= new ImageIcon("src/framework/img/icon_user.png");
    
    public static String fondo_menu= "src/framework/img/fondo_menu.png";
    
}
