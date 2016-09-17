/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.directory;

import java.util.*;
import java.text.*;
import javax.naming.*;
import javax.naming.directory.*;
import javax.mail.internet.InternetAddress;

import dtw.webmail.model.JwmaException;
import dtw.webmail.util.*;

/**
 * Class implementing utility methods for using
 * the directory framework.
 * <p>
 * <b>This class is under construction.</b>
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class DirectoryUtil {

  //instance attributes
  DirectoryManager m_DirectoryManager;
  SearchControls m_ValidateControls;

  /**
   * Constructs a new <tt>DirectoryUtil</tt> instance.
   */
  public DirectoryUtil(DirectoryManager mgr) {
    m_DirectoryManager = mgr;
    m_ValidateControls = new SearchControls();
  }//constructor

  public boolean validateRecipient(String address)
      throws JwmaException {

    boolean result = false;

    try {
      InternetAddress iadd = new InternetAddress(address);
      String[] addrparts = StringUtil.split(iadd.getAddress(), "@");
      ContextPool ctxp = m_DirectoryManager
          .getValidationContextPool(addrparts[1]);
      DirContext ctx = null;

      try {
        ctx = ctxp.leaseContext();
        //prepare fill in
        Object[] obj = {addrparts[0], addrparts[1], address};
        String filter = "(" + MessageFormat.format(ctxp.getBaseFilter(), obj) + ")";
        //search entry
        NamingEnumeration ne = ctx.search(ctxp.getSearchName(), filter, m_ValidateControls);
        //return if an entry was found
        result = ne.hasMore();
      } catch (NamingException nex) {
        throw new JwmaException(nex.getMessage(), true).
            setException(nex);
      } finally {
        ctxp.releaseContext(ctx);
      }

    } catch (Exception ex) {

    }
    return result;
  }//validateRecipient

  public List lookupEntry(String lookupname, String filter) {
    List entries = new ArrayList(25);
    try {
      ContextPool ctxp = m_DirectoryManager
          .getLookupContextPool(lookupname);
      DirContext ctx = null;

      try {
        ctx = ctxp.leaseContext();
        //set search controls, including returning attributes
        SearchControls searchControls = new SearchControls();
        searchControls.setReturningAttributes(ctxp.getResultAttributes());
        //makeup filter logically & with the base filter if given
        String baseFilter = ctxp.getBaseFilter();
        if (baseFilter == null || baseFilter.length() == 0) {
          filter = "(" + filter + ")";
        } else {
          filter = "(& (" + baseFilter + ") (" + filter + "))";
        }

        //search entries
        NamingEnumeration results = ctx.search(
            ctxp.getSearchName(),
            filter,
            searchControls
        );

        //create directory entries from results
        while (results.hasMore()) {

          //fetch result
          SearchResult sr = (SearchResult) results.next();
          //DEBUG:show name
          //System.out.println(">>>"+sr.getName());
          //fetch attributes
          //entries.add(new DirectoryEntryImpl(sr.getAttributes()));
        }

      } catch (NamingException nex) {
        throw new JwmaException(nex.getMessage(), true).
            setException(nex);
      } finally {
        ctxp.releaseContext(ctx);
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return entries;
  }//lookupEntry

  public static Attributes createAttributes(HashMap attrs, boolean ignoreCase) {
    Attributes attributes = new BasicAttributes(ignoreCase);

    for (Iterator iter = attrs.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      //fill in the attribute
      attributes.put(new BasicAttribute(name, attrs.get(name)));
    }
    System.out.println(attributes.toString());

    return attributes;
  }//createAttributes


}//class DirectoryUtil



