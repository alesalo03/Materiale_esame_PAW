
package it.unibo.paw;

import java.io.*;
import java.util.Vector;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class MainXMLParser {
	
	public static void main(String[] args) throws Exception{
		
		String xmlFilename;

		try {
			xmlFilename = "resources/AddressList.xml"; //modify
			
			String schemaFeature = "http://apache.org/xml/features/validation/schema";
			
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(true);
			spf.setNamespaceAware(true); //Riconosce i prefissi dei namespace, se messo a false li ignora del tutto
			SAXParser saxParser = spf.newSAXParser();
				
				//aggancio i listener al lettore XML
				XMLReader xmlReader = saxParser.getXMLReader();
				ErrorHandler errorHandler = new ErrorHandler();
			    xmlReader.setErrorHandler(errorHandler);
				SAXContentHandler handler = new SAXContentHandler(); //modify
			    xmlReader.setContentHandler(handler);

			    // utilizzo la seguente istruzione per specificare che stiamo validando tramite XML Schema 
				xmlReader.setFeature(schemaFeature,true);
				
				//parsifico il documento
				xmlReader.parse(xmlFilename);
			    
				//visualizzo il risultato
				//modify
				System.out.println("SAX IgnorableWhitespace = " + handler.getIgnorableWhitespace());
				System.out.println("SAX PeopleAmount = " + handler.getPeopleAmount());
				System.out.println("SAX PeoplePreMM = " + handler.getPeoplePreMM());
				System.out.println("SAX DonTel = " + handler.getDonTel());
				
				//lo stampo sul file 
				String outputFileName = "resources/output.txt"; //modify

				try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {//modify
				    writer.write("=== Risultati parsing SAX ===\n");
				    writer.write("IgnorableWhitespace: " + handler.getIgnorableWhitespace() + "\n");
				    writer.write("PeopleAmount: " + handler.getPeopleAmount() + "\n");
				    writer.write("PeoplePreMM: " + handler.getPeoplePreMM() + "\n");

				    Vector<String> donTel = handler.getDonTel();
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
}
