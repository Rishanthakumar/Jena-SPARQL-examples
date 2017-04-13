package com.akura.krr.SPARQL;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class Tutorial04 {

    static final String inputFileName = "vc-db-2.rdf";

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
                        "PREFIX info: <http://somewhere/peopleInfo#>\n" +
                        "SELECT ?resource\n" +
                        "WHERE {?resource  info:age  ?age ." +
                                "FILTER (?age <= 24)" +
                                "}";

        Query query1 = QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.create(query1, model);

        try {
            ResultSet resultSet = queryExecution.execSelect();

            while( resultSet.hasNext() ) {
                QuerySolution solution = resultSet.nextSolution();
                System.out.println(solution);
            }
        } finally {
            queryExecution.close();
        }
    }
}
