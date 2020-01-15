package io.github.madushanka.pos.db;


import lk.ijse.dep.crypto.DEPCrypt;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtill {
    private static EntityManagerFactory entityManagerFactory = bulidEntityManager();
    private static String password;
    private static String user;
    private static String port;
    private static String db;
    private static String ip;

    private static EntityManagerFactory bulidEntityManager() {


        File file = new File("src/main/resources/application.properties");
        Properties properties = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);

        } catch (Exception e) {
            Logger.getLogger("io.github.madushanka.pos.db.HibernateUtill").log(Level.SEVERE,"null",e);
            System.exit(2);
        }

        user=DEPCrypt.decode(properties.getProperty("javax.persistence.jdbc.user"),"123");
        password=DEPCrypt.decode(properties.getProperty("javax.persistence.jdbc.password"),"123");
        ip=properties.getProperty("pos.ip");
        db=properties.getProperty("pos.db");
        port=properties.getProperty("pos.port");

        properties.setProperty("javax.persistence.jdbc.user",user);
        properties.setProperty("javax.persistence.jdbc.password",password);

        EntityManagerFactory jpaPOS = Persistence.createEntityManagerFactory("JpaPOS", properties);
        return jpaPOS;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


    public static String getPassword(){ return password; }
    public static String getUser(){ return user; }
    public static String getPort(){return port;}
    public static String getDB(){return db; }
    public static String getIp(){return ip;}

}
