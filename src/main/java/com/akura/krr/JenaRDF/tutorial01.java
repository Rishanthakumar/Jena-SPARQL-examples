package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial01 extends Object {

    // some definitions
    private static String personURI = "http://somewhere/JohnSmith";
    private static String fullName = "John Smith";

    public static void main(String[] args) {

        // create an empty model
        Model model = ModelFactory.createDefaultModel();


        // create the resource
        Resource johnSmith = model.createResource(personURI);

        // add the property
        johnSmith.addProperty(VCARD.FN, fullName);
    }

}
