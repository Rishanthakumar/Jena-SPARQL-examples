package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial05_implicit_prefix extends Object {

    public static void main(String[] args) {
        Model m2 = ModelFactory.createDefaultModel();
        m2.read( "file:/tmp/fragment.rdf" );
        m2.write( System.out );
    }
}
