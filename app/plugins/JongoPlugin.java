package plugins;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.jongo.Jongo;
import play.Configuration;
import play.Play;
import play.Plugin;
import play.Application;

import java.net.UnknownHostException;

public class JongoPlugin extends Plugin {

    private Application application;
    private Mongo mongo;

    public JongoPlugin(Application application) {
        this.application = application;
    }

    @Override
    public void onStart() {
        Configuration config = Configuration.root().getConfig("jongo");
        String host = config.getString("mongodb.host");
        try {
            mongo = new Mongo(host);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to create Mongo instance.", e);
        }
    }

    public Jongo getJongo(String dbname) {
        DB db = mongo.getDB(dbname);
        return new Jongo(db);
    }

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
