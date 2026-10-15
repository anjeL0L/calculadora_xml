package calculadora;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LectorXML {

    public static List<Double> extraerVariable(String fileName, String nombreVariable) throws Exception {
        File inputFile = new File(fileName);
        
        if (!inputFile.exists()) {
            throw new FileNotFoundException("El documento XML '" + fileName + "' no existe o no se puede abrir.");
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        List<Double> lista = new ArrayList<>(); 
        NodeList nodeList = doc.getElementsByTagName(nombreVariable);

        if (nodeList.getLength() == 0) {
            throw new IllegalArgumentException("La variable '" + nombreVariable + "' no existe en el documento.");
        }

        for (int i = 0; i < nodeList.getLength(); i++) {
            String contenido = nodeList.item(i).getTextContent().trim();
            if (!contenido.isEmpty()) {
                lista.add(Double.parseDouble(contenido));
            }
        }
        return lista;
    }
}