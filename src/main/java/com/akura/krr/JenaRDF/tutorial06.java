package com.akura.krr.JenaRDF;

import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial06 extends Object {

    static final String inputFileName  = "vc-db-1.rdf";
    static final String johnSmithURI = "http://somewhere/JohnSmith";

    public static void main(String[] args) {

        // Create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );

        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName +  " not found" );
        }

        // read the RDF/XML file
        model.read(in, "");

        // retrieve the Adam Smith vcard resource from the model
        Resource vcard = model.getResource(johnSmithURI);

        // retrieve the value of the N property
        Resource name = vcard.getRequiredProperty(VCARD.N)
                        .getResource();

        // print the family and given name
        System.out.println("Family name: " + name.getProperty(VCARD.Family).getObject().toString());
        System.out.println("Given name: " + name.getProperty(VCARD.Given).getObject().toString());

        // retrieve the given name property

        String fullname = vcard.getProperty(VCARD.FN)
                                .getString();

        // add two nick name properties to vcard

        vcard.addProperty(VCARD.NICKNAME, "Smithy")
                .addProperty(VCARD.NICKNAME, "Adman");

        // set up the output
        System.out.println("The nicknames of \""+ fullname + "\" are: ");

        // list the nicknames
        StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);

        while( iter.hasNext()) {
            System.out.printf("     " + iter.nextStatement().getObject().toString());
        }


    }
}
