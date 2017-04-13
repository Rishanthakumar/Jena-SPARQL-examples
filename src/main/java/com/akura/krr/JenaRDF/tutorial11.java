package com.akura.krr.JenaRDF;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

import java.io.PrintWriter;

/**
 * Tutorial 01 which guide you to create a simple model.
 */
public class tutorial11 extends Object {


    public static void main(String[] args) {

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        Resource r = model.createResource();

        // add the property
        r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
                .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
                .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));

        // write out the graph
        model.write(new PrintWriter(System.out));
        System.out.println();

        // create the resource
        r = model.createResource();

        // add the property
        r.addProperty(RDFS.label, "11")
            .addProperty(RDFS.label, "11");

        // write out the Model
        model.write(System.out, "N-TRIPLE");
    }

}
