package it.unibo.paw;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler; 

public class SAXContentHandler extends DefaultHandler {
	
    boolean inSceltaLabel = false;
    boolean inFotografia = false;
    boolean inDescrizione = false;
    boolean inPrezzo = false;

    String sceltaLabel = "";
    String fotografia = "";
    String descrizione = "";
    String prezzo = "";
    
    private Vector<String> Scelte = new Vector<String>();
    private Vector<String> Oggetti = new Vector<String>();

	//METODI
	
    public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) {
        if (localName.equals("sceltaLabel")) {
            inSceltaLabel = true;
            sceltaLabel = "";
        } else if (localName.equals("fotografia")) {
            inFotografia = true;
            fotografia = "";
        } else if (localName.equals("descrizione")) {
            inDescrizione = true;
            descrizione = "";
        } else if (localName.equals("prezzo")) {
            inPrezzo = true;
            prezzo = "";
        }
    }

    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length).trim();
        if (inSceltaLabel) {
            sceltaLabel += content;
        } else if (inFotografia) {
            fotografia += content;
        } else if (inDescrizione) {
            descrizione += content;
        } else if (inPrezzo) {
            prezzo += content;
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        if (localName.equals("sceltaLabel")) {
            inSceltaLabel = false;
        } else if (localName.equals("fotografia")) {
            inFotografia = false;
        } else if (localName.equals("descrizione")) {
            inDescrizione = false;
        } else if (localName.equals("prezzo")) {
            inPrezzo = false;
        } else if (localName.equals("oggetto")) {
            Oggetti.add("O: " + fotografia + "\t" + descrizione + "\t" + prezzo + "\n");
        } else if (localName.equals("scelta")) {
            Scelte.add("s: " + sceltaLabel + "\n" + Oggetti.toString());
            Oggetti.clear(); // svuoto per la prossima scelta
        }
    }

    public Vector<String> getScelte() {
        return Scelte;
    }
}	
	
	
	
	
	
	
	
	
	
	


