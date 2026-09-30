import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		this.songs = new ArrayList<Song>();
	}
	
	public Playlist(String filename) throws IOException {
		this.songs = new ArrayList<Song>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				
					Song song = new Song(line);
					this.addSong(song);
				}
			}
		}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		if (song == null || index < 0 || index > getNumSongs()) {
			return false;
		}
		songs.add(index, song);
		return true;
	}
	
	public int addSongs(Playlist playlist) {
		if (playlist == null) {
			return 0;
		}
		int count = 0;
		for (Song song : playlist.getSongs()) {
			if (addSong(song)) {
				count++;
			}
		}
		return count;
	}
	
	public int addSongs(String filename) throws java.io.IOException {
		int initialSize = getNumSongs();
		try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				Song song = new Song(line);
				if (song.getTitle() != null) {
					this.addSong(song);
				}
			}
		}
		return getNumSongs() - initialSize;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		if (index < 0|| index >= getNumSongs()) {
			return null;
		}
		return songs.remove(index);
	}
	
	public void saveSongs(String filename) throws java.io.IOException {
		try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename))) {
			for (int i = 0; i < getNumSongs(); i++) {
				writer.write(getSong(i).toString());
				writer.newLine();
			}
		}
	}
	
	public int[] getTotalTime() {
		int totalSeconds = 0;
		for (int i = 0; i < getNumSongs(); i++) {
			Song song = getSong(i);
			int[] songTime = song.getTime();
			if (songTime.length == 1) {
				totalSeconds += songTime[0];
			} else if (songTime.length == 2) {
				totalSeconds += (songTime[1] * 60) + songTime[0];
			} else if (songTime.length == 3) {
				totalSeconds += (songTime[2] * 3600) + (songTime[1] * 60) + songTime[0];
			}
		}
		if (totalSeconds < 60) {
			return new int[] { totalSeconds };
		} else if (totalSeconds < 3600) {
			int mins = totalSeconds / 60;
			int secs = totalSeconds % 60;
			return new int[] { secs, mins };
		} else {
			int hours = totalSeconds / 3600;
			int mins = (totalSeconds % 3600) / 60;
			int secs = totalSeconds % 60;
			return new int[] { secs, mins, hours };
		}
	}
	
	public String toString() {
		if (songs.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < songs.size(); i++) {
			sb.append(songs.get(i).toString());
			if (i < songs.size() - 1) {
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
