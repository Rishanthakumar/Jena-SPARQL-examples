package com.akura.krr.SPARQL;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class Tutorial01 {

    static final String inputFileName = "vc-db-1.rdf";

    public static void main(String args[]) {

        // Create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);

        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName +  " not found" );
        }

        // read the RDF/XML file
        model.read(in, "");


        String queryString  =
                        "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>\n" +
                        "SELECT ?givenName\n" +
                        "WHERE {?y  vcard:Family  \"Smith\" ." +
                                "?y  vcard:Given  ?givenName ." +
                                "}";

        Query query1 = QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.create(query1, model);

        try {
            ResultSet resultSet = queryExecution.execSelect();

            while( resultSet.hasNext() ) {
                QuerySolution solution = resultSet.nextSolution();
                Literal name = solution.getLiteral("givenName");
                System.out.println(name);
            }
        } finally {
            queryExecution.close();
        }
    }
}
