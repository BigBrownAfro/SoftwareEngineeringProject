package application;

/**
 * Replaces Tuple in class diagram
 * holds two integers representing a start and end to a time period
 *
 */
public class TimePeriod {
	private int start;
	private int end;

	public TimePeriod(int s, int e) {
		setStart(s);
		setEnd(e);
	}
	
	private void setStart(int s) {
		start = s - (s % 25);
		
		if(start < 0) {
			start = 0;
		}else if(start > 2400) {
			start = 2400;
		}
	}
	
	private void setEnd(int e) {
		end = e - (e % 15);
		
		if(end < 0) {
			end = 0;
		}else if(end > 2400) {
			end = 2400;
		}
		if(end < start) {
			end = 2400;
			System.out.println("Error: End of time interval was before the start of interval");
		}
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
}
