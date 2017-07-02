package com.akura.krr.Ontology;


import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.FileManager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.apache.jena.ontology.OntModelSpec.OWL_MEM;

public class Tutorial03 {

    static final String inputFileName = "university.owl";
    static final String NS = "http://www.akura.com/review.owl#";

    public  static  void main(String args[]) {

        // Create ontology model.
        OntModel m = ModelFactory.createOntologyModel( OWL_MEM );

        // create an input stream to read the file.
        InputStream in = FileManager.get().open(inputFileName);

        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName +  " not found" );
        }

        // read the ontology from a file.
        m.read( in, "RDF/XML" );

        OntClass student =  m.getOntClass( NS + "Student" );
        Individual studentInd =  student.createIndividual( NS+"Student04" );

        Property first_name = m.getProperty(NS + "first_name");
        Property last_name = m.getProperty(NS + "last_name");
        Property studentId = m.getProperty(NS + "studentID");

        studentInd.addProperty(first_name, "Sharu");
        studentInd.addProperty(last_name, "Vive");
        studentInd.addProperty(studentId, "132343");

        try {
            PrintStream p = new PrintStream(inputFileName);
            m.write( p, "RDF/XML-ABBREV");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        Individual studentInd2 = m.getIndividual(NS + "Student04");
        System.out.println(studentInd2.getPropertyValue(studentId));
        System.out.println(studentInd2.getPropertyValue(first_name));
        System.out.println(studentInd2.getPropertyValue(last_name));


    }
}
