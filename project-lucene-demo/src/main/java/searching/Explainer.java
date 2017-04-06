package searching;

import common.Config;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class Explainer implements Config {
    public static void main(String[] args) throws IOException, ParseException {
        String indexDir = INDEX_BOOK_DIR;
        String queryExpression = "ant junit";

        Directory directory = FSDirectory.open(new File(indexDir));
        QueryParser parser = new QueryParser(Version.LUCENE_30, "subject", new SimpleAnalyzer());
        Query query = parser.parse(queryExpression);

        System.out.println("Query: " + queryExpression);

        IndexSearcher searcher = new IndexSearcher(directory);
        TopDocs topDocs = searcher.search(query, 10);

        for(ScoreDoc match: topDocs.scoreDocs){
            Explanation explanation = searcher.explain(query, match.doc);
            System.out.println("----------------");
            Document doc = searcher.doc(match.doc);
            System.out.println(doc.get("title"));
            System.out.println(explanation.toString());
        }
        searcher.close();
        directory.close();

    }
}
