package edu.uw.util.sql;

import edu.uw.util.sql.config.PropertiesConnectionConfig;
import edu.uw.util.sql.impex.DataUtil;
import edu.uw.util.sql.impex.SchemaUtil;
import org.kuali.common.impex.model.Schema;

import javax.sql.DataSource;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by komandur on 11/3/14.
 * Path: /svn/uw-util/trunk/uw-util-sql/src/test/java/edu/uw/util/sql/ImpexExporterApp.java
 */
public class ImpexExporterApp {

    public static void main(String[] args) throws JAXBException, IOException {
        String dumpLocation = "/svn/uwcm-trunk/uwcm-deployments/uwcm-dbs/uwcm-subset-db/src/main/resources/org/kuali/uwcm/deployments/uwcm-subset-db/";
        String tableStats = "/Users/komandur/jira1388/impexTableStats.txt";

        Schema schema = SchemaUtil.loadSchemaFromLocation("/svn/uwcm-trunk/uwcm-deployments/uwcm-dbs/uwcm-subset-db/src/main/resources/org/kuali/uwcm/deployments/uwcm-subset-db/schema.xml");

        DataSource dataSource = null;
        try {
            dataSource = SchemaUtil.getJdbcDataSource(new PropertiesConnectionConfig("/Users/komandur/kuali/main/dev/uwcm-db-init.properties"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        DataUtil.dumpAllData(dumpLocation, schema, tableStats, dataSource);
    }
}
