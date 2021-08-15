package viewTube;

public class VideoAnalytics {
	public String title;
	public int[] views;
	
	/**
	 * CONSTRUCTOR - VERY IMPORTANT!!!
	 * (make instance copies of the parameters into corresponding instance variables)
	 * WEEK 4 LECTURE COVERS EVERYTHING NEEDED FOR THE CONSTRUCTOR 
	 * @param title
	 * @param views
	 */
	public VideoAnalytics(String _title, int[] _views) {
		title = "" + _title; //adding to a NEW empty array to make an instance copy of _title
		views = deepCopy (_views); //deepCopy() returns a deep copy of array
	}
	
	/**
	 * 
	 * @return the number of days for which view history is kept
	 * return 0 if views is null
	 */
	public int getHistoryLength() {
		if (views == null)
			return 0;
		//guaranteed views array is not null
		return views.length;
	}
	
	/**
	 * 
	 * @return total number of views (return 0 if views is null)
	 */
	public int getTotalViews() {
		return sum(views); //sum() returns the sum of all items in the array
	}
	
	/**
	 * 
	 * @return the index of the day that had the least views.
	 * return null if views is null or empty
	 */
	public Integer indexOfLeastViews() {
		if (views == null || views.length < 1)
			return null;
		//guaranteed data exists in views array
		int minIndex = 0; //holds the current index with the least views
		int minViews = views[0]; //holds the current number of least views
		for (int i = 1; i < views.length; i++)
			if (views[i] < minViews) {
				minViews = views[i];
				minIndex = i;
			}
		return minIndex;
	}
	
	/**
	 * 
	 * @return the maximum number of daily views. 
	 * return 0 if views is null or empty.
	 */
	public int highestViewCount() {
		if (views == null || views.length < 1)
			return 0;
		//guaranteed data exists in views array
		int maxViews = 0; //holds the current number of maximum views, initialized to 0 (lowest possible)
		for (int i = 0; i < views.length; i++)
			if (views[i] > maxViews)
				maxViews = views[i];
		return maxViews;
	}
	
	/**
	 * 
	 * @return an array representing the number of views as a percentage
	 * of total views.
	 * for example, if views = {10, 70, 20, 90}, the total views are 190.
	 * 10 is 5.26% of 190
	 * 70 is 36.84% of 190
	 * 20 is 10.52% of 190
	 * 90 is 47.36% of 190
	 * Don't worry about the EXACT value. The test checks if each value is
	 * within 0.01 of the value expected 
	 * return null if views is null and empty array if views is an empty array
	 */
	public double[] viewsInPercentage() {
		if (views == null)
			return null;
		//guaranteed views array is not null
		double totalViews = getTotalViews();
		double [] viewsPer100 =  new double [getHistoryLength()]; //new array to hold percentages
		for (int i = 0; i < getHistoryLength(); i++) //loop will not execute when views array is empty
			viewsPer100[i] = (views[i] /totalViews) *100;
		return viewsPer100;
	}
	
	/**
	 * 
	 * @param other
	 * @return 
	 * 1 if the total views of calling object are more than the total views of parameter object
	 * -1 if the total views of calling object are less than the total views of parameter object
	 * 0 if the total views of calling object are equal to the total views of parameter object
	 */
	public int compareTo(VideoAnalytics other) {
		if (this.getTotalViews() > other.getTotalViews())
			return 1;
		if (this.getTotalViews() < other.getTotalViews())
			return -1;
		//guaranteed both totals are equal
		return 0;
	}
	
	/**
	 * return a String representation of the calling object of the form:
	 * 
	 * Title
	 * Day 1 views: <day 1 views>
	 * Day 2 views: <day 2 views>
	 * ...
	 * 
	 * (special case for when views is null or empty)
	 * 
	 * For example, 
	 * 
	 * if title = "Intro" and views = {10, 70, 20, 90}, return
	 * 
	 * "Intro
	 * Day 1 views: 10
	 * Day 2 views: 70
	 * Day 3 views: 20
	 * Day 4 views: 90"
	 * 
	 * return the String "Intro - No Views Yet"	if title = "Intro" and views is null or empty
	 */
	public String toString() {
		if (views == null || views.length < 1)
			return title +" - No Views Yet";
		//guaranteed data exists in views array
		String Views = "";
		for (int i = 0; i < getHistoryLength(); i++) {
			int day = i +1; //holds the day corresponding to index
			if (i < getHistoryLength() -1)
				Views += "Day " +day +" views: " +views[i] +"\n";
			else
				Views += "Day " +day +" views: " +views[i];
		}
		return title +"\n" +Views;
	}
	
	/**
	 * 
	 * @return the length of the longest sequence where 
	 * each item is strictly more than the item before it.
	 * return 0 if views is null or empty
	 */
	public int maxLengthIncreasingViews() {
		if (views == null || views.length < 1)
			return 0;
		//guaranteed data exists in views array
		if (views.length == 1)
			return 1;
		//guaranteed more than one entry of data exists
		int currentSequence = 1; //holds the number of views increasing days CURRENTLY being checked
		int maxSequence = 1; //holds the value for MAXIMUM increasing no. of days at present
		for (int i = 1; i < views.length; i++) {
			if (views[i] > views[i-1])
				currentSequence++;
			if (currentSequence > maxSequence)
				maxSequence = currentSequence;
			if (views[i] <= views[i-1]) 
				currentSequence = 1;
		}
		return maxSequence;
	}
	
	/**
	 * 
	 * @param n
	 * @return the view history separated in buckets of size n, 
	 * padding any extra spaces with 0s (as demonstrated in the example)
	 * return null if views is null or empty or if n is less than or equal to 0
	 * 
	 * For example, 
	 * if views = {10,70,20,30,80,40} and n = 3, return {{10,70,20},{30,80,40}}
	 * if views = {10,70,20,30,80,40} and n = 4, return {{10,70,20,30},{80,40,0,0}} 
	 */
	public int[][] buckets(int n) {
		if (views == null || views.length < 1 || n<=0)
			return null;
		//guaranteed data exists in views array and 'n' is valid
		int bucketsQty = views.length /n; //gets the number of buckets
		if (views.length %n != 0)
			bucketsQty +=1; //adds extra bucket if there's a remainder
		int [][] bucketArr = new int [bucketsQty][n];
		int viewsIndex = 0;
		for (int i = 0; i < bucketsQty; i++)
			for (int j = 0; j < n; j++) {
				if (viewsIndex >= views.length)
					bucketArr[i][j] = 0; //to pad extra spaces with 0
				//guaranteed corresponding data exists in views array
				else
					bucketArr[i][j] = views[viewsIndex];
				viewsIndex++;
			}
		return bucketArr;
	}
	
	/**
	 * 
	 * @param n
	 * @return the indices in the array where view count is at least n
	 * return null if views is null or empty
	 */
	public int[] getIndicesViewCountAtleast(int n) {
		if (views == null || views.length < 1)
			return null;
		//guaranteed data exists in views array
		int outputLen = 0; //holds the number of items qualified to be in returning array
		for (int i = 0; i < views.length; i++)
			if (views[i] >= n)
				outputLen++;
		int [] output = new int [outputLen];
		int outputIndex = 0;
		for (int i = 0; i < views.length; i++)
			if (views[i] >= n) {
				output[outputIndex] = i;
				outputIndex++;
			}
		return output;
	}
	
	/**
	 * 
	 * @param beginIndex
	 * @param endIndex
	 * @return a VideoAnalytics object that represents a slice of the calling object.
	 * For example, 
	 * 
	 * if views = {10,70,20,90} and title = "Introduction", 
	 * and the parameters are beginIndex=1, endIndex=2, you must return
	 * a VideoAnalytics object that has views = {70,20} and title = "Introduction"
	 * 
	 * if views = {10,70,20,90} and title = "Introduction", 
	 * and the parameters are beginIndex=0, endIndex=3, you must return
	 * a VideoAnalytics object that has views = {10,70,20,90} and title = "Introduction"
	 * 
	 * In the second case, if you make any changes to the array views of the calling object
	 * AFTER the function call, the array views of the returned object should NOT change.
	 * 
	 * You must return a VideoAnalytics object with required title and views = null
	 * for any invalid parameter (see test cases for most info on this)
	 */
	public VideoAnalytics slice(int beginIndex, int endIndex) {
		VideoAnalytics output = new VideoAnalytics(title, null);
		output.views = subarray(views, beginIndex, endIndex); //subarray() returns a subarray in the reqested index range
		return output;
	}
	
	/**
	 * 
	 * @param data
	 * @return return a deep copy of int array
	 */
	private int[] deepCopy(int[] data) {
		if (data == null)
			return null;
		//guaranteed array is not null
		if (data.length < 1)
			return new int[] {};
		//guaranteed data exists in array
		int [] copy = new int [data.length];
		for (int i = 0; i < data.length; i++)
			copy[i] = data[i];
		return copy;
	}
	
	/**
	 * 
	 * @param data
	 * @return sum of all data in array (return 0 if data is null or empty)
	 * Originally implemented for:
	 * COMP1010_source_codes_for_students_updated.practicePackage.arrays.attempts.Stage2
	 */
	private int sum(int[] data) {
		if (data == null || data.length == 0)
			return 0;
		//guaranteed data exists in array
		int sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += data[i];
		return sum;
	}
	
	/**
	 * 
	 * @param data
	 * @param start
	 * @param end
	 * @return sub-array from index start to index end (inclusive)
	 * return null if data is empty or null or any index is invalid
	 * Originally implemented for:
	 * COMP1010_source_codes_for_students_updated.practicePackage.arrays.attempts.Stage3
	 */
	private int[] subarray(int[] data, int start, int end) {
		if (data == null || data.length < 1 || start >= data.length || start < 0 || end >= data.length || end < 0 || end < start)
			return null;
		//guaranteed data exists in array and index boundaries are valid
		int [] output = new int [end-start +1];
		for (int i = 0; i < output.length; i++)
			output [i] = data[i +start];
		return output;
	}
}
