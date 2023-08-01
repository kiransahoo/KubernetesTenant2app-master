package com.uda.core.metahandler;

/**
 * Holds Config info in postgres store
 *
 * @author sahookir
 */



import com.fasterxml.jackson.databind.ObjectMapper;
import com.uda.core.model.UdaMetadataBean;
import com.uda.core.model.UdaSchema;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class PostgresMetadataHandler extends BaseMetadataHandler implements Serializable {

    //    @Value("${dynamodb.endpoint}")
//    private String endpoint ;
//
//    @Value("${dynamodb.region}")
//    private String region ;

    //private static final Logger logger = Logger.getLogger(PostgresMetadataHandler.class);
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.query}")
    private String query;
    private String predefinedSchemaLocation = null;
    @Override
    public void loadConfigFiles(String key, String region) {


        Connection c = null;

        Statement stmt = null;

        try {

            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection(url,username, password);

//     c.setAutoCommit(false);

            System.out.println("Successfully Connected.");

            query = query + "'" + key + "'";

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( query );
            String configdata = "";
            String  schema = "";

            while ( rs.next() ) {

                 configdata = rs.getString("config");

                  schema = rs.getString("schema");



            }
            initializeMetadata(configdata);
            rs.close();

            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }

        System.out.println(" Data Retrieved Successfully ..");

    }


//        try {
//
//            this.schema = initializeSchema(schemafileargs);
//            this.udaMetadataBean = initializeMetadata(configfileargs);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
  //  }

    @Override
    public UdaSchema initializeSchema(String schemaData) throws Exception {
        return new ObjectMapper().readValue(schemaData, UdaSchema.class);

    }

    @Override
    public UdaMetadataBean initializeMetadata(String configData) throws Exception {
//
        return new ObjectMapper().readValue(configData, UdaMetadataBean.class);
    }

    @Override
    public String getPredefinedSchemaPath(String type) {
        return this.predefinedSchemaLocation;
    }

}

