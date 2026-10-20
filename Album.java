package Semana5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

public class Album {
    private String name;
    private int year;
    private List<Artists> artists;
    private List<Song> songs;
    public Album(List<Artists> artists, List<Song> songs, int year, String name){
        this.name=name;
        this.year=year;
        this.artists=artists;
        this.songs=songs;
    }
    public String getName(){
        return this.name;
    }
    public int getYear(){
        return this.year;
    }
    public Time getDuration(){
        Time tempoTotal=new Time(0,0);
        for(Song song : songs){
           tempoTotal=tempoTotal.newAddTime(song.getDuration());
        }
        return tempoTotal;
    }
    public List<Artists> getArtists(){
        return this.artists;
    }
    public List<Song> getSongs(){
        return this.songs;
    }
    public static Album load(File file,AiTunas aiTunas){
        List<Song> musicas=new ArrayList<>();
        List<Artists> artistas=new ArrayList<>();
        String reader;
        Album album=new Album(artistas,musicas,0,"");
        int i=0;
        try {
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                reader=scanner.nextLine().trim();
                album.name=reader;
                int ano = Integer.parseInt(scanner.nextLine().trim());
                album.year=ano;
                reader=scanner.nextLine().trim(); //le a primeira linha, antes de começar o ciclo, assim tenho a certeza que não estou a guardar algo vazio
                while(!reader.isEmpty()){
                    if (!aiTunas.hasArtist(reader)) {
                        ArrayList<Album> albums=new ArrayList<>();
                        Artists artists1=new Artists(reader,albums);
                        aiTunas.addArtist(artists1);
                        artistas.add(artists1);
                    }else {
                       artistas.add(aiTunas.findArtist(reader));
                    }
                    reader = scanner.nextLine().trim();
                }
                while(scanner.hasNextLine()){
                    reader= scanner.nextLine().trim();
                    i++;
                    String[] music=reader.split(" ",2);
                    Time time=new Time(music[0]);
                    Song song =new Song(music[1],time,i);
                    musicas.add(song);
                }
                album.songs=musicas;
            }
        }catch (FileNotFoundException e){
            System.out.println("1.Ficheiro não encontrado");
        }
        for(Artists artist:artistas){
            artist.addAlbum(album);
        }
        return album;
    }
    public void sortByNumber(){
       this.songs.sort( Comparator.comparing(song->song.getTrackNumber()));
    }
    public void sortByName(){
        this.songs.sort(Comparator.comparing(song->song.getName()));
    }
    public void sortByDuration(){
        this.songs.sort(Comparator.comparingInt(song->song.getDuration().totalSegundos()));
    }
    @Override
    public String toString(){
        return "Album: "+getName()+"\nAno: "+getYear()+"\nArtistas: "+getArtists()+"\nMúsicas: "+getSongs();
    }
    static public class Song {
        private String name;
        private Time duration;
        private int trackNumber;
        public Song(String name, Time duration, int trackNumber){
            this.name=name;
            this.duration=duration;
            this.trackNumber=trackNumber;
        }
        public String getName(){
            return this.name;
        }
        public Time getDuration(){
            return this.duration;
        }
        public int getDurationInt(){
            return duration.totalSegundos();
        }
        public int getTrackNumber() {
            return this.trackNumber;
        }
        @Override
        public String toString() {
            return "\n"+getTrackNumber() + " - " + getName() + " (" + getDuration()+ ")";
        }
    }

}
