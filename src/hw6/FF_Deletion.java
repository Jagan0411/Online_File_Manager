package hw6;

public class FF_Deletion {

	Integer id;
	String name;
	boolean type;
	
	public FF_Deletion(Integer id,String name,boolean type)
	{
		this.id = id;
		this.name=name;
		setType(type);;
	}
	
	public FF_Deletion(Integer id,String name)
	{
		this.id = id;
		this.name=name;
		setType(false);;
	}
	
	public FF_Deletion(Integer id)
	{
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	
	public String toString()
	{
		return "type ="+type;
	}
}
