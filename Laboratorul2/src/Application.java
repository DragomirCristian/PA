import java.time.LocalDate;
import java.util.Date;
public class Application extends Project {
	
	Language lang;
	public Application(String name, LocalDate localDate, Language lang)
	{
     setName(name);
	setDeadline(localDate);
	this.lang=lang;	
	}
	public String toString() { 
	    return name+" "+deadline+" "+lang;
	} 
}
