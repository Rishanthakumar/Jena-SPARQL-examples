package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial10 extends Object {

    static final String inputFileName = "vc-db-1.rdf";

    public static void main(String[] args) {

        // Create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);

        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        // create a bag
        Bag smiths = model.createBag();

        // select all the resources with a VCARD.FN property
        // whose value ends with "Smith"
        StmtIterator iter = model.listStatements(
                new SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                    @Override
                    public boolean selects(Statement s) {
                        return s.getString().endsWith("Smith");
                    }
                }
        );

        // add the Smith's to the bag
        while (iter.hasNext()) {
            smiths.add(iter.nextStatement().getSubject());
        }

        // print the graph as RDF/XML
        model.write(new PrintWriter(System.out));
        System.out.println();

        // print out the members of the bag
        NodeIterator iter2 = smiths.iterator();

        if (iter2.hasNext()) {
            System.out.println("The bag contains:");

            while (iter2.hasNext()) {
                System.out.printf("     "+ ((Resource) iter2.next()).getRequiredProperty(VCARD.FN).getString());
            }


        } else {
            System.out.println("The bag is empty");
        }


    }
}
