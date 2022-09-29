package compactazip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactaZip {
    public static void main(String[] args) throws Exception{
        try {
            Scanner cam = new Scanner(System.in);
            System.out.println("Digite o caminho do arquivo desejado: ");
            String camArquivo = cam.nextLine();
            
            String arquivoZip = camArquivo;
            byte[] buf = new byte[1024];
            
            String compac = "C:\\Users\\edudu\\OneDrive\\Documentos\\NetBeansProjects\\CompactaZip\\src\\compactazip\\arquivo.zip";
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(compac));
            FileInputStream in = new FileInputStream(arquivoZip);
            out.putNextEntry(new ZipEntry(arquivoZip));
            
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            
            out.closeEntry();
            in.close();
            out.close();
            
            System.out.println("Arquivo ZIP gerado!!!");
        } catch (IOException e) {
            System.out.println("Caminho informado nao existe!!!");
        }
    }
}