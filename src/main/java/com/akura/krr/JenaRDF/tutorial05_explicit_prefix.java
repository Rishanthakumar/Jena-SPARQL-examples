package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial05_explicit_prefix extends Object {

    public static void main(String[] args) {

        // Create an empty model
        Model model = ModelFactory.createDefaultModel();

        // name spaces
        String nsA = "http://somewhere/else#";
        String nsB = "http://nowhere/else#";

        // create root resource.
        Resource root = model.createResource(nsA + "root");

        // create properties
        Property P = model.createProperty(nsA + "P");
        Property Q = model.createProperty(nsB + "Q");

        Resource x = model.createResource(nsA + "x");
        Resource y = model.createResource(nsA + "y");
        Resource z = model.createResource(nsA + "z");

        model.add(root, P, x).add(root, P, y).add(y, Q, z);

        System.out.println("# -- no special prefixes defined");
        model.write(System.out);

        System.out.println();
        System.out.println();

        System.out.println( "# -- nsA defined" );
        model.setNsPrefix( "nsA", nsA );
        model.write( System.out );

        System.out.println();
        System.out.println();

        System.out.println( "# -- nsA and cat defined" );
        model.setNsPrefix( "cat", nsB );
        model.write( System.out );

    }
}
