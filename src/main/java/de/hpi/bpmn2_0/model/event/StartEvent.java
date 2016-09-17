/**
 * Copyright (c) 2009
 * Philipp Giese, Sven Wagner-Boysen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package de.hpi.bpmn2_0.model.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.oryxeditor.server.diagram.Shape;
import org.oryxeditor.server.diagram.StencilType;


/**
 * <p>Java class for tStartEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tStartEvent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/bpmn20}tCatchEvent">
 *       &lt;attribute name="isInterrupting" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "startEvent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tStartEvent")
public class StartEvent
    extends CatchEvent
{

    @XmlAttribute
    protected Boolean isInterrupting;

    /*@XmlAttribute
    protected String form;
    
    @XmlAttribute
    protected String jspform;
    */
    @XmlAttribute
    protected String admin;
    
    @XmlAttribute
    protected String reader;
    
    @XmlAttribute
    protected String endscript;

    @XmlAttribute
    protected String process;
    
    /*@XmlAttribute
    protected String formfieldautomatic;
    
    @XmlAttribute
    protected String organizerpermission;*/
    
    @XmlAttribute
    protected String urgemessage;
    
    @XmlAttribute
    protected String notificationmessage;
    
    /**
     * Set general properties of a start event while generating its json 
     * representation
     */
    public void toShape(Shape shape) {
    	super.toShape(shape);
    	
    	shape.setStencil(new StencilType("StartNoneEvent"));
    }
    
    /* Getter & Setter */
    
    /**
     * Gets the value of the isInterrupting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsInterrupting() {
        if (isInterrupting == null) {
            return false;
        } else {
            return isInterrupting;
        }
    }

    /**
     * Sets the value of the isInterrupting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInterrupting(Boolean value) {
        this.isInterrupting = value;
    }

	/*public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
	public String getJspform() {
		return jspform;
	}

	public void setJspform(String jspform) {
		this.jspform = jspform;
	}
*/
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getProcesses() {
		return process;
	}

	public void setProcesses(String process) {
		this.process = process;
	}
	
	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getEndscript() {
		return endscript;
	}

	public void setEndscript(String endscript) {
		this.endscript = endscript;
	}

	/*public String getFormfieldautomatic() {
		return formfieldautomatic;
	}

	public void setFormfieldautomatic(String formfieldautomatic) {
		this.formfieldautomatic = formfieldautomatic;
	}
	
	public String getOrganizerPermission(){
		return organizerpermission;
	}
	
	public void setOrganizerPermission(String organizerpermission){
		this.organizerpermission = organizerpermission;
	}
	*/
	public void setUrgeMessage(String urgemessage){
		this.urgemessage = urgemessage;
	}
	
	public String getUrgeMessage(){
		return urgemessage;
	}
	
	public String getNotificationmessage() {
		return notificationmessage;
	}

	public void setNotificationmessage(String notificationmessage) {
		this.notificationmessage = notificationmessage;
	}
}
