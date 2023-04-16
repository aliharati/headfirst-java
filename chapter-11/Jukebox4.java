import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Jukebox4 {
    
    public static void main(String[] args) {
        new Jukebox4().go();

    }

    public void go(){
        List<SongV2> songList = MockSongs.getSongsV2();
        System.out.println(songList);

        TitleCompare titleCompare = new TitleCompare();
        songList.sort(titleCompare);
        System.out.println(songList);

        ArtitstCompare artitstCompare = new ArtitstCompare();
        songList.sort(artitstCompare);
        System.out.println(songList);


    }
}

class ArtitstCompare implements Comparator<SongV2>{
    public int compare(SongV2 one,SongV2 two){
        return one.getArtist().compareTo(two.getArtist());
    }
}
class TitleCompare implements Comparator<SongV2>{
    public int compare(SongV2 one,SongV2 two){
        return one.getTitle().compareTo(two.getTitle());
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
    public int getBpm(){
        return bpm;
    }
    public String toString(){
        return title;
    }
}

