package jsontocsv;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class JsonToCsv {
    public static void main(String[] args) throws Exception{
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(args[0])));
            JSONObject objetoJson = new JSONObject(jsonString);
            File arquivo = new File("C:\\Users\\edudu\\OneDrive\\Documentos\\NetBeansProjects\\JsonToCsv\\src\\jsontocsv\\arquivo.csv");
            FileUtils.writeStringToFile(arquivo, titulo() + jsonString);
            System.out.println("Arquivo CSV gerado com sucesso!!!");
        } catch (Exception e) {
            System.out.println("Arquivo NAO convertido!!!");
        }
    }
    
    public static String titulo() {
        return "IDADE \t || \t RITMO \t || \t NOME \t || \t CIDADE \t || \t ESTADO \n";
    }
}