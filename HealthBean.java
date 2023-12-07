package bean;
import java.io.Serializable;

public class HealthBean implements Serializable{
	private int id;
	private String name;
	private double height;
	private double weight;
	private double bmi;
	private double targetBmi;
	private double targetWeight;
	
	public void setId(int id){this.id=id;}
	public void setName(String name) {this.name=name;}
	public void setHeight(double height) {this.height=height;}
	public void setWeight(double weight) {this.weight=weight;}
	public void setBmi(double bmi) {this.bmi=bmi;}
	public void setTargetBmi(double targetBmi) {this.targetBmi=targetBmi;}
	public void setTargetWeight(double targetWeight) {this.targetWeight=targetWeight;}
	
	public int getId() {return id;}
	public String getName() {return name;}
	public double getHeight() {return height;}
	public double getWeight() {return weight;}
	public double getBmi() {return bmi;}
	public double getTargetBmi() {return targetBmi;}
	public double getTargetWeight() {return targetWeight;}

}
