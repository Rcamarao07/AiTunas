package Semana5;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TesteAlbum {
    void main() {
        List<Album.Song> lista = new ArrayList<>();
        List<Artists> artista = new ArrayList<>();
        Album album = new Album(artista, lista, 0, "");
        AiTunas aiTunas= new AiTunas(new ArrayList<>(),new ArrayList<>());
        File file = new File("Albuns");
        album=album.load(file,aiTunas);
        aiTunas.load(file);
        System.out.println(aiTunas);
    }
}