import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Jukebox6 {
    
    public static void main(String[] args) {
        new Jukebox6().go();

    }

    public void go(){
        List<SongV2> songList = MockSongs.getSongsV2();
        System.out.println(songList);

        songList.sort((one,two) -> one.getTitle().compareTo(two.getTitle()));
        System.out.println(songList);
        songList.sort((one,two) -> two.getTitle().compareTo(one.getTitle()));
        System.out.println(songList);
        songList.sort((one,two) -> one.getArtist().compareTo(two.getArtist()));
        System.out.println(songList);
        songList.sort((one,two) -> one.getBpm().compareTo(two.getBpm()));
        System.out.println(songList);
    }
}

class MockSongs{
    public static List<SongV2> getSongsV2(){
        List<SongV2> songs = new ArrayList<>();
        songs.add(new SongV2("somersault", "zero 7", 147));
        songs.add(new SongV2("cassidy", "grateful dead", 158));
        songs.add(new SongV2("$10", "hitchhiker", 140));
        songs.add(new SongV2("havana", "cabello", 105));
        songs.add(new SongV2("Cassidy", "grateful dead", 158));
        songs.add(new SongV2("50 ways", "simon", 102));
        return songs;
    }
}

class SongV2{
    private String title;
    private String artist;
    private int bpm;

    SongV2(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }


    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public Integer getBpm(){
        return bpm;
    }
    public String toString(){
        return title;
    }
}


