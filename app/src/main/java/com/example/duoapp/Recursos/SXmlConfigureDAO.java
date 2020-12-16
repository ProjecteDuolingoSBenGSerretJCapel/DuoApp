package com.example.duoapp.Recursos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface SXmlConfigureDAO {

    public Document getMyGetDocument(String fileUrl) throws ParserConfigurationException, IOException;
    public NodeList returnNodeListByDocument(Document doc, String tag);
    public Element setElementByNode(Node node);
    public boolean modificarXML(Document document, String aModificar, String valorAModificar);
    public void guardarXML(Document document, String path, File file, String novaIp, String nouNom, String novaContrasena, String monedes, String punts) throws TransformerException, ParserConfigurationException;
    public String getValueTag(Element element, String valor);

}
