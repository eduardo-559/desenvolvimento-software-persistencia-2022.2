package geraJson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class geraJson {
    public static void main(String[] args) throws Exception{
        try (
            Scanner cantor = new Scanner(System.in)) {
                
            System.out.println("Digite o nome do cantor: ");
            String nome = cantor.nextLine();

            System.out.println("Digite o ritmo do cantor: ");
            String ritmo = cantor.nextLine();

            System.out.println("Digite a cidade do cantor: ");
            String cidade = cantor.nextLine();

            System.out.println("Digite o estado do cantor: ");
            String estado = cantor.nextLine();

            System.out.println("Digite a idade do cantor: ");
            int idade = cantor.nextInt();

            Cantor c1 = new Cantor(nome, ritmo, cidade, estado, idade);

            FileWriter arq = null;
            JSONObject objetoJson = new JSONObject();
            
            objetoJson.put("Nome", c1.getNome());
            objetoJson.put("Ritmo", c1.getRitmo());
            objetoJson.put("Cidade", c1.getCidade());
            objetoJson.put("Estado", c1.getEstado());
            objetoJson.put("Idade", c1.getIdade());

            try {
                arq = new FileWriter("C:\\Users\\edudu\\OneDrive\\Documentos\\NetBeansProjects\\GeraJson\\src\\geraJson\\arquivo.json");
                arq.write(objetoJson.toJSONString());
                arq.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println(objetoJson.toJSONString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }   
}