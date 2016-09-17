package dtw.webmail.util.config;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;


/**
 * Class implementing a <tt>JwmaConfiguration</tt>
 * with Castor XML based persistence.
 * <p>
 * @author Dieter Wimberger (wimpi)
 * @version (created Feb 24, 2003)
 */
public class CastorXMLConfiguration {

  private static Logger log = Logger.getLogger(CastorXMLConfiguration.class);

  private File m_ConfigFile;
  private Mapping m_Mapping;

  public CastorXMLConfiguration(File configfile) {
    super();
    m_ConfigFile = configfile;
    //load mapping
    if(!loadMapping()) {
      throw new RuntimeException("Failed to load configuration mapping file.");
    }
  }//constructor


  /**
   * Loads the castor mapping file.
   *
   * @param file the url of the mapping file.
   * @return true if successfully loaded, false otherwise.
   */
  private boolean loadMapping() {
    try {
      ClassLoader cl = this.getClass().getClassLoader();
      URL mapping = cl.getResource("dtw/webmail/resources/configuration_mapping.xml");
      m_Mapping = new Mapping(cl);
      m_Mapping.loadMapping(mapping);
      log.debug("loadMapping()"+": Mapping loaded successfully.");
      return true;
    } catch (Exception ex) {
      log.fatal("loadMapping()", ex);
      return false;
    }
  }//loadMapping

  /**
   * Stores a <tt>JwmaConfiguration</tt> instance
   * to the given writer.
   *
   * @param config the instance to be marshalled.
   * @param writer the <tt>Writer</tt>.
   *
   * @throws Exception on failure.
   */
  synchronized public void store(JwmaConfiguration config)
      throws Exception {

    FileWriter writer = new FileWriter(m_ConfigFile);
    Marshaller m_Marshaller = new Marshaller(writer);

    m_Marshaller.setMapping(m_Mapping);
    m_Marshaller.marshal(config);
    writer.flush();
    writer.close();
    log.debug("store()"+": Configuration stored successfully.");
  }//marshal

  /**
   * Unmarshalls a <tt>JwmaConfiguration</tt> instance
   * from the given reader.
   *
   * @param reader the <tt>Reader</tt>.
   */
  public JwmaConfiguration load()
      throws Exception {

    JwmaConfiguration config = null;
    FileReader reader = new FileReader(m_ConfigFile);
    Unmarshaller m_Unmarshaller = new Unmarshaller(m_Mapping);
    config = (JwmaConfiguration) m_Unmarshaller.unmarshal(reader);
    reader.close();
    log.debug("load()"+": Configuration loaded successfully.");
    return config;
  }//unmarshal

}//class CastorXMLConfiguration
