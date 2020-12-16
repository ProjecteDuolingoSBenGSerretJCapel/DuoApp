package com.example.duoapp.Recursos;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SXmlConfigureImpl implements SXmlConfigureDAO{


    @Override
    public Document getMyGetDocument(String fileUrl) throws ParserConfigurationException, IOException {
        File file = new File("/data/user/0/com.example.duoapp/usuariXML.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            DOMImplementation domi = db.getDOMImplementation();

            Document dUsuari = domi.createDocument(null, "Usuario", null);
            dUsuari.setXmlVersion("1.0");

            try {
                Element elementConfigure = dUsuari.createElement("Configure");

                Element elementIp = dUsuari.createElement("Ip");
                Text textIp = dUsuari.createTextNode("198.192.12.11");

                elementIp.appendChild(textIp);

                Element elementName = dUsuari.createElement("Nom");
                Text textNom = dUsuari.createTextNode("Samir");

                elementName.appendChild(textNom);

                Element elementContrasena = dUsuari.createElement("Contrasena");
                Text textContrasena = dUsuari.createTextNode("contrasena");

                elementContrasena.appendChild(textContrasena);

                Element elementMonedes = dUsuari.createElement("Monedes");
                Text textMonedes = dUsuari.createTextNode("200");

                elementMonedes.appendChild(textMonedes);

                Element elementPunts = dUsuari.createElement("Punts");
                Text textPunts = dUsuari.createTextNode("0");

                elementPunts.appendChild(textPunts);

                dUsuari.getDocumentElement().appendChild(elementConfigure);

                elementConfigure.appendChild(elementIp);
                elementConfigure.appendChild(elementName);
                elementConfigure.appendChild(elementContrasena);
                elementConfigure.appendChild(elementMonedes);
                elementConfigure.appendChild(elementPunts);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(dUsuari);
                StreamResult result = new StreamResult(new File(fileUrl));
                transformer.transform(source, result);

                return dUsuari;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }


    @Override
    public NodeList returnNodeListByDocument(Document doc, String tag) {
        NodeList lista = doc.getElementsByTagName(tag);
        return lista;
    }

    @Override
    public Element setElementByNode(Node node) {
        Element element = (Element) node;
        return element;
    }

    @Override
    public boolean modificarXML(Document document, String aModificar, String valorAModificar) {
        Node configure = document.getElementsByTagName("Configure").item(0);

        NamedNodeMap attr = configure.getAttributes();

        NodeList list = configure.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node nodes = list.item(i);

            if (aModificar.equals(nodes.getNodeName())) {
                nodes.setTextContent(valorAModificar);
                return true;
            }
        }

        return false;
    }

    @Override
    public void guardarXML(Document document, String path, File file, String novaIp, String nouNom, String novaContrasena, String monedes, String punts) throws TransformerException, ParserConfigurationException {
        if(file.exists()){
            file.delete();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            DOMImplementation domi = db.getDOMImplementation();

            Document dUsuari = domi.createDocument(null, "Usuario", null);
            dUsuari.setXmlVersion("1.0");

            try {
                Element elementConfigure = dUsuari.createElement("Configure");

                Element elementIp = dUsuari.createElement("Ip");
                Text textIp = dUsuari.createTextNode(novaIp);

                elementIp.appendChild(textIp);

                Element elementName = dUsuari.createElement("Nom");
                Text textNom = dUsuari.createTextNode(nouNom);

                elementName.appendChild(textNom);

                Element elementContrasena = dUsuari.createElement("Contrasena");
                Text textContrasena = dUsuari.createTextNode(novaContrasena);

                elementContrasena.appendChild(textContrasena);

                Element elementMonedes = dUsuari.createElement("Monedes");
                Text textMonedes = dUsuari.createTextNode(monedes);

                elementMonedes.appendChild(textMonedes);

                Element elementPunts = dUsuari.createElement("Punts");
                Text textPunts = dUsuari.createTextNode(punts);

                elementPunts.appendChild(textPunts);

                dUsuari.getDocumentElement().appendChild(elementConfigure);

                elementConfigure.appendChild(elementIp);
                elementConfigure.appendChild(elementName);
                elementConfigure.appendChild(elementContrasena);
                elementConfigure.appendChild(elementMonedes);
                elementConfigure.appendChild(elementPunts);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(dUsuari);
                StreamResult result = new StreamResult(new File(path));
                transformer.transform(source, result);


            } catch (Exception e) {
                e.printStackTrace();

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        }
        else{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        }
    }

    @Override
    public String getValueTag(Element element, String valor) {
        String s = element.getElementsByTagName(valor).item(0).getTextContent();

        return s;
    }
}
