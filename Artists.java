package Semana5;

import java.util.ArrayList;

public class Artists {
    private String name;
    private ArrayList<Album> albums;
    public Artists(String name,ArrayList<Album> albums){
        this.name=name;
        this.albums=albums;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Album> getAlbums(){
        return this.albums;
    }
    public void addAlbum(Album album){
        this.albums.add(album);
    }
}
