package com.akura.krr.Ontology;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.apache.jena.ontology.OntModelSpec.OWL_MEM;
import static org.apache.jena.ontology.OntModelSpec.OWL_MEM_MICRO_RULE_INF;

public class Tutorial01 {

    static final String inputFileName = "eswc-2006-09-21.rdf";

    public static void main(String args[]) {

        // create the base model
        String SOURCE = "http://www.eswc2006.org/technologies/ontology";
        String NS = SOURCE + "#";
        OntModel m = ModelFactory.createOntologyModel( OWL_MEM );


        InputStream in = FileManager.get().open(inputFileName);

     //   OutputStream os = FileManager.get().open(inputFileName);

        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName +  " not found" );
        }

        m.read( in, "OWL/XML" );

        OntClass place = m.getOntClass( NS + "Place" );

        EnumeratedClass ukCountries =
                m.createEnumeratedClass( NS + "UKCountries", null );

        ukCountries.addOneOf( place.createIndividual( NS + "england" ) );
        ukCountries.addOneOf( place.createIndividual( NS + "scotland" ) );
        ukCountries.addOneOf( place.createIndividual( NS + "wales" ) );
        ukCountries.addOneOf( place.createIndividual( NS + "northern_ireland" ) );

        for (Iterator i = ukCountries.listOneOf(); i.hasNext(); ) {
            Resource r = (Resource) i.next();
            System.out.println( r.getLocalName());
        }

        OntClass c = m.createClass( NS + "SomeClass" );

        // first way: use a call on OntModel
        Individual ind0 = m.createIndividual( NS + "ind0", c );

        // second way: use a call on OntClass
        Individual ind1 = c.createIndividual( NS + "ind1" );

        Ontology ont = m.getOntology("http://www.eswc2006.org/technologies/ontology");
        // now list the ontology imports
        for (ExtendedIterator<OntResource> imp = ont.listImports(); imp.hasNext();) {
            System.out.println( "Ontology ttp://www.eswc2006.org/technologies/ontology imports " + imp.next().getURI().toString() );
        }

        try {

            PrintStream p = new PrintStream(inputFileName);
            m.write( p, "RDF/XML" );
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        // m.write();


//        // create the reasoning model using the base
//        OntModel inf = ModelFactory.createOntologyModel( OWL_MEM_MICRO_RULE_INF, base );
//
//        // create a dummy paper for this example
//        OntClass paper = base.getOntClass( NS + "Paper" );
//        Individual p1 = base.createIndividual( NS + "paper1", paper );
//
//        // list the asserted types
//        for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
//            System.out.println( p1.getURI() + " is asserted in class " + i.next() );
//        }
//
//        // list the inferred types
//        p1 = inf.getIndividual( NS + "paper1" );
//        for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
//            System.out.println( p1.getURI() + " is inferred to be in class " + i.next() );
//        }
//
//        OntClass artefact = base.getOntClass( NS + "Artefact" );
//        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
//            OntClass c = i.next();
//            System.out.println( c.getURI() );
//        }
    }
}
