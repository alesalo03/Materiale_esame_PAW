
package it.unibo.paw;

import java.io.*;
import java.util.Vector;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class MainXMLParser {
	
	public static void main(String[] args) throws Exception{
		
		String xmlFilename;
		
		try {
			xmlFilename = "resources/AddressList.xml";
			
			String schemaFeature = "http://apache.org/xml/features/validation/schema";
			String ignorableWhitespace = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
				
			//Costruisco un DocumentBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
				
			// utilizzo la seguente istruzione per specificare che stiamo validando tramite XML Schema
			dbf.setFeature(schemaFeature,true);
				
			// utilizzo la seguente istruzione per specificare che gli "ignorable whitespace" (tab, new line...) 
			// tra un tag ed un altro devono essere scartati e non considerati come text node
			dbf.setFeature(ignorableWhitespace, false);
				
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new ErrorHandler());
				
			//Parsifico il documento, ottenendo un documento DOM
			Document domDocument = db.parse(new File(xmlFilename));
			domDocument.getDocumentElement().normalize(); //agisce sui nodi di tipo Text per rendere il parsing più coerente e pulito
				
			//Utilizzo il documento DOM per mezzo delle funzioni definite sotto
			//modify
			System.out.println("DOM PeopleAmount = " + getPeopleAmount(domDocument));
			System.out.println("DOM PeoplePreMM = " + getPeoplePreMM(domDocument));
			System.out.println("DOM DonTel1 = " + getTel("Don", domDocument) );
			System.out.println("DOM MickeyTel1 = " + getTel("Mickey", domDocument) );
			setTel("Donald", "Duck", "1234", domDocument);
			setTel("Mickey", "Mouse", "5678", domDocument);
			System.out.println("DOM DonTel2 = " + getTel("Don", domDocument) );
			System.out.println("DOM MickeyTel2 = " + getTel("Mickey", domDocument) );
			    
			//lo stampo sul file 
			String outputFileName = "resources/output.txt";

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			    writer.write("=== Risultati parsing DOM ===\n");
			    writer.write("PeopleAmount: " + getPeopleAmount(domDocument) + "\n");
			    writer.write("PeoplePreMM: " + getPeoplePreMM(domDocument) + "\n");

			    Vector<String> donTel = getTel("Don", domDocument);
			    writer.write("DonTel:\n");
			    if (donTel.isEmpty()) {
			        writer.write("  Nessun numero trovato.\n");
			    } else {
			        for (String tel : donTel) {
			            writer.write("  - " + tel + "\n");
			        }
			    }

			    System.out.println("Risultati scritti su: " + outputFileName);

			} catch (IOException ioEx) {
			    System.err.println("Errore nella scrittura del file: " + ioEx.getMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
//modify tutte le funzioni sotto
	private static int getPeopleAmount(Document domDocument){
		NodeList informationList = domDocument.getElementsByTagName("Information");
		return informationList.getLength();
	}
	
	private static int getPeoplePreMM(Document domDocument){
		int proplePreMM = 0;
		boolean mmFound = false;

		NodeList informationList = domDocument.getElementsByTagName("Information");
		for(int i=0; !mmFound && i<informationList.getLength(); i++){
			Node information = informationList.item(i);
			
			// cerco "Full_name"
			NodeList infoChildrenNodes = information.getChildNodes();
			Node fullName = null;
			for(int j=0; fullName==null && j<infoChildrenNodes.getLength(); j++){
				Node node = infoChildrenNodes.item(j);
				if( node.getNodeName().equals("Full_name")){
					fullName = node;
				}
			}
			NodeList fullNameList = fullName.getChildNodes();
			
			// cerco "First_name" e "Last_name"
			String firstName = null, lastName = null;
			for(int j=0; j<fullNameList.getLength(); j++){
				Node el = fullNameList.item(j);
				if( el.getLocalName()!=null ){
					if ( el.getLocalName().equals("First_name")){
						firstName = el.getTextContent();
					}
					else if(el.getLocalName().equals("Last_name")){
						lastName = el.getTextContent();
					}
				}
			}
			
			//if( firstName!=null && lastName!=null ){
				if( firstName.equals("Mickey") && lastName.equals("Mouse") ){
					mmFound = true;
				}
			//}
			else{
				proplePreMM++;
			}
		}
		
		return proplePreMM;
	}
	
	private static Vector<String> getTel(String startsWith, Document domDocument){
		Vector<String> tel = new Vector<String>();
		
		NodeList informationList = domDocument.getElementsByTagName("Information");
		//System.out.println("getPeoplePreMM informationList.getLength()="+informationList.getLength());
		for(int i=0; i<informationList.getLength(); i++){
			boolean found = false;
			
			Node information = informationList.item(i);

			// cerco "Full_name"
			NodeList infoChildrenNodes = information.getChildNodes();
			Node fullName = null;
			for(int j=0; fullName==null && j<infoChildrenNodes.getLength(); j++){
				Node node = infoChildrenNodes.item(j);
				if( node.getNodeName().equals("Full_name")){
					fullName = node;
				}
			}
			NodeList fullNameList = fullName.getChildNodes();
			
			// cerco "First_name"
			for(int j=0; j<fullNameList.getLength(); j++){
				Node el = fullNameList.item(j);if( el.getLocalName()!=null ){
					if ( el.getLocalName().equals("First_name")){
						if(el.getTextContent().startsWith(startsWith)){
							found = true;
						}
					}
				}
			}
			
			if(found){
				// cerco "Telephone"
				boolean telephone = false;
				for(int j=0; !telephone && j<infoChildrenNodes.getLength(); j++){
					Node node = infoChildrenNodes.item(j);
					if( node.getNodeName().equals("Telephone")){
						telephone = true;
						tel.addElement(node.getTextContent());
					}
				}
			}
		}
		
		return tel;
	}
	
	private static void setTel(String firstName, String lastName, String newTelephone, Document domDocument){
		NodeList informationList = domDocument.getElementsByTagName("Information");
		//System.out.println("getPeoplePreMM informationList.getLength()="+informationList.getLength());
		for(int i=0; i<informationList.getLength(); i++){
			boolean foundFirstName = false;
			boolean foundLastName = false;
			
			Node information = informationList.item(i);

			// cerco "Full_name"
			NodeList infoChildrenNodes = information.getChildNodes();
			Node fullName = null;
			for(int j=0; fullName==null && j<infoChildrenNodes.getLength(); j++){
				Node node = infoChildrenNodes.item(j);
				if( node.getNodeName().equals("Full_name")){
					fullName = node;
				}
			}
			NodeList fullNameList = fullName.getChildNodes();
			
			// cerco "First_name" e "Last_name"
			for(int j=0; j<fullNameList.getLength(); j++){
				Node el = fullNameList.item(j);
				if( el.getLocalName()!=null ){
					if ( el.getLocalName().equals("First_name")){
						if(el.getTextContent().equals(firstName)){
							foundFirstName = true;
						}
					}
					else if ( el.getLocalName().equals("Last_name")){
						if(el.getTextContent().equals(lastName)){
							foundLastName = true;
						}
					}
				}
			}
			
			if( foundFirstName && foundLastName ){
				// cerco "Telephone"
				boolean telephone = false;
				for(int j=0; !telephone && j<infoChildrenNodes.getLength(); j++){
					Node node = infoChildrenNodes.item(j);
					if( node.getNodeName().equals("Telephone")){
						telephone = true;
						node.setTextContent(newTelephone);
					}
				}
				if( ! telephone ){
					Element telEl = domDocument.createElement("Telephone");
					telEl.setTextContent(newTelephone);
					information.appendChild(telEl);
				}
			}
		}
	}
	
}
