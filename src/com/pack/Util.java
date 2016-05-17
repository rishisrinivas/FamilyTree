package com.pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

	static Person main;
    private static Printer printer;
    static List<Person> personList = new ArrayList<Person>();
	
    //This method is to get the relation from the level. Written for Console Printing. Not useful for image 
	public static Relation getRelationFromLevel(Person relative, int level) {
		
		if(level == 0) {
			if(main.getSpouse() ==  relative)
				return Relation.SPOUSE;
			else if(main.getFather().getChildren().contains(relative))
				return Relation.SIBLING;
			return Relation.OTHER; 
		} 
		
		 else if(level == 1) {
			if(main.getFather().equals(relative))  {
				return Relation.FATHER;
			} else if(main.getFather().getSpouse().equals(relative))
				return Relation.MOTHER;
			return Relation.OTHER;
		}
		
		else if(level == 2) {
			try {
			if(main.getFather().getFather().equals(relative) || main.getFather().getFather().getSpouse().equals(relative))
				return Relation.GRANDPARENT;
			} catch(Exception e) {}
			return Relation.OTHER;
		}
		
		 else if(level >= 3) 
				return Relation.GREAT_GRANDPARENT;
		
		 else if(level == -1) {
				if(main.getChildren().contains(relative))
					return Relation.CHILD;
				return Relation.OTHER;
		} 
				
		else if(level == -2) {
			try {
				if(relative.getFather().getFather().equals(main) || relative.getFather().getFather().getSpouse().equals(main))
					return Relation.GRANDCHILD;
			} catch(Exception e) {}
			return Relation.OTHER;
		}
		
		else if (level < -2)
			return Relation.GREAT_GRANDCHILD;
		
		return Relation.OTHER;
	}

	//This method is written in order to create the file in the format which is required to print the image
    static void printFamilyTreeInDOT(List<Person> persons, Person person) {
        try  {
            DOTPrinter printer = new DOTPrinter();
            Util.printer = printer;
            StringBuilder sb = new StringBuilder();
            printer.print("graph graphname {");
            printer.printNewLine();
            printer.print(persons.get(0).getID() + " [label=\"" + persons.get(0).getName() + "\"]");
            printer.printNewLine();
            for (Person p : persons) {
                if(p.getSpouse() != null) {
                	Person spouse = p.getSpouse();
                	printer.print(spouse.getID() + " [label=\"" + spouse.getName() + "\"]");
                	sb.append(p.getID() + " -- " + spouse.getID() + " [label=\"" + Relation.SPOUSE + "\"]\n");
                	printer.printNewLine();
                }
                for(Person child : p.getChildren()) {
                	printer.print(child.getID() + " [label=\"" + child.getName() + "\"]");
                	sb.append(p.getID() + " -- " + child.getID() + " [label=\"" + Relation.CHILD + "\"]\n");
                	printer.printNewLine();
                }
            }

            printer.print(sb.toString());
            printer.print("}");
            printer.printNewLine();

            printer.finalizeWriting();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

   public static void printFamilyOf(Person p, Printer printer) {
        Util.printer = printer;
        p.getFamily();
        Collections.sort(Util.personList, new LevelComparator());
    }
	
	
    // Writing the relation to console
	static void print(Person relative) {
		Relation relation = Util.getRelationFromLevel(relative, relative.getLevel());
        try {
            Util.printer.print(main, relation, relative);
        } catch (Exception ex) {
            System.out.println(ex);
        }
	}


	
    interface Printer {
        void print(Person from, Relation relation, Person to) throws IOException;
        void print(String text) throws IOException;
    }
    
    
    public static final class DOTPrinter implements Printer {
        private BufferedWriter writer;

        public DOTPrinter() throws IOException {
			FileWriter fw = new FileWriter("Outputfile.gv");
			writer = new BufferedWriter(fw); 
        }

        public void print(Person from, Relation relation, Person to) throws IOException{
            print(from.getID() + " -- " + to.getID() + " [label=\"" + relation + "\"]");
            printNewLine();
        }

        public void print(String text) throws IOException{
            writer.write(text);
        }

        public void printNewLine() throws IOException{
            writer.newLine();
        }

        public void finalizeWriting() throws IOException {
            writer.flush();
            writer.close();
        }
    }

    
    public static final class ConsolePrinter implements Printer {
        public void print(Person from, Relation relation, Person to) {
            print(from.getName() + "'s " + relation + " is "+ to.getName());
        }

        public void print(String text) {
            System.out.println(text);
        }
    }
    
    public static final class UnitTestPrinter implements Printer {
    	public StringBuilder utSB = new StringBuilder();
        public void print(Person from, Relation relation, Person to) {
            print(from.getName() + "'s " + relation + " is "+ to.getName());
        }

        public void print(String text) {
            utSB.append(text + "\n");
        }
        
        public void clear() {
        	this.utSB.delete(0, this.utSB.length());
        }
        
        public String getString() {
        	return this.utSB.toString();
        }
    }
}
