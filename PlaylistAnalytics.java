//UNASSESSED
package viewTube;

public class PlaylistAnalytics {
	public String name;
	public VideoAnalytics[] videoStats;
	
	public PlaylistAnalytics(String name, VideoAnalytics[] videoStats) {
		this.name = name;
		this.videoStats = videoStats;
	}
	
	/**
	 * 
	 * @return an array containing the most views in each item of videoStats
	 */
	public int[] getMostViews() {
		if(videoStats == null) {
			return null;
		}
		int[] result = new int[videoStats.length];
		
		for(int i=0; i < videoStats.length; i++) {
			result[i] = videoStats[i].highestViewCount();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return the title of the most popular video across all videos.
	 * return the title of the first video in case of a tie
	 */
	public String mostPopularVideo() {
		if(videoStats == null || videoStats.length == 0) {
			return null;
		}
		
		int idx = 0;
		
		for(int i=1; i < videoStats.length; i++) {
			if(videoStats[i].getTotalViews() > videoStats[idx].getTotalViews()) {
				idx = i;
			}
		}
		
		return videoStats[idx].title;
	}
	
	/**
	 * sort the items of the array videoStats in descending order of number of views
	 */
	public void sort() {
		for(int i=1; i < videoStats.length; i++) {
			int k=i-1;
			VideoAnalytics backup = videoStats[i];
			while(k>=0 && videoStats[i].compareTo(backup) > 0) {
				videoStats[i+1] = videoStats[i];
				k--;
			}
			videoStats[i+1] = backup;
		}
	}
}
