package com.pack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Person {
	
    private int id;
	private boolean isVisited;
	private String name;
	private Gender gender;
	private int level;

	private HashMap<Relation, Person> relationMap = new HashMap<>();
	private ArrayList<Person> children = new ArrayList<Person>();
	
	public Person(int id, String name, Gender gender) {
        this.id = id;
		this.name = name;
		this.gender = gender;
	}

    public int getID() {
        return this.id;
    }
	
	public void setFather(Person father) {
		this.relationMap.put(Relation.FATHER, father);
		father.children.add(this);
	}
	
	public void setSpouse(Person spouse) {
		this.relationMap.put(Relation.SPOUSE, spouse);
		spouse.relationMap.put(Relation.SPOUSE, this);
	}
	
	public void addChild(Person child) {
		if(this.isPersonMale()) {
			this.children.add(child);
			child.relationMap.put(Relation.FATHER, this);
		} else {
			throw new RuntimeException();
		}
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public boolean isPersonMale() {
		return (this.gender == Gender.MALE);
	}
	
	public Person getFather() {
		return this.relationMap.get(Relation.FATHER);
	}
	
	public Person getSpouse() {
		return this.relationMap.get(Relation.SPOUSE);
	}
	
	public List<Person> getChildren() {
		if(!this.isPersonMale()) {
			if(this.getSpouse() != null)
				return this.getSpouse().getChildren();
		}
		return this.children;
	}
	
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	
	@Override
	public String toString() {
		return this.getName();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Person ob = (Person) obj;
		return this.getName().equals(ob.getName());
	}

	
	public void getFamily() {
		this.setLevel(0);
		Util.personList.add(this);
		Util.main = this;
		this.setVisited(isVisited);
		Queue<Person> queue = new LinkedList<Person>();
		queue.add(this);
		this.getFamily(queue);
	}

	
	public void getFamily(Queue<Person> queue) {
		
		Person person = queue.remove();
		if (!person.isVisited()) {

			Person father = person.getFather();
			if (father != null && !father.isVisited()) {
				father.setLevel(person.getLevel() + 1);
				Util.personList.add(father);
				Util.print(father);
				queue.add(father);
				Person mother = father.getSpouse();
				if(mother!= null && !mother.isVisited())  {
					mother.setLevel(person.getLevel() + 1);
					Util.print(mother);
				mother.setVisited(true);
				}
			}

			Person spouse = person.getSpouse();
			if (spouse != null && !spouse.isVisited()) {
				spouse.setLevel(person.getLevel());
				Util.print(spouse);
				spouse.setVisited(true);
			}

			if (person.getChildren().size() != 0) {
				List<Person> children = person.getChildren();
				for (Person child : children) {
					if (!child.isVisited()) {
						child.setLevel(person.getLevel() - 1); 
						Util.print(child);
						Util.personList.add(child);
						if(child.isPersonMale()) {
							queue.add(child);
						}
					}
				}
			}

			person.setVisited(true);
		}
			if(!queue.isEmpty()) {
				Person nextPerson = queue.peek();
					nextPerson.getFamily(queue);
			}
		
	}
	
}



class LevelComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		if(o1.getLevel() > o2.getLevel()) 
			return -1;
		else 
			return 1;
	}
	
}























