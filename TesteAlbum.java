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
        aiTunas.load(file);Album primeiroAlbum = aiTunas.getAllAlbuns().get(0); // Vai buscar o primeiro álbum da lista (Voo Nocturno)
        System.out.println("\n⏳ Duração do álbum '" + primeiroAlbum.getName() + "': " + primeiroAlbum.getDuration());

        Artists sergio = aiTunas.getAllArtists().get(1); // Assumindo que o Sérgio Godinho é o segundo artista (índice 1)
        AiTunas.ArtistPlaylist playlistSergio = aiTunas.new ArtistPlaylist(sergio, "Best of Sérgio");
        System.out.println("⏳ Duração da playlist '" + playlistSergio.getName() + "': " + playlistSergio.getDuration());

        System.out.println(playlistSergio);
        System.out.println(aiTunas);
    }
}