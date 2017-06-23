package com.ibm.uima.custom;

import java.io.File;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
 



import java.lang.String;

/**
 *  * @author abhishek
 *  This class parses XMI files using SAXParser and gives the details of the different requested entities.
 *
 */ 
public class XMIDocsParser {
	
	static String XMIFile = "./data/0000.xmi";	
	final static String TAGNAME = "sire2:EntityMention";
    
    /**
     * This method parses the xmi file and provides the details of the requested element
     * @argument: none
     * @return: none
     */
    public void getElementDetails(){
    	
    	NodeList listofTagsByName;
        try {
        	
        	/*Document Builder API to create parser to parse XMI files*/
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(XMIFile));
 
            doc.getDocumentElement().normalize();
        
            
            //Getting the list of elements in the provided TAGNAME
            listofTagsByName = doc.getElementsByTagName(TAGNAME);
            //length of tag elements with that TAGNAME
            int totalTagLength=listofTagsByName.getLength();
            System.out.println("total tag length:: "+totalTagLength);
            
            //parse through the tag length and get the desired attaribute.
            for (int i = 0; i < listofTagsByName.getLength(); i++) {
         	   
                Element link = (Element) listofTagsByName.item(i);

                System.out.println("SOFA ID::::: "+ link.getAttribute("sofa"));

            }            
 
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
 
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
 
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        
    }
 
    //main class
    public static void main(String args[]) {
    	
    	XMIDocsParser parsexmi=new XMIDocsParser();
    	parsexmi.getElementDetails();
    }
}
