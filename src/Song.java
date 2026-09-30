import java.util.Arrays;

public class Song {
	
	private static final String INFO_DELIMITER = ";";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	private String title;
	private String artist;
	private int[] time;
		
	public static void main(String[] args) {}
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	public String toString() {
		String timeStr = "";
		if (time.length == 1) {
			timeStr = String.valueOf(time[0]);
		} else if (time.length == 2) {
			timeStr = String.format("%d:%02d", time[1], time[0]);
		} else if (time.length == 3) {
			timeStr = String.format("%d:%02d:%02d", time[2], time[1], time[0]);
		}
		return title + "; " + artist + "; " + timeStr;
	}
	
	public Song(String fileLine) {
		String[] parts = fileLine.split(INFO_DELIMITER);
		if (parts.length == 3) {
			this.title = parts[IDX_TITLE].trim();
			this.artist = parts[IDX_ARTIST].trim();
			String[] timeParts = parts[IDX_TIME].trim().split(TIME_DELIMITER);
			this.time = new int[timeParts.length];
			for (int i = 0; i < timeParts.length; i++) {
				this.time[i] = Integer.parseInt(timeParts[timeParts.length - 1 - i]);
				}
			}
		}
	}
