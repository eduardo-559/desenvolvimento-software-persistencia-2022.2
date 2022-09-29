package jsonpraxml;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonPraXml {
    public static void main(String[] args) throws IOException, JSONException {
        try {
            Path path = Paths.get(args[0]);
            String stringJson = Files.readAllLines(path).get(0);
            JSONObject json = new JSONObject(stringJson);

            File arqXml = new File("C:\\Users\\edudu\\OneDrive\\Documentos\\NetBeansProjects\\JsonPraXml\\src\\jsonpraxml\\teste.xml");
            XmlMapper xm = new XmlMapper();
            xm.writeValue(arqXml, XML.toString(json));
            xm.enable(SerializationFeature.INDENT_OUTPUT);
            
            System.out.println(XML.toString(json));
        } catch (IOException e) {
            System.out.println("Caminho informado nao existe!!!");
        }
    }   
}