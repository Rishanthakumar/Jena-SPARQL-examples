package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial09 extends Object {

    static final String inputFileName1 = "vc-db-3.rdf";
    static final String inputFileName2 = "vc-db-4.rdf";

    public static void main(String[] args) {

        // create an empty model
        Model model1 = ModelFactory.createDefaultModel();
        Model model2 = ModelFactory.createDefaultModel();

        // use the class loader to find the input file
        InputStream in1 = FileManager.get().open(inputFileName1);
        if (in1 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        InputStream in2 = FileManager.get().open(inputFileName2);
        if (in2 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName2 + " not found");
        }

        // read the RDF/XML files
        model1.read( in1, "" );
        model2.read( in2, "" );

        // merge the graphs
        Model model = model1.union(model2);

        // difference
        Model model3 = model1.difference(model2);

        // print the graph as RDF/XML
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();
    }
}
