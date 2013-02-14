package plugins;

import java.util.Collection;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.DBAddress;

import org.jongo.Jongo;

import play.Configuration;
import play.Play;
import play.Plugin;
import play.Application;

import java.net.UnknownHostException;

/**
 * Jongo Play2 Plugin.
 */
public class JongoPlugin extends Plugin {
    private Application application;
    private Mongo mongo;
    private String host = "127.0.0.1:27017/test";
    private String defaultBase = "test";

    // ------------

    /**
     * Plugin constructor.
     */
    public JongoPlugin(Application application) {
        this.application = application;
    }

    // ------------

    @Override
    public void onStart() {
        Configuration config = Configuration.root().getConfig("jongo");
        host = config.getString("mongodb.host");
        try {
            DBAddress address = new DBAddress(host);
            defaultBase = address.getDBName();
            mongo = new Mongo(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to create Mongo instance.", e);
        }
    }

    /**
     * Returns an instance of Jongo opened on the default database. 
     * The default database is the one configured in the application.conf parameter
     * or an undefined value based on the connection string if none is configured.
     * @return an instance of Jongo opened on the default database. 
     */
    public Jongo getJongo() {
        return getJongo(defaultBase);
    }

    /**
     * Returns a Jongo instance for the provided database name.
     * @param dbname the dbname to use in Mongo.
     * @return the Jongo instance opened on the provided dbname.
     */
    public Jongo getJongo(String dbname) {
        DB db = mongo.getDB(dbname);
        return new Jongo(db);
    }

    /**
     * Returns the host set in the configuration.
     * @return the host set in the configuration.
     */
    public String getHost() {
        return this.host;
    }

    // ------------

    /**
     * Returns the JongoPlugin.
     * @return the JongoPlugin.
     * @throws RuntimeException if the JongoPlugin can't be obtained.
     */
    public static JongoPlugin getJongoPlugin() {
        play.Application app = Play.application();
        JongoPlugin plugin = app.plugin(JongoPlugin.class);
        if (plugin == null) {
            throw new RuntimeException("Unable to obtain Jongo Plugin. "
                    + "Check if plugin has been declared into your project in conf/play.plugins file. "
                    + "If not, please add line '20000:plugins.JongoPlugin'");
        }
        return plugin;
    }
}
