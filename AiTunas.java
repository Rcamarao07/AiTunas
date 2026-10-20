package Semana5;

import java.io.File;
import java.lang.classfile.instruction.StackInstruction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class AiTunas {
    private ArrayList<Album> albums;
    private ArrayList<Artists> artists;
    public AiTunas(ArrayList<Album> albums,ArrayList<Artists> artists){
        this.albums=albums;
        this.artists=artists;
    }
    public ArrayList<Album> getAllAlbuns(){
        return this.albums;
    }
    public ArrayList<Artists> getAllArtists(){
        return this.artists;
    }
    public Artists findArtist(String nomeArtista){
        for(Artists artists1 : this.artists){
            if(nomeArtista.equals(artists1.getName())){
                return artists1;
            }
        }
        return null;
    }
    public boolean hasArtist(String nomeArtista){
        for(Artists artists1 : this.artists){
            if(nomeArtista.equals(artists1.getName())){
                return true;
            }
        }
        return false;
    }
    public void addArtist(Artists artist){
        this.artists.add(artist);
    }
    public boolean songExists(Album.Song song){
        for(Album album:this.albums){
            for(Album.Song songs : album.getSongs()){
                if(songs==song){
                    return true;
                }
            }
        }
        System.out.println("Não foi encontrada a música pedida");
        return false;
    }
    public List<Artists> getArtistFromSong(Album.Song song){
        for(Album album:albums){
            for(Album.Song song1:album.getSongs()){
                if(song1==song){
                    return album.getArtists();
                }
            }
        }
        return null;
    }
    public ArrayList<Album> getArtistAlbuns(String name){
      for(Artists artist:this.artists){
          if(name.equals(artist.getName())){
              return artist.getAlbums();
          }
      }
      return null;
    }
    public ArrayList<Album.Song> getAllSongFromArtist(Artists artists){
        if(!hasArtist(artists.getName())){
            throw new IllegalArgumentException("Artista não existe");
        }
        ArrayList<Album.Song> songs1=new ArrayList<>();
        for(Album album:artists.getAlbums()){
           songs1.addAll(album.getSongs());
        }
        return songs1;
    }
    public AiTunas load(File file){
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("O ficheiro fornecido não é um diretorio");
        }
        File[] children= file.listFiles();
            for(File file1:children){
                Album album=Album.load(file1,this);
                this.albums.add(album);
            }
            this.albums.sort(Comparator.comparingInt(album->album.getYear()));
            this.artists.sort(Comparator.comparing(artist->artist.getName()));
            for(Album album:this.albums){
                album.getSongs().sort(Comparator.comparingInt(song->song.getTrackNumber()));
            }
            return this;
    }
    @Override

    public String toString() {
        String texto = "=== O MEU AITUNAS ===\n";
        texto += "\n ÁLBUNS (" + this.albums.size() + "):\n";
        for (Album album : this.albums) {
            texto += "  - " + album.getName() + " (" + album.getYear() + ")\n";
            for (Album.Song song : album.getSongs()) {
                texto += "      " + song.getTrackNumber() + ". " + song.getName() + "\n";
            }
        }
        texto += "\n ARTISTAS (" + this.getAllArtists().size() + "):\n";
        for (Artists artista : this.getAllArtists()) {
            texto += "  - " + artista.getName() + "\n";
        }
        return texto;
    }
    public class ManualPlayList{
        private ArrayList<Album.Song> songs;
        private ArrayList<Artists> artists2;
        private String name;
        public ManualPlayList(ArrayList<Album.Song> songs,ArrayList<Artists> artists){
            this.songs=songs;
            this.artists2=artists;
        }
        public void addSong(Album.Song song){
            if(!songExists(song)){
                throw new NoSuchElementException("Música não encontrada");
            }
            songs.add(song);
            artists2.addAll(getArtistFromSong(song));
        }
        public int getDuration(){
            int c=0;
            for(Album.Song song:songs){
                c=+song.getDurationInt();
            }
            return c;
        }
        @Override
        public String toString() {
            String texto = "=== PLAYLIST MANUAL: " + this.name + " ===\n";
            texto += "Duração Total: " + this.getDuration() + " segundos\n";
            texto += "Músicas (" + this.songs.size() + "):\n";

            for (Album.Song song : this.songs) {
                texto += " " + song.getName() + "\n";
            }

            return texto;
        }
    }
    public class ArtistPlaylist{
        private Artists artist;
        private ArrayList<Album.Song> songs;
        private String name;
        public ArtistPlaylist(Artists artist,String name){
            this.artist=artist;
            this.name=name;
            this.songs = new ArrayList<>();
            this.songs.addAll(getAllSongFromArtist(artist));
        }
        public int getDuration(){
            int c=0;
            for(Album.Song song:songs){
                c+=song.getDurationInt();
            }
            return c;
        }
        @Override
        public String toString() {
            String texto = "=== PLAYLIST DO ARTISTA: " + this.name + " ===\n";
            texto += "Artista: " + this.artist.getName() + "\n";
            texto += "Duração Total: " + this.getDuration() + " segundos\n";
            texto += "Músicas (" + this.songs.size() + "):\n";

            for (Album.Song song : this.songs) {
                texto += "  " + song.getName() + "\n";
            }
            return texto;
        }
    }
}
