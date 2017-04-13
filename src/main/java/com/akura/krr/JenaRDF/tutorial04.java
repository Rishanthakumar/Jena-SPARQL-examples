package com.akura.krr.JenaRDF;

import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.util.Symbol;
import org.apache.jena.vocabulary.VCARD;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial04 extends Object {

    public static void main(String[] args) {

        // some definitions
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();


        // create the resource
        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N,
                        model.createResource()
                            .addProperty(VCARD.Given, givenName)
                            .addProperty(VCARD.Family, familyName));

        // now write the model in XML form to a file
        model.write(System.out, "RDF/XML-ABBREV");

        System.out.println();
        System.out.println();

        // now write the model in N-TRIPLES form to a file
        model.write(System.out, "N-TRIPLES");
    }

}
