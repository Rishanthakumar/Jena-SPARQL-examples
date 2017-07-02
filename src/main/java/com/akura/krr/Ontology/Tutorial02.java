package com.akura.krr.Ontology;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.util.Iterator;

public class Tutorial02 {

    public static void main(String args[]) {

        String SOURCE = "http://www.eswc2006.org/technologies/ontology";
        String NS = SOURCE + "#";

        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        OntClass programme = m.createClass( NS + "Programme" );
        OntClass orgEvent = m.createClass( NS + "OrganizedEvent" );

        ObjectProperty hasProgramme = m.createObjectProperty( NS + "hasProgramme" );

        hasProgramme.addDomain( orgEvent );
        hasProgramme.addRange( programme );
        hasProgramme.addLabel( "has programme", "en" );

        System.out.println(hasProgramme.isObjectProperty());


//        for (ExtendedIterator<String> i = orgEvent.listVersionInfo(); i.hasNext(); ) {
//            System.out.println(i.toString());
//        }

    }
}
