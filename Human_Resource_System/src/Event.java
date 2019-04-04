import java.io.IOException;

public class Event {
	private String postDate, title, start, end;
    private int cost, id;
    
    public Event(String postDate, String title, String start, String end, int cost, int id){
    	this.postDate = postDate;
    	this.title = title;
    	this.start = start;
    	this.end = end;
    	this.cost = cost;
    	this.id = id;
    }
    
    public String getPostDate() {
        return postDate;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getStartDate() {
        return start;
    }
    
    public String getEndDate() {
        return end;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getID() {
        return id;
    }
    
    public void setStartDate(String newDate) {
    	start = newDate;
    }
    
    public void setEndDate(String newDate) {
    	end = newDate;
    }
    
    public void setCost(int newCost) {
    	cost = newCost;
    }
}
