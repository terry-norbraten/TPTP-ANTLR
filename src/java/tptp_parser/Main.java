package tptp_parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path inPath = Paths.get(args[0]);
        try (Stream<Path> paths = Files.walk(inPath)) {
            paths.filter(f -> f.toString().endsWith(".p")).sorted().forEach( f ->  {
                try {
                    String input = new String(Files.readAllBytes(f));
                    ParseContext pc = ThfAstGen.parse(input,"tptp_file","nname");
                    if (pc.error){
                        System.err.println("ERR " + f.toString());
                    } else {
                        System.out.println("SUC " + f.toString());
                    }
                } catch (IOException e) {
                    System.err.println("EIO " + f.toString());
                    e.printStackTrace();
                }
                catch (ParseException e) {
                    System.err.println("Eparse" + f.toString());
                    e.printStackTrace();
                }
            });
        }
    }
}
