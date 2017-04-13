package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial02 extends Object {

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
    }

}
