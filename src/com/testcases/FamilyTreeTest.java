package com.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import com.pack.Gender;
import com.pack.Person;
import com.pack.Util;

public class FamilyTreeTest {

	Util.UnitTestPrinter utPrinter = new Util.UnitTestPrinter();
	
	@After
	public void clearStringBuilder() {
		utPrinter.clear();
	}
	
	@Test
	public void testSpouseRelation() {
		Person p21 = new Person(21, "Elizabeth", Gender.FEMALE);
		Person p22 = new Person(22, "Thomas Nash", Gender.MALE);
		p21.setSpouse(p22);
		
		Util.printFamilyOf(p22, utPrinter);
		
		String s = "Thomas Nash's SPOUSE is Elizabeth\n";
		assertEquals("", s, utPrinter.getString());
	}
	
	@Test
	public void testChildRelation() {
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Adam", Gender.MALE);
		p1.setFather(p2);
		
		Util.printFamilyOf(p2, utPrinter);
		String s = "Adam's CHILD is James\n";
		assertEquals("", s, utPrinter.getString());
		
	}
	
	@Test
	public void testMotherAndFatherRelation() {
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Mary", Gender.FEMALE);
		Person p3 = new Person(3, "Henry", Gender.MALE);
		p1.setSpouse(p2);
		p3.setFather(p1);
		
		Util.printFamilyOf(p3, utPrinter);
		
		String s = "Henry's FATHER is James\n" + 
					 "Henry's MOTHER is Mary\n";
		
		assertEquals("", s, utPrinter.getString());
		
	}
	
	
	@Test
	public void testGrandParentRelation() {
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Smith", Gender.MALE);
		Person p3 = new Person(3, "Henry", Gender.MALE);
		p2.setFather(p1);
		p3.setFather(p2);
		
		Util.printFamilyOf(p3, utPrinter);
		
		String s = "Henry's FATHER is Smith\n" + 
					 "Henry's GRANDPARENT is James\n";
		
		assertEquals("", s, utPrinter.getString());
		
	}
	
	@Test
	public void testGrandChildRelation() {
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Smith", Gender.MALE);
		Person p3 = new Person(3, "Henry", Gender.MALE);
		p1.setFather(p2);
		p2.setFather(p3);
		
		Util.printFamilyOf(p3, utPrinter);
		
		String s = "Henry's CHILD is Smith\n" + 
					 "Henry's GRANDCHILD is James\n";
		
		assertEquals("", s, utPrinter.getString());
		
	}
	
	@Test
	public void testOtherRelation() {
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Smith", Gender.MALE);
		Person p3 = new Person(3, "Mary", Gender.FEMALE);
		p2.setFather(p1);
		p2.setSpouse(p3);
		
		Util.printFamilyOf(p1, utPrinter);
		
		String s = "James's CHILD is Smith\n" + 
					 "James's OTHER is Mary\n";
		
		assertEquals("", s, utPrinter.getString());
		
	}
	
	@Test
	public void testAllRelations() {
		
		Person p0 = new Person(1, "Slater", Gender.MALE);
		Person p1 = new Person(1, "James", Gender.MALE);
		Person p2 = new Person(2, "Mary", Gender.FEMALE);
		Person p3 = new Person(3, "Smith", Gender.MALE);
		Person p4 = new Person(4, "Mike", Gender.MALE);
		Person p5 = new Person(5, "Serena", Gender.FEMALE);
		Person p6 = new Person(6, "Root", Gender.MALE);
		Person p7 = new Person(7, "Amelie", Gender.MALE);
		
		p1.setFather(p0);
		p1.setSpouse(p2);
		p3.setFather(p1);
		p4.setFather(p1);
		p3.setSpouse(p5);
		p6.setFather(p3);
		p7.setSpouse(p6);
		
		Util.printFamilyOf(p3, utPrinter);
		
		String s = "Smith's FATHER is James\n" + 
					"Smith's MOTHER is Mary\n" + 
					"Smith's SPOUSE is Serena\n" + 
					"Smith's CHILD is Root\n" + 
					"Smith's GRANDPARENT is Slater\n" + 
					"Smith's SIBLING is Mike\n" + 
					"Smith's OTHER is Amelie\n";
		
		assertEquals("", s, utPrinter.getString());
		
	}
}
