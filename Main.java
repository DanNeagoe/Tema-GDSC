import java.io.*;
import java.util.*; 


abstract class Echipa {
	 protected Membru lider;
	 private int nr = 0;
	 private int nr_max;
	 private String nume;
	 protected ArrayList<Membru> membrii = new ArrayList<>();
	 
	 public Echipa(String nume, int nr_max) {
		 this.nume=nume;
		 this.nr_max=nr_max;
	 }
	 
	 public boolean addMember(Membru member) {
		 if(nr<=nr_max-1) {
		 membrii.add(member);
		 nr++;
		 return true;
	     }
		 else return false;
	 }
	 
	 
	public boolean setLeader(Membru liderNou) {
		if(liderNou.experienta>=5) {
			lider=liderNou;
			return true;
		}
		else return false;
	}
	
	public Membru removeMember(Membru member) {
		for(int i=0;i<=membrii.size()-1;++i) {
			if(membrii.get(i)==member) {
				membrii.remove(i);
				nr--;
				return member;
			}
		}
		return null;
	}
	
	public String toString() {
		String n="Lider echipa: " + "<" + lider.nume + " " + lider.prenume + ">" + " ";
		for(int i=0;i<=membrii.size()-1;++i) {
			n+=membrii.get(i).nume+" " + membrii.get(i).prenume + "     ";
		}
		return n;
	}
	
	public abstract double getCost(); 
	
	
	public void SetNrMax(Membru membru,int nr_max) {
		if(membru.equals(lider)==true) {
			this.nr_max = nr_max;
		} else {
			System.out.println("NU ai voie, esti angajat nu sef, mai ai de muncit prietene");
		}
	}
}



class Membru {
	protected String nume;
	protected String prenume;
	protected int varsta;
	protected int salariu;
	protected int experienta;
	
	
	
	public Membru(String nume,String prenume, int varsta,int salariu,int experienta) {
		this.nume=nume;
		this.prenume=prenume;
		this.varsta=varsta;
		this.salariu=salariu;
		this.experienta=experienta;
	}
	
	public boolean equals(Object m) {
		if(m instanceof Membru) {
			return this==m;
		} else {
			return false;
		}
	}
}


class DevTeam extends Echipa {
	private double cost =0;
	
	public DevTeam(String nume, int nr_max) {
		super(nume,nr_max);
	}
	
	public double getCost() {
		cost = 2500 + lider.experienta * 250;
		for(int i=0;i<=membrii.size()-1;++i) {
			if(membrii.get(i).experienta<2) cost += 1500 ;
				else if((membrii.get(i).experienta>=2) && (membrii.get(i).experienta<=5)) cost += (1500 + 0.25*1500);
		    	         else cost+=(1500 + 0.5*1500);
		}
		return cost;
	}
}


class HR extends Echipa {
	private double cost;
	
	public HR(String nume, int nr_max) {
		super(nume,nr_max);
	}
	
	public double getCost() {
		cost = 1350 + lider.experienta * 300;
		for(int i=0;i<=membrii.size()-1;++i) {
			if(membrii.get(i).experienta<2) cost += 1000 ;
		    	else if((membrii.get(i).experienta>=2) && (membrii.get(i).experienta<=5)) cost += (1000 + 0.25*1000);
		    	         else cost+=(1000 + 0.5*1000);
		}
		return cost;
	}
	
}


class Main {
	public static void main(String[] args) {
	Echipa echipa1 = new DevTeam("Albatros",4);
	Echipa echipa2 = new HR("Pepito",5);
	
	Membru m1 = new Membru("Barbu","Daci",21,3000,6);
	Membru m2 = new Membru("Mergea","Sergiu",18,2000,1);
	Membru m3 = new Membru("Pegulescu","Bogdan",19,2200,3);
	Membru m4 = new Membru("Neagoe","Dan",21,2100,2);
	Membru m5 = new Membru("Belcea","Vlad",20,1900,3);
	Membru m6 = new Membru("Traila","Alin",30,3900,10);
	Membru m7 = new Membru("Cosma","Laurentiu",28,2800,4);
	Membru m8 = new Membru("Loghin","Alex",27,5000,7);
	Membru m9 = new Membru("Manciu","Bianca",27,3100,1);
	
	echipa1.addMember(m2);
	echipa1.addMember(m3);
	echipa1.addMember(m4);
	if(echipa1.setLeader(m1)==true) System.out.println(echipa1.getCost());
		else System.out.println("Membrul dat nu are experienta necesara pentru a fii lider"); 
	echipa2.addMember(m5);
	echipa2.addMember(m7);
	echipa2.addMember(m8);
	echipa2.addMember(m9);
	if(echipa2.setLeader(m6)==true) System.out.println(echipa2.getCost());
		else System.out.println("Membrul dat nu are experienta necesara pentru a fii lider"); 	
	
	echipa2.SetNrMax(m5,8);
	}
}
