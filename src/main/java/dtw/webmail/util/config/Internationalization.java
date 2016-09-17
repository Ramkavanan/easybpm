package dtw.webmail.util.config;

import dtw.webmail.util.StringUtil;

import java.util.*;
import java.io.Serializable;

/** 
 * Class representing a model for i18n configuration
 * data.
 * <p>
 * @author Dieter Wimberger (wimpi)
 * @version (created Feb 24, 2003)
 */
public class Internationalization
    implements Serializable {

  private Locale m_SystemLocale;
  private Locale m_DefaultViewLocale;
  private Set m_ViewLocales;
  //private Set m_ViewLanguages;

  public Internationalization() {
    //m_ViewLanguages = Collections.synchronizedSet(new TreeSet());
    m_ViewLocales = Collections.synchronizedSet(new HashSet(10));
  }//constructor

  /**
   * Returns the system language in ISO two-letter
   * format.
   *
   * @return the language code as <tt>String</tt>.
   */
  public String getSystemLanguage() {
    return m_SystemLocale.getLanguage();
  }//getSystemLanguage

  /**
   * Sets the system language. The parameter should be
   * an ISO two-letter format string.
   *
   * @param language the language as <tt>String</tt>.
   */
  public void setSystemLanguage(String language) {
    m_SystemLocale = new Locale(language, "");
  }//setSystemLanguage

  /**
   * Returns the system locale.
   *
   * @return a <tt>Lcoale</tt> instance.
   */
  public Locale getSystemLocale() {
    return m_SystemLocale;
  }//getSystemLocale

  /**
   * Sets the system locale.
   *
   * @param locale a <tt>Locale</tt> instance.
   */
  public void setSystemLocale(Locale locale) {
    m_SystemLocale = locale;
  }//setSystemLocale

  /**
   * Returns the default view language in ISO two-letter
   * format.
   *
   * @return the language code as <tt>String</tt>.
   */
  public String getDefaultViewLanguage() {
    return m_DefaultViewLocale.getLanguage();
  }//getDefaultViewLanguage

  /**
   * Sets the default view language. The parameter should be
   * an ISO two-letter format string.
   *
   * @param language the language as <tt>String</tt>.
   */
  public void setDefaultViewLanguage(String language) {
    m_DefaultViewLocale =  new Locale(language, "");
  }//setDefaultViewLanguage


  /**
   * Returns the default view locale.
   *
   * @return a <tt>Lcoale</tt> instance.
   */
  public Locale getDefaultViewLocale() {
    return m_DefaultViewLocale;
  }//getDefaultViewLocale

  /**
   * Sets the default view locale.
   *
   * @param locale a <tt>Locale</tt> instance.
   */
  public void setDefaultViewLocale(Locale locale) {
    m_DefaultViewLocale = locale;
  }//setDefaultViewLocale

  /**
   * Convenience method for obtaining the supported languages
   * (represented in ISO two letter code) as a simple
   * comma seperated list.
   * <p>
   * Note that this is supposed to be helpful for simplifying
   * the persistency mechanism.
   *
   * @return the list as <tt>String</tt>.
   */
  public String getViewLanguageList() {
    return StringUtil.join(listViewLanguages(),",");
  }//getViewLanguageList

  /**
   * Convenience method for setting the supported languages
   * (represented in ISO two letter code) as a simple
   * comma seperated list.
   * <p>
   * Note that this is supposed to be helpful for simplifying
   * the persistency mechanism.
   *
   * @return the list as <tt>String</tt>.
   */
  public void setViewLanguageList(String list) {
    String[] locales = StringUtil.split(list,",");
    m_ViewLocales.clear();
    for (int i = 0; i < locales.length; i++) {
      addViewLocale(new Locale(locales[i],""));
    }
  }//setViewLanguageList

  /**
   * Returns the list of languages (by their ISO two
   * letter code).
   *
   * @return the list as <tt>String[]</tt>.
   */
  public String[] listViewLanguages() {
    String[] strs = new String[m_ViewLocales.size()];
    int i = 0;
    for (Iterator iterator = m_ViewLocales.iterator(); iterator.hasNext(); i++) {
      strs[i] = ((Locale)iterator.next()).getLanguage();
    }
    return strs;
  }//listViewLanguages

  /**
   * Returns a list of view languages as
   * locale instances.
   *
   * @return the view languages as <tt>Locale[]</tt>.
   */
  public Locale[] listViewLocales() {
    Locale[] strs = new Locale[m_ViewLocales.size()];
    return (Locale[]) m_ViewLocales.toArray(strs);
  }//listViewLocales

  /**
   * Adds a language, specifying it's ISO two letter
   * code.
   *
   * @param lang the language as <tt>String</tt>.
   */
  public void addViewLanguage(String lang) {
    m_ViewLocales.add(new Locale(lang,""));
  }//addViewLanguage

  /**
   * Adds a view locale.
   *
   * @param locale a <tt>Locale</tt> instance.
   */
  public void addViewLocale(Locale locale) {
    m_ViewLocales.add(locale);
  }//addViewLocale

  /**
   * Removes a view language.
   *
   * @param lang the language as <tt>String</tt>.
   */
  public void removeViewLanguage(String lang) {
    m_ViewLocales.remove(new Locale(lang, ""));
  }//removeViewLanguage

  /**
   * Removes a view locale.
   *
   * @param locale a <tt>Locale</tt> instance.
   */
  public void removeViewLocale(Locale locale) {
    m_ViewLocales.remove(locale);
  }//removeViewLocale

  /**
   * Tests if a given language (by ISO two letter code)
   * is supported.
   *
   * @param lang a <tt>String</tt> representing a language.
   * @return true if supported, false otherwise.
   */
   public boolean isSupportedViewLanguage(String lang) {
     return m_ViewLocales.contains(new Locale(lang, ""));
   }//isSupportedViewLanguage


  /**
   * Tests if a given locale is supported.
   *
   * @param loc a <tt>Locale</tt> instance.
   * @return true if supported, false otherwise.
   */
  public boolean isSupportedViewLocale(Locale loc) {
    return m_ViewLocales.contains(loc);
  }//isSupportedViewLocale

}//class Internationalization
