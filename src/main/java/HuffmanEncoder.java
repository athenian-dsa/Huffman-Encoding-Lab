import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HuffmanEncoder {
    public static void main(String[] args) throws FileNotFoundException {
        // Read the original file and "compress" it
        Scanner scan = new Scanner(new File("src/original.txt"));
        PrintWriter printer = new PrintWriter("src/compressed.txt");

        String str = "";
        while (scan.hasNext()) {
            str += scan.next() + " ";
        }

        String[] code = Node.createCode(Node.createHuffmanTree(Node.countChars(str)));

        printer.println(Node.encode(str, code));
        printer.close();
        scan.close();

        // Read the compressed file and uncompress it
        scan = new Scanner(new File("src/compressed.txt"));
        printer = new PrintWriter("src/uncompressed.txt");

        str = "";
        while (scan.hasNext()) {
            str += scan.next() + " ";
        }

        printer.println(Node.decode(str, code));
        printer.close();
        scan.close();
    }
}