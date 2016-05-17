package com.pack;

public class Main {

	public static void main(String[] args) {

		Person p0 = new Person(0, "Richard Shakespeare", Gender.MALE);
		Person p1 = new Person(1, "John Shakespeare", Gender.MALE);
		Person p2 = new Person(2, "Mary Arden", Gender.FEMALE);
		Person p3 = new Person(3, "Henry", Gender.MALE);
		Person p4 = new Person(4, "Margarett", Gender.FEMALE);
		Person p5 = new Person(5, "Joan", Gender.MALE);
		Person p6 = new Person(6, "Margaret", Gender.MALE);
		Person p7 = new Person(7, "Gilbert", Gender.MALE);
		Person p8 = new Person(8, "William", Gender.MALE);
		Person p9 = new Person(9, "Anne Hathway", Gender.FEMALE);
		Person p10 = new Person(10, "Anne", Gender.FEMALE);
		Person p12 = new Person(12, "Richard", Gender.MALE);
		Person p13 = new Person(13, "Susana", Gender.FEMALE);
		Person p14 = new Person(14, "John Hall", Gender.MALE);
		Person p15 = new Person(15, "Hamnet", Gender.MALE);
		Person p16 = new Person(16, "Judith", Gender.MALE);
		Person p17 = new Person(17, "Thomas Quiney", Gender.FEMALE);
		Person p18 = new Person(18, "Shakespeare", Gender.MALE);
		Person p19 = new Person(19, "Richard", Gender.MALE);
		Person p20 = new Person(20, "Thomas", Gender.MALE);
		Person p21 = new Person(21, "Elizabeth", Gender.FEMALE);
		Person p22 = new Person(22, "Thomas Nash", Gender.MALE);
		
		

        p1.setFather(p0);
        p2.setSpouse(p1);
        p3.setFather(p0);
        p3.setSpouse(p4);
        p5.setFather(p1);
        p6.setFather(p1);
        p7.setFather(p1);
        p8.setFather(p1);
        p8.setSpouse(p9);
        p10.setFather(p1);
        p12.setFather(p1);
        p13.setFather(p8);
        p13.setSpouse(p14);
        p15.setFather(p8);
        p16.setFather(p8);
        p16.setSpouse(p17);
        p18.setFather(p16);
        p19.setFather(p16);
        p20.setFather(p16);
        p21.setFather(p14);
        p21.setSpouse(p22);
		

        Util.printFamilyOf(p22, new Util.ConsolePrinter());
        Util.printFamilyTreeInDOT(Util.personList, p22);
	} 

}
