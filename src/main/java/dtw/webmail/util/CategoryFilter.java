/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import dtw.webmail.model.JwmaContact;

public class CategoryFilter implements ContactFilter {

  private String m_Category = "";

  public CategoryFilter(String category) {
    m_Category = category;
  }//constructor

  public boolean isFiltered(JwmaContact contact) {
    if (m_Category.length() == 0) {
      return false;
    } else {
      return !contact.getCategory().equals(m_Category);
    }
  }//isFiltered

  public boolean isAllowed(JwmaContact contact) {
    if (m_Category.length() == 0) {
      return true;
    } else {
      return contact.getCategory().equals(m_Category);
    }
  }//isAllowed

  public String getCategory() {
    return m_Category;
  }//getCategory

  public void setCategory(String category) {
    m_Category = category;
  }//setCategory

}//CategoryFilter
