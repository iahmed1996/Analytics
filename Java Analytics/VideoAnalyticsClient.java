package viewTube;

public class VideoAnalyticsClient {
	public static String getLog() {
		//create an array named first with the values 10, 70, 20 and 90 (in that order)
		int [] first = {10, 70, 20, 90};
		//create a VideoAnalytics object named performance1 with the title "Taxicab" and the array first
		VideoAnalytics performance1 = new VideoAnalytics ("Taxicab", first);
		//create an array named second with the values 10, 70, 20, 90, 30 and 80 (in that order)
		int [] second = {10, 70, 20, 90, 30, 80};
		//create a VideoAnalytics object named performance2 with the title "Rule of third" and the array second		
		VideoAnalytics performance2 = new VideoAnalytics ("Rule of third", second);
		//Initialize a String named result to a blank string
		String result = "";
		//add how many days' records are held in performance1 followed by newline to result
		result += performance1.getHistoryLength() + "\n";
		//add total views for performance1 followed by newline to result
		result += performance1.getTotalViews() + "\n";
		//add index of the day (0 - first) with the least views for performance1 followed by newline to result
		result += performance1.indexOfLeastViews() + "\n";
		//add highest view count for performance1 followed by newline to result
		result += performance1.highestViewCount() + "\n";
		//add value returned when comparing performance1 (as calling object) with performance2, followed by newline to result
		result += performance1.compareTo(performance2) + "\n";
		//add value returned when comparing performance2 with performance1, followed by newline to result
		result += performance2.compareTo(performance1) + "\n";
		//add value returned when calling toString on performance1 to result (no newline at the end)
		result += performance1.toString();
		//return the constructed string (result)
		return result;
	}
}
