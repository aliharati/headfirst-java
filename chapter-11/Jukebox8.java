import java.util.*;

public class Jukebox8 {
    
    public static void main(String[] args) {
        new Jukebox8().go();

    }

    public void go(){
        List<SongV4> songList = MockMoreSongs.getSongsV3();
        System.out.println(songList);

        songList.sort((one,two) -> one.getTitle().compareTo(two.getTitle()));
        System.out.println(songList);
        songList.sort((one,two) -> two.getTitle().compareTo(one.getTitle()));
        System.out.println(songList);
        songList.sort((one,two) -> one.getBpm().compareTo(two.getBpm()));
        System.out.println(songList);

        Set<SongV4> songSet = new HashSet<>(songList);
        System.out.println(songSet);
        Set<SongV4> songSet2 = new TreeSet<>(songList);
        System.out.println(songSet2);

        Set<SongV4> songSet3 = new TreeSet<>((one,two)-> one.getBpm().compareTo(two.getBpm()));
        songSet3.addAll(songList);
        System.out.println(songSet3);
    }
}

class MockMoreSongs{
    public static List<SongV4> getSongsV3(){
        List<SongV4> songs = new ArrayList<>();
        songs.add(new SongV4("somersault", "zero 7", 147));
        songs.add(new SongV4("cassidy", "grateful dead", 158));
        songs.add(new SongV4("cassidy", "grateful dead", 158));
        songs.add(new SongV4("$10", "hitchhiker", 140));
        songs.add(new SongV4("$10", "hitchhiker", 140));
        songs.add(new SongV4("havana", "cabello", 105));
        songs.add(new SongV4("50 ways", "simon", 102));
        return songs;
    }
}

class SongV4 implements Comparable<SongV4>{
    private String title;
    private String artist;
    private int bpm;

    SongV4(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public int compareTo(SongV4 s){
        return title.compareTo(s.getTitle());
    }
    public boolean equals(Object aSong){
        SongV4 other = (SongV4) aSong;
        return title.equals(other.getTitle()) && artist.equals((other.getArtist()));
    }
    public int hashCode() {
        return title.hashCode();
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


