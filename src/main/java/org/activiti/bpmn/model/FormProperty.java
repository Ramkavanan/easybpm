/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.bpmn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tijs Rademakers
 */
public class FormProperty extends BaseElement {

  protected String name;
  protected String label;
  protected String value;
  protected String defValue;
  protected String expression;
  protected String variable;
  protected String type;
  protected String defaultExpression;
  protected String datePattern;
  protected Boolean readable;
  protected Boolean writeable;
  protected Boolean required;
  protected String helpText;
  protected String maxLength;
  protected String mask;
  protected String size;
  protected String onClick;
  protected String onChange;
  protected String onBlur;
  protected String onFocus;
  
protected List<FormValue> formValues = new ArrayList<FormValue>();

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public String getExpression() {
    return expression;
  }
  public void setExpression(String expression) {
    this.expression = expression;
  }
  public String getVariable() {
    return variable;
  }
  public void setVariable(String variable) {
    this.variable = variable;
  }
  public String getType() {
    return type;
  }
  public String getDefaultExpression() {
    return defaultExpression;
  }
  public void setDefaultExpression(String defaultExpression) {
    this.defaultExpression = defaultExpression;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getDatePattern() {
    return datePattern;
  }
  public void setDatePattern(String datePattern) {
    this.datePattern = datePattern;
  }
  public Boolean getReadable() {
    return readable;
  }
  public void setReadable(Boolean readable) {
    this.readable = readable;
  }
  public Boolean getWriteable() {
    return writeable;
  }
  public void setWriteable(Boolean writeable) {
    this.writeable = writeable;
  }
  public Boolean getRequired() {
    return required;
  }
  public void setRequired(Boolean required) {
    this.required = required;
  }
  public List<FormValue> getFormValues() {
    return formValues;
  }
  public void setFormValues(List<FormValue> formValues) {
    this.formValues = formValues;
  }
  
  public String getHelpText() {
		return helpText;
  }
  
  public void setHelpText(String helpText) {
	this.helpText = helpText;
  }
  
  public String getMaxLength() {
	return maxLength;
  }
  public void setMaxLength(String maxLength) {
	this.maxLength = maxLength;
  }
  public String getMask() {
	return mask;
  }
  public void setMask(String mask) {
	this.mask = mask;
  }

  public String getLabel() {
 	return label;
  }
  public void setLabel(String label) {
	this.label = label;
  }
  
  public String getSize() {
	return size;
  }
  public void setSize(String size) {
	this.size = size;
  }
public String getOnClick() {
	return onClick;
}
public void setOnClick(String onClick) {
	this.onClick = onClick;
}
public String getOnChange() {
	return onChange;
}
public void setOnChange(String onChange) {
	this.onChange = onChange;
}
public String getOnBlur() {
	return onBlur;
}
public void setOnBlur(String onBlur) {
	this.onBlur = onBlur;
}
public String getOnFocus() {
	return onFocus;
}
public void setOnFocus(String onFocus) {
	this.onFocus = onFocus;
}
public String getDefValue() {
	return defValue;
}
public void setDefValue(String defValue) {
	this.defValue = defValue;
}
  
}